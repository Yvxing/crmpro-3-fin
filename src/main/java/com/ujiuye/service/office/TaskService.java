package com.ujiuye.service.office;

import com.ujiuye.pojo.Task;
import com.ujiuye.utils.TaskBean;

import java.util.List;

public interface TaskService {

    /**
     * 添加
     * @param task
     */
    void addTask(Task task);

    /**
     * 获取全部任务信息
     * @param status
     * @return
     */
    List<TaskBean> getAll(String status);

    /**
     * 更新任务状态
     * @param id
     * @param status
     */
    int updateTaskStatus(int id, int status);
}
