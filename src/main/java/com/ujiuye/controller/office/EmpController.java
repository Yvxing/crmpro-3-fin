package com.ujiuye.controller.office;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Task;
import com.ujiuye.service.office.EmployeeService;
import com.ujiuye.utils.EmpToTasksBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录检查，并将结果存到session
     * @param name
     * @param psw
     * @return
     */
    @RequestMapping("/login")
    public String login(String name, String psw, HttpSession session){
        Employee employeeByUsername = employeeService.getEmployeeByUsername(name, psw);
        if (employeeByUsername !=null){
            session.setAttribute("admin",employeeByUsername);
            return "redirect:/index.jsp";
        }else{
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/getOneEmpAndTask")
    @ResponseBody
    public EmpToTasksBean getOneEmpAndTasks(HttpSession session){
        EmpToTasksBean empToTasksBean = new EmpToTasksBean();

        if (session.getAttribute("admin")==null){

            return null;
        }else {
            Employee admin = (Employee) session.getAttribute("admin");
            List<Task> tasksByEmpId = employeeService.getTasksByEmpId(admin.getEid());

            empToTasksBean.setEmployee(admin);
            empToTasksBean.setTasks(tasksByEmpId);

            return empToTasksBean;
        }
    }

}
