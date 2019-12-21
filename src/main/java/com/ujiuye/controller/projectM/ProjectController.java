package com.ujiuye.controller.projectM;

import com.ujiuye.pojo.Customer;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Project;
import com.ujiuye.service.poject.ProjectService;
import com.ujiuye.utils.ProjectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 获取项目全部信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/allPro")
    public List<ProjectBean> getAll(){
        List<ProjectBean> allProjectBeans = projectService.getAllProjectBeans();
        return  allProjectBeans;
    }

    /**
     * 获取客户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/showCus")
    public List<Customer> allCus(){
        List<Customer> allCustomer = projectService.getAllCustomer();
        return allCustomer;
    }


    /**
     * 获取员工信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/showEmp")
    public List<Employee> allEmp(){
        List<Employee> allEmployee = projectService.getAllEmployee();
        return allEmployee;
    }


    /**
     * 添加新工程
     * @param pname
     * @param newcomname
     * @param empFk
     * @param empcount
     * @param starttime
     * @param buildtime
     * @param endtime
     * @param cost
     * @param level
     * @param remark
     * @return
     * @throws ParseException
     */
    @RequestMapping("/savePro")
    public String addProject(String pname,String newcomname,int empFk,int empcount,String starttime,String buildtime,String endtime,int cost,String level,String remark) throws ParseException {
        Project p = new Project();
        p.setPname(pname);
        p.setComname(Integer.parseInt(newcomname.split(",")[0]));
        p.setEmpFk(empFk);
        p.setEmpcount(empcount);

        //对时间的转化
        SimpleDateFormat df = new SimpleDateFormat("y-M-d");
        p.setStarttime(df.parse(starttime));
        p.setBuildtime(df.parse(buildtime));
        p.setEndtime(df.parse(endtime));

        p.setCost(cost);
        p.setLevel(level);
        p.setRemark(remark);

        projectService.addProject(p);
        return "/project-base.jsp";
    }


    /**
     * 将信息带到网页中
     * @param pid
     * @return
     */
    @RequestMapping("/toupdate")
    public ModelAndView toupdate(int pid){
        ModelAndView mv = new ModelAndView();
        Project projectByPId = projectService.getProjectByPId(pid);
        DateFormat df = new SimpleDateFormat("y-M-d");
        //对时间的处理
        String start = df.format(projectByPId.getStarttime());
        String build = df.format(projectByPId.getBuildtime());
        String end = df.format(projectByPId.getEndtime());

        mv.addObject("start",start);
        mv.addObject("build",build);
        mv.addObject("end",end);
        mv.addObject("item",projectByPId);
        mv.setViewName("/project-base-edit.jsp");
        return mv;
    }

    /**
     * 更新数据
     * @return
     */
    @RequestMapping("/update")
    public String update(int pid,String pname,int comname,int empFk,int empcount,String starttime,String buildtime,String endtime,int cost,String level,String remark) throws ParseException {
        ModelAndView mv = new ModelAndView();
        DateFormat df = new SimpleDateFormat("y-M-d");
        Project p = new Project();

        p.setPid(pid);
        p.setPname(pname);
        p.setComname(comname);
        p.setEmpFk(empFk);
        p.setEmpcount(empcount);
        p.setStarttime(df.parse(starttime));
        p.setBuildtime(df.parse(buildtime));
        p.setEndtime(df.parse(endtime));
        p.setCost(cost);
        p.setLevel(level);
        p.setRemark(remark);

        projectService.updateProject(p);
        return "/project-base.jsp";
    }

    /**
     * 查看详情
     * @param pid
     * @return
     */
    @RequestMapping("/detail")
    public ModelAndView detail(int pid){
        Project byPId = projectService.getProjectByPId(pid);
        ModelAndView mv = new ModelAndView();
        DateFormat df = new SimpleDateFormat("y-M-d");

        mv.addObject("start",df.format(byPId.getStarttime()));
        mv.addObject("build",df.format(byPId.getBuildtime()));
        mv.addObject("end",df.format(byPId.getEndtime()));
        mv.addObject("item",byPId);

        mv.setViewName("/project-base-look.jsp");
        return mv;
    }

    /**
     * 条件查询
     * @param cid
     * @param orderby
     * @param keyword
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView search(String cid,String orderby,String keyword){
        ModelAndView mv = new ModelAndView();
        if (cid!=null || orderby !=null || keyword!=null){
            List<ProjectBean> projectBeans = projectService.searchProject(cid, orderby, keyword);

            mv.addObject("item",projectBeans);
            mv.setViewName("/project-search.jsp");
            return mv;
        }else{
            return null;
        }
    }

    /**
     * 删除
     * @param ids
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/del")
    public void del(String  ids,HttpServletResponse resp) throws IOException {
        String[] split = ids.split(",");
        for (String s: split) {
            projectService.delSelected(Integer.parseInt(s));
        }
        resp.getWriter().print(true);           //ajax的接受数据类型要注意，text才可以使用if判断data==true
    }

    /**
     * excel导出
     * @param ids
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/daochu")
    public void daochu(String ids,HttpServletResponse resp) throws IOException {
            projectService.doExcel(ids);
        resp.getWriter().print(true);
    }

}
