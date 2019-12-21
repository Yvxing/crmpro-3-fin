package com.ujiuye.utils;

import com.ujiuye.pojo.Customer;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Project;

public class ProjectBean {
    private Project project;
    private Customer customer;
    private Employee employee;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
