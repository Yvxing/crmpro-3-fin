package com.ujiuye.service.office;

import com.ujiuye.dao.EmployeeMapper;
import com.ujiuye.dao.TaskMapper;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.EmployeeExample;
import com.ujiuye.pojo.Task;
import com.ujiuye.pojo.TaskExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询所有的信息，select给null就是逆向工程的无条件查询全部
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExample(null);
    }

    /**
     * 检查用户登录
     * @param username
     * @param psw
     * @return
     */
    public Employee getEmployeeByUsername(String username,String psw) {
        if (username.equals("")){
            return null;
        }else{
            EmployeeExample employeeExample = new EmployeeExample();
            employeeExample.createCriteria().andUsernameEqualTo(username);
            List<Employee> employees = employeeMapper.selectByExample(employeeExample);
            if (employees.size()!=1){
                return null;
            }else {
                if (employees.get(0).getPassword().equals(psw)){
                    return employees.get(0);
                }else{
                    return null;
                }
            }
        }
    }

    /**
     * 查找员工对应的任务列表
     * @param id
     * @return
     */
    public List<Task> getTasksByEmpId(int id) {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andEmpFk2EqualTo(id);
        List<Task> list = taskMapper.selectByExample(taskExample);
        return list;
    }
}
