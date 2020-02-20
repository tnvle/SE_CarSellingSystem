package edu.mum.cs.cs425.finalproject.carmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
@SpringBootApplication
public class CarmanagementApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CarmanagementApplication.class, args);
    }
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CarmanagementApplication.class);
    }
}
