package com.ujiuye.controller.office;

import com.ujiuye.pojo.Baoxiao;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Expendituretype;
import com.ujiuye.service.office.BaoxiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/baoxiao")
public class BaoXiaoController {
    @Autowired
    private BaoxiaoService baoxiaoService;


    /**
     * 报销列表的展示
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Baoxiao> getAllBaoxiao(){
        return baoxiaoService.getAll();
    }

    /**
     * 将信息带入到审批界面
     * @param bxid
     * @return
     */
    @RequestMapping("/getOneBaoxiaoById")
    @ResponseBody
    public Baoxiao getOneBaoxiaoById(String bxid){
        return baoxiaoService.getOneBaoxiaoLsByBxid(bxid);
    }


    /**
     * 审批状态的修改,和对应条目增减
     * @param bxid
     * @param bxstatus
     * @param content
     * @param resp
     */
    @RequestMapping("/baoXiaoShenPi")
    public void baoXiaoShenPi(String bxid, int bxstatus, String content, HttpServletResponse resp) throws IOException {
        baoxiaoService.updateBxShenpi(bxid,bxstatus,content);
        resp.getWriter().print(true);
    }

    /**
     * 获得当前登录用户的报销列表
     * @param session
     * @return
     */
    @RequestMapping("/getBaoxiaoByeid")
    @ResponseBody
    public List<Baoxiao> getBaoxiaoByeid(HttpSession session){
        Employee admin = (Employee) session.getAttribute("admin");
        return baoxiaoService.myBaoxiao(admin);
    }


    /**
     * 进入编辑页面
     * @param bxid
     * @return
     */
    @RequestMapping("/showBaoxiaoAndAllExpendituretype")
    @ResponseBody
    public Baoxiao showBaoxiaoAndAllExpendituretype(String bxid){
        return baoxiaoService.showBaoxiaoAndAllExpendituretype(bxid);
    }

    /**
     * 报销的报销修改
     * @param durtime
     * @param session
     * @return
     */
    @RequestMapping("/updateBaoxiao")
    public String updateBaoxiao(Baoxiao baoxiao,String durtime,HttpSession session) throws ParseException {
        DateFormat df = new SimpleDateFormat("y-M-d");
        Date parse = df.parse(durtime);
        //由于类内部bxtime是date类型，所以为了解析不出现错误，将可以对上的字段直接使用类接受
        // ，将无法解析的date在jsp中修改name属性，使类中的属性名不与参数进行对接，将可以解析的全部使用对象接收并自动复制
        //无法解析的在修改jsp中的name属性后单独使用参数绑定获取，使用String接收date
        //直接在pojo内进行类的包装不会影响到mapper文件中的sql语句执行，当将原有对象和不能整合的属性整合后
        baoxiao.setBxtime(parse);
        baoxiao.setEmpFk(((Employee) session.getAttribute("admin")).getEid());
        baoxiao.setBxstatus(0);

        baoxiaoService.editBaoxiao(baoxiao);
        return "/mybaoxiao-base.jsp";
    }

    @RequestMapping("/getAllType")
    @ResponseBody
    public List<Expendituretype> getAllType(){
        return baoxiaoService.getAllExpendituretype();
    }

    @RequestMapping("/addBaoXiao")
    public String addBaoXiaoaddBaoXiao(Baoxiao baoxiao,String durtime,HttpSession session) throws ParseException {
        DateFormat df = new SimpleDateFormat("y-M-d");
        Date parse = df.parse(durtime);

        baoxiao.setBxtime(parse);
        baoxiao.setEmpFk(((Employee)session.getAttribute("admin")).getEid());
        baoxiao.setBxid(UUID.randomUUID().toString());

        baoxiaoService.addBaoXiao(baoxiao);
        return "/mybaoxiao-base.jsp";
    }

}
