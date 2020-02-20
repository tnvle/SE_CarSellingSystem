package edu.mum.cs.cs425.finalproject.carmanagement.model;

import edu.mum.cs.cs425.finalproject.carmanagement.model.validators.UniqueDealerNumber;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="dealers")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealerId;

    @UniqueDealerNumber
    @NotNull(message = "* Dealer Number is required")
    @Column(name = "dealerNumber", nullable = false, unique = true)
    private String dealerNumber;

    @NotNull(message = "* Name is required")
    @NotBlank(message = "* Name cannot be empty or blank space(s)")
    private String name;

    @NotNull(message = "* Email is required")
    @NotBlank(message = "* Email cannot be empty or blank space(s)")
    private String email;

    @NotNull(message = "* Password is required")
    @NotBlank(message = "* Password cannot be empty or blank space(s)")
    private String password;

    @NotNull(message = "* Phone Number is required")
    @NotBlank(message = "* Phone Number cannot be empty or blank space(s)")
    private String phoneNumber;

    @NotNull(message = "* Address is required")
    @NotBlank(message = "* Address cannot be empty or blank space(s)")
    private String address;

    private String website;



    //modify start
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //modify end

    public Dealer(){

    }

    public Dealer(@NotNull(message = "* Dealer Number is required") String dealerNumber, @NotNull(message = "* Name is required")
    @NotBlank(message = "* Name cannot be empty or blank space(s)") String name, @NotNull(message = "* Email is required")
    @NotBlank(message = "* Email cannot be empty or blank space(s)") String email, @NotNull(message = "* Password is required")
    @NotBlank(message = "* Password cannot be empty or blank space(s)") String password, @NotNull(message = "* Phone Number is required")
    @NotBlank(message = "* Phone Number cannot be empty or blank space(s)") String phoneNumber, @NotNull(message = "* Address is required")
    @NotBlank(message = "* Address cannot be empty or blank space(s)") String address, String website) {
        this.dealerNumber = dealerNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.website = website;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "dealerId=" + dealerId +
                ", dealerNumber=" + dealerNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
