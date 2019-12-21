package com.ujiuye.service.office;

import com.ujiuye.dao.BaoxiaoMapper;
import com.ujiuye.dao.BaoxiaoreplyMapper;
import com.ujiuye.dao.EmployeeMapper;
import com.ujiuye.dao.ExpendituretypeMapper;
import com.ujiuye.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BaoxiaoServiceImpl implements BaoxiaoService {

    @Autowired
    private BaoxiaoMapper baoxiaoMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ExpendituretypeMapper expendituretypeMapper;
    @Autowired
    private BaoxiaoreplyMapper baoxiaoreplyMapper;

    /**
     * 报销列表的数据展示
     * @return
     */
    public List<Baoxiao> getAll() {
        List<Baoxiao> lists = new ArrayList<Baoxiao>();
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(null);
        for (Baoxiao bx: baoxiaos) {
            Employee employee = employeeMapper.selectByPrimaryKey(bx.getEmpFk());
            Expendituretype expendituretype = expendituretypeMapper.selectByPrimaryKey(bx.getPaymode());
            bx.setEmployee(employee);
            bx.setExpendituretype(expendituretype);
            lists.add(bx);
        }
        return lists;
    }

    /**
     * 将信息带到审批页面
     * @param bxid
     * @return
     */
    public Baoxiao getOneBaoxiaoLsByBxid(String bxid) {
        Baoxiao baoxiao = baoxiaoMapper.selectByPrimaryKey(bxid);

        BaoxiaoreplyExample baoxiaoreplyExample = new BaoxiaoreplyExample();
        baoxiaoreplyExample.createCriteria().andBaoxiaoFkEqualTo(bxid);
        List<Baoxiaoreply> baoxiaoreplies = baoxiaoreplyMapper.selectByExample(baoxiaoreplyExample);

        baoxiao.setBaoxiaoreplies(baoxiaoreplies);

        return baoxiao;
    }

    /**
     * 利用bxid修改baoxiao表的bxstatus并增加baoxiaoreply表一条数据
     * @param bxid
     * @param bxstatus
     * @param content
     */
    public void updateBxShenpi(String bxid, int bxstatus, String content) {
        //更新状态
        Baoxiao baoxiao = baoxiaoMapper.selectByPrimaryKey(bxid);
        baoxiao.setBxstatus(bxstatus);
        baoxiaoMapper.updateByPrimaryKey(baoxiao);

        //添加新的回复
        Baoxiaoreply baoxiaoreply = new Baoxiaoreply();
        baoxiaoreply.setBaoxiaoFk(bxid);
        baoxiaoreply.setContent(content);
        DateFormat df = new SimpleDateFormat("y-M-d h-m-s");
        String format = df.format(new Date());
        try {
            baoxiaoreply.setReplytime(df.parse(format));
            baoxiaoreplyMapper.insert(baoxiaoreply);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *根据employee获得当前对象的报销列表
     * @param employee
     * @return
     */
    public List<Baoxiao> myBaoxiao(Employee employee) {
        Integer eid = employee.getEid();

        BaoxiaoExample baoxiaoExample = new BaoxiaoExample();
        baoxiaoExample.createCriteria().andEmpFkEqualTo(eid);
        List<Baoxiao> list = baoxiaoMapper.selectByExample(baoxiaoExample);
        for (Baoxiao bx: list) {
            bx.setEmployee(employee);
        }
        return  list;
    }

    /**
     * 获取当前对象这一条报销信息和所有的类型
     * @param bxid
     * @return
     */
    public Baoxiao showBaoxiaoAndAllExpendituretype(String bxid) {
        Baoxiao baoxiao = baoxiaoMapper.selectByPrimaryKey(bxid);
        List<Expendituretype> expendituretypes = expendituretypeMapper.selectByExample(null);
        BaoxiaoreplyExample baoxiaoreplyExample = new BaoxiaoreplyExample();
        baoxiaoreplyExample.createCriteria().andBaoxiaoFkEqualTo(bxid);
        List<Baoxiaoreply> baoxiaoreplies = baoxiaoreplyMapper.selectByExample(baoxiaoreplyExample);

        baoxiao.setExpendituretypes(expendituretypes);
        baoxiao.setBaoxiaoreplies(baoxiaoreplies);

        return baoxiao;
    }

    /**
     * 对报销的编辑
     * @param baoxiao
     */
    public void editBaoxiao(Baoxiao baoxiao) {
        baoxiaoMapper.updateByPrimaryKey(baoxiao);
    }

    /**
     * 获取全部的报销类型
     * @return
     */
    public List<Expendituretype> getAllExpendituretype() {
        return expendituretypeMapper.selectByExample(null);
    }

    /**
     * 添加当前用户新的报销
     * @param baoxiao
     */
    public void addBaoXiao(Baoxiao baoxiao) {
        baoxiaoMapper.insert(baoxiao);
    }

}
