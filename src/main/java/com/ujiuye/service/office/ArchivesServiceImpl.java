package com.ujiuye.service.office;

import com.ujiuye.dao.ArchivesMapper;
import com.ujiuye.dao.EmployeeMapper;
import com.ujiuye.pojo.Archives;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService{
    @Autowired
    private ArchivesMapper archivesMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Archives> getAll() {
        List<Archives> lists = new ArrayList<Archives>();
        List<Archives> archives = archivesMapper.selectByExample(null);
        for (Archives ac: archives) {
            EmployeeExample employeeExample = new EmployeeExample();
            employeeExample.createCriteria().andEidEqualTo(ac.getEmpFk());
            //获取全部的archives 在遍历过程中，通过获取ac的外键，在employee的条件查询中获取对应的employee数据
            //然后将该对象赋值给当次循环下的父对象ac  最终填入ac类型的列表中
            List<Employee> employees = employeeMapper.selectByExample(employeeExample);     //此处虽然获得是的list类型但是只有一条数据
            for (Employee e: employees) {
                ac.setEmployee(e);
                lists.add(ac);
            }
        }
        return lists;
    }
}
