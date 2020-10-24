package com.govtech.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="employee_id")
    private String employeeId;

    @Column(name="login_name")
    private String loginName;

    @Column(name="name")
    private String name;

    @Column(name="salary")
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String loginName, String name, double salary) {
        this.employeeId = employeeId;
        this.loginName = loginName;
        this.name = name;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(employeeId, employee.employeeId) &&
                Objects.equals(loginName, employee.loginName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, loginName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

