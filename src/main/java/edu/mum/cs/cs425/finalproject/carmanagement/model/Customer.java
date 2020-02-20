package edu.mum.cs.cs425.finalproject.carmanagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

//    @UniqueCustomerNumber
    @NotNull(message = "* Customer Number is required")
    @Column(name = "customerNumber", nullable = false, unique = true)
    private String customerNumber;

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


    @ManyToMany
    private List<Car> savedCars;

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

    public Customer(){
        this.savedCars = new ArrayList<>();
    }

    public Customer(@NotNull(message = "* Customer Number is required") String customerNumber, @NotNull(message = "* Name is required")
    @NotBlank(message = "* Name cannot be empty or blank space(s)") String name, @NotNull(message = "* Email is required")
    @NotBlank(message = "* Email cannot be empty or blank space(s)") String email, @NotNull(message = "* Password is required")
    @NotBlank(message = "* Password cannot be empty or blank space(s)") String password, @NotNull(message = "* Phone Number is required")
    @NotBlank(message = "* Phone Number cannot be empty or blank space(s)") String phoneNumber, @NotNull(message = "* Address is required")
    @NotBlank(message = "* Address cannot be empty or blank space(s)") String address) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.savedCars = new ArrayList<>();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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
    

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerNumber=" + customerNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +               
                '}';
    }

    public List<Car> getSavedCars() {
        return savedCars;
    }

    public void setSavedCars(List<Car> savedCars) {
        this.savedCars = savedCars;
    }

    public void addFavoriteCar(Car car) {
        if(this.savedCars == null) {
            this.savedCars = new ArrayList<>();
        }
        this.savedCars.add(car);
    }
}
