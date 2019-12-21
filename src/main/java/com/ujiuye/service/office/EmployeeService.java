package com.ujiuye.service.office;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Task;

import java.util.List;

public interface EmployeeService {
    /**
     * 获取全部的员工信息
     * @return
     */
    List<Employee> getAll();

    /**
     * 查询是否有当前用户
     * @param username
     * @return
     */
    Employee  getEmployeeByUsername(String username,String psw);

    /**
     * 根据登录的员工的信息查询其对应的任务
     * @param id
     * @return
     */
    List<Task> getTasksByEmpId(int id);
}
