package com.ujiuye.service.poject;

import com.ujiuye.dao.CustomerMapper;
import com.ujiuye.dao.EmployeeMapper;
import com.ujiuye.dao.ProjectMapper;
import com.ujiuye.pojo.*;
import com.ujiuye.utils.ProjectBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;


    public List<ProjectBean> getAllProjectBeans() {
        List<ProjectBean> lists = new ArrayList<ProjectBean>();
        //查询项目
        List<Project> projectList =  projectMapper.selectByExample(null);
        for (Project   p :  projectList) {
            ProjectBean pb = new ProjectBean();

            CustomerExample customerExample = new CustomerExample();
            CustomerExample.Criteria customerExampleCriteria = customerExample.createCriteria();
            customerExampleCriteria.andIdEqualTo(p.getComname());
            List<Customer> customers = customerMapper.selectByExample(customerExample);

            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria employeeExampleCriteria = employeeExample.createCriteria();
            employeeExampleCriteria.andEidEqualTo(p.getEmpFk());
            List<Employee> employees = employeeMapper.selectByExample(employeeExample);

            pb.setProject(p);
            pb.setCustomer(customers.get(0));
            pb.setEmployee(employees.get(0));

            lists.add(pb);
        }



        return lists;
    }


    /**
     * 获取全部客户信息
     * @return
     */
    public List<Customer> getAllCustomer() {
        return customerMapper.selectByExample(null);
    }

    /**
     * 获取全部雇员的信息
     * @return
     */
    public List<Employee> getAllEmployee() {
        return employeeMapper.selectByExample(null);
    }

    /**
     * 增加项目
     * @param project
     */
    public void addProject(Project project) {
        projectMapper.insertSelective(project);
    }

    /**
     * 通过pid获取项目的单个项目
     * @param pid
     * @return
     */
    public Project getProjectByPId(int pid) {
        return projectMapper.selectByPrimaryKey(pid);
    }

    /**
     * 更新project
     * @param project
     */
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    /**
     * 条件查询
     * @param cid
     * @param orderBy
     * @param keyword
     * @return
     */
    public List<ProjectBean> searchProject(String cid, String orderBy, String keyword) {
        List<ProjectBean> lists = new ArrayList<ProjectBean>();
        if (cid!=null && orderBy!=null){
            if (cid.equals("1")) {
                ProjectExample projectExample = new ProjectExample();
                ProjectExample.Criteria criteria = projectExample.createCriteria();
                criteria.andPnameEqualTo(keyword);
                List<Project> projects = projectMapper.selectByExample(projectExample);
                for (Project p : projects) {
                    ProjectBean pb = new ProjectBean();
                    //通过id分别获取了custom中的数据
                    CustomerExample customerExample = new CustomerExample();
                    CustomerExample.Criteria customerExampleCriteria = customerExample.createCriteria();
                    customerExampleCriteria.andIdEqualTo(p.getComname());
                    List<Customer> customers = customerMapper.selectByExample(customerExample);
                    //通过id分别获取employee中的数据
                    EmployeeExample employeeExample = new EmployeeExample();
                    EmployeeExample.Criteria employeeExampleCriteria = employeeExample.createCriteria();
                    employeeExampleCriteria.andEidEqualTo(p.getEmpFk());
                    List<Employee> employees = employeeMapper.selectByExample(employeeExample);

                    pb.setProject(p);
                    pb.setEmployee(employees.get(0));
                    pb.setCustomer(customers.get(0));
                    lists.add(pb);
                }
            }else {
                EmployeeExample employeeExample = new EmployeeExample();
                EmployeeExample.Criteria employeeExampleCriteria = employeeExample.createCriteria();
                employeeExampleCriteria.andEnameEqualTo(keyword);
                List<Employee> employees = employeeMapper.selectByExample(employeeExample);
                Integer eid = employees.get(0).getEid();

                ProjectExample projectExample = new ProjectExample();
                ProjectExample.Criteria criteria = projectExample.createCriteria();
                criteria.andEmpFkEqualTo(eid);
                List<Project> projects = projectMapper.selectByExample(projectExample);
                for (Project p: projects) {
                    ProjectBean pb = new ProjectBean();
                    //通过id分别获取了custom中的数据
                    CustomerExample customerExample = new CustomerExample();
                    CustomerExample.Criteria customerExampleCriteria = customerExample.createCriteria();
                    customerExampleCriteria.andIdEqualTo(p.getComname());
                    List<Customer> customers = customerMapper.selectByExample(customerExample);
                    //通过id分别获取employee中的数据
                    EmployeeExample employeeExample1 = new EmployeeExample();
                    EmployeeExample.Criteria employeeExampleCriteria1 = employeeExample1.createCriteria();
                    employeeExampleCriteria1.andEidEqualTo(p.getEmpFk());
                    List<Employee> employeeses = employeeMapper.selectByExample(employeeExample1);

                    pb.setProject(p);
                    pb.setEmployee(employeeses.get(0));
                    pb.setCustomer(customers.get(0));
                    lists.add(pb);
                }

            }
        }
        if (orderBy!=null){

        }
        return null;
    }

    /**
     * 删除选定的
     * @param id
     */
    public void delSelected(int id) {
        projectMapper.deleteByPrimaryKey(id);
    }

    /**
     * excel文件的导出
     * @param ids
     */
    public void doExcel(String ids) {
            //创建工作簿
        HSSFWorkbook sheets = new HSSFWorkbook();
            //创建表
        HSSFSheet sheet = sheets.createSheet("crmpro");
        //接下来获取数据库中数据，也有可能需要对应的字段名

        //获取字段名
        List<String> projectList = projectMapper.getSheetName("project");
        //获取要导出的数据
        String[] split = ids.split(",");
        List<Map<String,String>> projects = new ArrayList<Map<String, String>>();
        for(int i=0; i<split.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            DateFormat df = new SimpleDateFormat("y-M-d");
            Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(split[i]));

            map.put("pid", project.getPid() + "");
            map.put("pname", project.getPname());
            map.put("comname", project.getComname() + "");
            map.put("emp_fk1", project.getEmpFk1() + "");
            map.put("empcount", project.getEmpcount() + "");
            map.put("starttime", df.format(project.getStarttime()));
            map.put("buildtime", df.format(project.getBuildtime()));
            map.put("cost", project.getCost() + "");
            map.put("level", project.getLevel() + "");
            map.put("endtime", df.format(project.getEndtime()));
            map.put("remark", project.getRemark());
            map.put("emp_fk", project.getEmpFk() + "");

            projects.add(map);
        }
            for(int i=0;i<projects.size();i++){
                //获取数据，创建行
                HSSFRow row = sheet.createRow(i);
                for (int j = 0;j<projectList.size();j++){
                    //获取字段创建列
                    HSSFCell cell = row.createCell(j);
                    if (i==0){
                        cell.setCellValue(projectList.get(j));
                    }else{
                        cell.setCellValue(projects.get(i).get(projectList.get(j)));
                    }
                }
            }
            try {
                sheets.write(new File("e://aa.xls"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


