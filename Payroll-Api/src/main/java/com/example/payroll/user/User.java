package com.example.payroll.user;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.security.SecureRandom;
import java.util.Objects;

@Entity
@Table(name="Users")
public class User {

    private @Id @Column @NotBlank @Size(min = 3, max = 100) String username;
    private @Column @NotBlank @Size(min = 6, max = 100) String password;
    private @Column @NotBlank @Size(min = 3, max = 100) String firstName;
    private @Column @NotBlank @Size(min = 3, max = 100) String lastName;


public User () {}

public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
}

    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //Password Verification Method
    public boolean checkPassword(String enteredPWD) {
     if(enteredPWD.equalsIgnoreCase(this.getPassword())){
          return true;
      }else
        return false;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password);
    }
}
