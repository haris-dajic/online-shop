package com.onlineMarket.EurekaDiscoveryService.Models;

import com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers.*;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "VERIFICATION_CODE")
    private String verificationCode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "POINTS")
    private Integer points;

    @Column(name = "ROLE")
    private String role;

    protected User() {}

    public User(String name, String surname, String address, String phoneNumber, String email, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidNameException {
        if(name.matches("^([A-ZŠĐČĆ]{1}[a-zšđčć]{2,12}){1}($|(\\s{1}[A-ZŠĐČĆ]{1}[a-zšđčć]{2,12}){1}|(-{1}[A-ZŠĐČĆ]{1}[a-zšđčć]{2,12}){1})$"))
            this.name = name;
        else
            throw new InvalidNameException("Invalid name!");
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) throws InvalidSurnameException {
        if(surname.matches("^([A-ZŠĐČĆ]{1}[a-zšđčć]{2,12}){1}($|(\\s{1}[A-ZŠĐČĆ]{1}[a-zšđčć]{2,12}){1}|(-{1}[A-ZŠĐČĆ]{1}[a-zšđčć]{2,12}){1})$"))
            this.surname = surname;
        else
            throw new InvalidSurnameException("Invalid Surname!");
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) throws InvalidAddressException {
        if(address.matches("^([A-ZŽŠĐĆČ]{1}[a-zšđžćč]{1,15}\\s+){1,5}(b\\.b\\.|\\d+)$"))
            this.address = address;
        else
            throw new InvalidAddressException("Invalid Address!");
    }

    public String getVerificationCode() {
        return verificationCode;
    }
    public void setVerificationCode(String verificationCode) throws InvalidVerificationCodeException {
        if(verificationCode.matches("^([a-zA-Z0-9]{5}-){4}[a-zA-Z0-9]{5}$"))
            this.verificationCode = verificationCode;
        else
            throw new InvalidVerificationCodeException("Invalid Verification code!");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if(!phoneNumber.matches("^[+][0-9]{10,17}$"))
            throw new InvalidPhoneNumberException("Invalid Phone number!");
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws InvalidEmailException {
        if(email.matches("^[A-Za-z0-9-+_.]+@[a-zA-Z0-9-.]+\\.(com|ba)$"))
            this.email = email;
        else
            throw new InvalidEmailException("Invalid Email!");
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws InvalidPasswordException {
        if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{7,}"))
            this.password = password;
        else
            throw new InvalidPasswordException("Password mora sadržavati barem jedno veliko i malo slovo, broj i mora se sastojati od 7 i više karaktera!");
    }

    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) throws InvalidPointsException {
        if(points < 0 || points > 100)
            throw new InvalidPointsException("Invalid Points!");
        else
            this.points = points;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
