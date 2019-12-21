package com.ujiuye.utils;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Task;

public class TaskBean {
    private Task task;
    private Employee employee;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
