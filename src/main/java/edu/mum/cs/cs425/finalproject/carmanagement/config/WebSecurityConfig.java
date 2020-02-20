package edu.mum.cs.cs425.finalproject.carmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;

/**
 * @author okalu
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier(value = "eCarManagementUserDetailsService")
    @Autowired
    private UserDetailsService eCarManagementUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(eCarManagementUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "/ecarmanagement/public/**", "/webjars/**", "/assets/**").permitAll()
                .antMatchers("/ecarmanagement", "/ecarmanagement/about").permitAll()
                ///ecarmanagement/car/favorite
                .antMatchers("/ecarmanagement/car/favorite", "/ecarmanagement/car/save").hasRole("USER")
                .antMatchers("/ecarmanagement/car/search","/ecarmanagement/car/detail").permitAll()
                .antMatchers("/ecarmanagement/secured/car/**").hasRole("DEALER")
                .antMatchers("/ecarmanagement/secured/dealer/**").hasRole("ADMIN")
                .anyRequest().authenticated() 
                .and()
                .formLogin()
                .loginPage("/ecarmanagement/login")
                .defaultSuccessUrl("/ecarmanagement/home")
                .failureUrl("/ecarmanagement/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/ecarmanagement/logout"))
                .logoutSuccessUrl("/ecarmanagement/login?logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }
}
