package com.PostmanTest.Controllers;

import java.util.HashMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {
    HashMap<Integer, Employee> employeeList = new  HashMap<>();

    //list all employees
    @GetMapping("/employee")
    public @ResponseBody HashMap<Integer,Employee> getAllEmployees() {
        return employeeList;
    }

    //create employee
    @PostMapping("/employee")
    public @ResponseBody String createEmployee(@RequestBody Employee emp) {
        System.out.println(emp);
        employeeList.put(emp.getID(), emp);
        return "New employee is "+ emp.getFirstName() + " " + emp.getLastName();
    }

    //get employee info
    @GetMapping("/employee/{id}")
    public @ResponseBody Employee getEmployee(@PathVariable int id) {
    	Employee emp = employeeList.get(id);
        return emp;
    }

    //update employee info
    @PutMapping("/employee/{id}")
    public @ResponseBody Employee updatePerson(@PathVariable int id, @RequestBody Employee emp) {
    	employeeList.replace(id, emp);
        return employeeList.get(id);
    }

    //delete employee data
    @DeleteMapping("/employee/{id}")
    public @ResponseBody HashMap<Integer, Employee> deleteEmployee(@PathVariable int id) {
    	employeeList.remove(id);
        return employeeList;
    }
}

class Employee {
	
	private int id;
	
    private String firstName;

    private String lastName;

    private String address;

    private String telephone;

    public Employee(){}

    public Employee(int id, String firstName, String lastName, String address, String telephone){
    	this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }
    
    public int getID() {
    	return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return firstName + " " 
               + lastName + " "
               + address + " "
               + telephone;
    }
}

