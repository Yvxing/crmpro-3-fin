package com.ujiuye.service.left;

import com.ujiuye.dao.ArchivesMapper;
import com.ujiuye.dao.EmailMapper;
import com.ujiuye.dao.EmployeeMapper;
import com.ujiuye.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailMapper emailMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ArchivesMapper archivesMapper;

    /**
     * 获取员工的信息和档案中的email
     * @return
     */
    public List<Employee> getEmployeeAndArchives() {
        List<Employee> employees = employeeMapper.selectByExample(null);
        for (Employee emps: employees) {
            ArchivesExample archivesExample = new ArchivesExample();
            archivesExample.createCriteria().andEmpFkEqualTo(emps.getEid());
            List<Archives> archives = archivesMapper.selectByExample(archivesExample);
            for (Archives as: archives) {
                emps.setArchives(as);
            }
        }
        return employees;
    }

    /**
     * 保存邮件信息，之后跳转到消息推送页面
     * @param email
     */
    public void addEmail(Email email) {
        emailMapper.insertSelective(email);
    }

    public List<Email> showGivePage() {
        List<Email> emails = emailMapper.selectByExample(null);
        for (Email em: emails) {
            Employee employee = employeeMapper.selectByPrimaryKey(em.getEmpFk2());
            em.setEmployee(employee);
        }
        return emails;
    }
}
