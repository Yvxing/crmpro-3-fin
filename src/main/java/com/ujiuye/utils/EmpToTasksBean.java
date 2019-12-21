package com.ujiuye.utils;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Task;

import java.util.List;

public class EmpToTasksBean {
    private Employee employee;
    private List<Task> tasks;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
