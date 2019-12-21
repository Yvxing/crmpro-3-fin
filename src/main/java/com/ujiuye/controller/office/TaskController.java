package com.ujiuye.controller.office;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Task;
import com.ujiuye.service.office.EmployeeService;
import com.ujiuye.service.office.TaskService;
import com.ujiuye.utils.TaskBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EmployeeService employeeService;


    /**
     * 添加任务
     * @param task
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("/saveTask")
    public String savetask(Task task,String start,String end) throws ParseException {
        DateFormat df = new SimpleDateFormat("y-M-d");
        task.setStarttime(df.parse(start));
        task.setEndtime(df.parse(end));
        task.setStatus(0);
        taskService.addTask(task);

        return "/task.jsp";
    }

    /**
     * 获取全部的员工信息
     * @return
     */
    @RequestMapping("/allEmps")
    @ResponseBody
    public List<Employee> emps(){
        return  employeeService.getAll();
    }

    /**
     * 展示任务，可附带条件
     * @param status
     * @return
     */
    @RequestMapping("/showTask")
    @ResponseBody
    public List<TaskBean> showTask(String status){
        return taskService.getAll(status);
    }

    @RequestMapping("/updateTaskStatus")
    public void  updateTaskStatus(int id, int status, HttpServletResponse resp) throws IOException {
        int i = taskService.updateTaskStatus(id, status);
        if (i>0){
            resp.getWriter().print(true);
        }else{
            resp.getWriter().print(false);
        }
    }

}
