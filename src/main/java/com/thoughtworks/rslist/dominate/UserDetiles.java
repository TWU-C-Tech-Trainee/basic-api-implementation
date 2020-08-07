package com.thoughtworks.rslist.dominate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
public class UserDetiles {
    @NotNull
    @Size(max = 8)

    private String userName;

    @NotNull
    @Max(100)
    @Min(18)
    private int age;

    @NotNull
    private String gender;

    @Email
    private String email;

    @Pattern(regexp = "1\\d{10}")
    @NotNull
    private String phoneNumber;

    private int votes;

    public UserDetiles(String userName,int age,String gender,String email,String phoneNumber){
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}