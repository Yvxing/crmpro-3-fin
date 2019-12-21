package com.ujiuye.service.poject;

import com.ujiuye.pojo.Customer;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Project;
import com.ujiuye.utils.ProjectBean;

import java.util.List;

public interface ProjectService {

    /**
     * 项目管理的列表中的信息
     * @return
     */
    List<ProjectBean> getAllProjectBeans();

    /**
     * 查询所有客户的公司信息
     * @return
     */
    List<Customer> getAllCustomer();

    /**查询项目经理（技术负责人）下拉选项
     * @return
     */
    List<Employee> getAllEmployee();

    /**添加项目
     * @param project
     */
    void addProject(Project project);

    /**
     * 为了更新，根据id获取这个对象
     * @param pid
     * @return
     */
    Project getProjectByPId(int pid);

    /**
     * 更新项目信息
     * @param project
     */
    void updateProject(Project project);

    /**
     * 条件查询
     * @param cid
     * @param orderBy
     * @param keyword
     * @return
     */
    List<ProjectBean> searchProject(String cid,String orderBy,String keyword);


    /**
     * 删除选中的
     * @param id
     */
    void delSelected(int id);


    /**
     * excel 表的导出
     * @param ids
     */
    void doExcel(String ids);

}
