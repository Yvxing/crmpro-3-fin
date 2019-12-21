package com.ujiuye.controller.projectM;

import com.ujiuye.pojo.Analysis;
import com.ujiuye.pojo.Module;
import com.ujiuye.pojo.Project;
import com.ujiuye.service.poject.AnalysisService;
import com.ujiuye.service.poject.ModuleService;
import com.ujiuye.service.poject.ProjectService;
import com.ujiuye.utils.AnalysisBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModuleService moduleService;

    /**
     * 查询全部analysis
     * @return
     */
@RequestMapping("/getAll")
@ResponseBody
    public List<AnalysisBean> getAll(){
    List<AnalysisBean> all = analysisService.getAll();
    return all;
}

//    /**
//     * projects全部获取
//     * @return
//     */
//@RequestMapping("/getPro")
//@ResponseBody
//    public List<ProjectBean> getpro(){
//    List<ProjectBean> allProjectBeans = projectService.getAllProjectBeans();
//    return allProjectBeans;
//}

    /**
     * 查询详情
     * @param id
     * @return
     */
@RequestMapping("/findOne")
public ModelAndView getone(int id){
    ModelAndView mv = new ModelAndView();
    Analysis analysisById = analysisService.getAnalysisById(id);
    Project projectByPId = projectService.getProjectByPId(id);

    mv.addObject("item",analysisById);
    mv.addObject("pname",projectByPId.getPname());
    mv.setViewName("/project-need-look.jsp");
return mv;
}

//对task-add.jsp中的数据传送
@RequestMapping("/totask")
@ResponseBody
    public AnalysisBean  totask(int id){
    AnalysisBean ab = new AnalysisBean();

    Analysis analysisById = analysisService.getAnalysisById(id);
    List<Module> modules = moduleService.getModulesByAnalysisFk(id);

    ab.setAnalysis(analysisById);
    ab.setModules(modules);

    return ab;
}


    /**
     * 添加
     * @param file
     * @param analysis
     * @return
     * @throws IOException
     */
@RequestMapping("/add")
    public String add(MultipartFile file, Analysis analysis) throws IOException {
    String originalFilename = file.getOriginalFilename();
    String path="e://analysis";
    File file1 = new File(path+originalFilename);

    if (!file1.exists()){
        file1.mkdirs();
    }
    file.transferTo(file1);

    analysis.setId(analysis.getId());
    analysis.setAddtime(new Date());
    analysis.setUpdatetime(new Date());
    analysis.setDetaileddis(analysis.getDetaileddis());
    analysis.setRemark(analysis.getRemark());
    analysis.setTitle(analysis.getTitle());
    analysis.setProname(path+originalFilename);

    analysisService.addAnalysis(analysis);
    return "/project-need.jsp";
}

    /**
     * 带信息到修改页面
     * @param id
     * @return
     */
@RequestMapping("/toupdate")
    public ModelAndView toupdate(int id){
    ModelAndView mv = new ModelAndView();
    Analysis analysisById = analysisService.getAnalysisById(id);
    Project projectByPId = projectService.getProjectByPId(id);

    mv.addObject("item",analysisById);
    mv.addObject("pname",projectByPId.getPname());
    mv.setViewName("/project-need-edit.jsp");
    return mv;
}

    /**
     * 更新
     * @param analysis
     * @return
     */
@RequestMapping("/update")
    public String update(Analysis analysis){
    analysis.setUpdatetime(new Date());
    analysisService.updateAnlisysById(analysis);
    return "/project-need.jsp";
}


    /**
     * 删除
     * @param ids
     * @param resp
     * @throws IOException
     */
@RequestMapping("/del")
    public  void del(String ids, HttpServletResponse resp) throws IOException {
    analysisService.delById(ids);
    resp.getWriter().print(true);
}

    /**
     * 查询
     * @param cid
     * @param keyword
     * @return
     */
@RequestMapping("/search")
    public ModelAndView search(String cid,String keyword){
    ModelAndView mv = new ModelAndView();
    List<AnalysisBean> search = analysisService.search(cid, keyword);
    mv.addObject("item",search);
    mv.setViewName("/project-need-search.jsp");
    return mv;
}

@RequestMapping("/daochu")
    public void daochu(String ids,HttpServletResponse resp) throws IOException {
    analysisService.daochu(ids);
    resp.getWriter().print(true);
}


}
