package com.ujiuye.service.office;

import com.ujiuye.pojo.Baoxiao;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Expendituretype;

import java.util.List;

public interface BaoxiaoService {

    /**
     * 报销列表中的数据展示
     * @return
     */
    List<Baoxiao> getAll();

    /**
     * 将相关信息带到审批页面
     * @param bxid
     * @return
     */
    Baoxiao getOneBaoxiaoLsByBxid(String bxid);

    /**
     *根据得到的bxid修改报销表的status并增加baoxiaoreply表一条数据
     */
    void updateBxShenpi(String bxid, int bxstatus, String content);

    /**
     * 根据session保存的登录用户，获得此人的报销信息
     * @param employee
     * @return
     */
    List<Baoxiao> myBaoxiao(Employee employee);


    /**
     * 获取当前对象的这一条报销信息和所有的报销类型
     * @param bxid
     * @return
     */
    Baoxiao showBaoxiaoAndAllExpendituretype(String bxid);


    /**
     * 我的报销的编辑
     * @param baoxiao
     */
    void editBaoxiao(Baoxiao baoxiao);

    /**
     * 获取全部的报销类型
     * @return
     */
    List<Expendituretype> getAllExpendituretype();

    /**
     * 添加当前用户新的报销
     * @param baoxiao
     */
    void addBaoXiao(Baoxiao baoxiao);

}
