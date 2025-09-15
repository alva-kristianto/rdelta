/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catatantech.re.rulesenginedelta.parallel;

/**
 *
 * @author Admin
 */
public class Person {
    private String name = "";
    private String status = "";
    private String penempatan = "";
    private int salary = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPenempatan() {
        return penempatan;
    }

    public void setPenempatan(String penempatan) {
        this.penempatan = penempatan;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "name: " + name + " -- status : " + status + " -- penempatan : " + penempatan + " -- salary: " + salary;
    }
    
    
}
