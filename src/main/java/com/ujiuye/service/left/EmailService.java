package com.ujiuye.service.left;

import com.ujiuye.pojo.Email;
import com.ujiuye.pojo.Employee;

import java.util.List;

public interface EmailService {

    /**
     * 回显发送邮件时的人员选项
     * @return
     */
    List<Employee> getEmployeeAndArchives();

    /**
     * 保存邮件的信息,然后跳转到消息推送页面
     * @param email
     */
    void addEmail(Email email);

    /**
     * 展示message-give页面的信息
     * @return
     */
    List<Email> showGivePage();
}
