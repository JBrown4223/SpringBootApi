package com.example.payroll.employee;

import org.hibernate.validator.constraints.UniqueElements;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Employees")
class Employee {


    private @Id @Column(name = "Username", nullable = false) @Size(min = 3, max = 100) String username;
    private @Column @NotBlank @Size(min = 3, max = 100) String firstName;
    private @Column @NotBlank @Size(min = 3, max = 100) String lastName;
    private @Column @Size(min = 6, max = 100) String password;
    private @Column String role;
    private @Column @GeneratedValue(strategy = GenerationType.IDENTITY) Long employeeNum;
    private @Column double salary;
    private @Column String pay_cycle;
    private @Column double pay;

    Employee() {}

    Employee(String username, String firstName, String lastName, String role, double salary, String pay_cycle, double pay) {

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
        this.pay_cycle = pay_cycle;
        this.pay = pay;

    }


    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmployeeNum() {
        return this.employeeNum;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRole() {
        return this.role;
    }

    public void setEmployeeNum(Long id) {
        this.employeeNum = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public void setPay_cycle(String pay_cycle) {
        this.pay_cycle = pay_cycle;
    }

    public double getSalary() {
        return salary;
    }

    public double getPay() {
        return pay;
    }

    public String getPay_cycle() {
        return pay_cycle;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.employeeNum, employee.employeeNum) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(this.employeeNum, this.firstName, this.lastName, this.role,this.salary,this.pay);
        return  hash;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.employeeNum + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", role='" + this.role + '\'' +  '\'' + ", Salary='" + this.salary + '\''
                + '\'' + ", Paycheck Amount='" + this.pay + '\''+ '}';
    }

    public void setUsername(String username) {
        int rand = (int) Math.random();
        this.username = username + rand;
    }
}
