package com.ujiuye.service.office;

import com.ujiuye.dao.EmployeeMapper;
import com.ujiuye.dao.TaskMapper;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Task;
import com.ujiuye.pojo.TaskExample;
import com.ujiuye.utils.TaskBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 添加任务
     * @param task
     */
    public void addTask(Task task) {
        taskMapper.insertSelective(task);
    }

    /**
     * 展示任务信息,可以附带条件查询
     * @param status
     * @return
     */
    public List<TaskBean> getAll(String status) {
        List<TaskBean> lists = new ArrayList<TaskBean>();
        List<Task> list = null;
        if (status.equals("")){
            list = taskMapper.selectByExample(null);
        }else{
            TaskExample taskExample = new TaskExample();
            taskExample.createCriteria().andStatusEqualTo(Integer.parseInt(status));
            list = taskMapper.selectByExample(taskExample);
        }
        for (Task t:list) {
            TaskBean tb = new TaskBean();
            Employee employee = employeeMapper.selectByPrimaryKey(t.getEmpFk2());
            tb.setTask(t);
            tb.setEmployee(employee);
            lists.add(tb);
        }
        return  lists;
    }

    /**
     * 更新任务状态
     * @param id
     * @param status
     */
    public int updateTaskStatus(int id, int status) {
        Task task = taskMapper.selectByPrimaryKey(id);
        task.setStatus(status);
        int i = taskMapper.updateByPrimaryKey(task);
        return i;
    }
}
