package com.ujiuye.service.poject;

import com.ujiuye.pojo.Analysis;
import com.ujiuye.pojo.Project;
import com.ujiuye.utils.AnalysisBean;

import java.util.List;

public interface AnalysisService {

    //获取包装类的全部信息
    List<AnalysisBean> getAll();

    //添加需求分析
    void addAnalysis(Analysis analysis);

    //获取单个对象的全部内容
    Analysis getAnalysisById(int id);

    //更新
    void updateAnlisysById(Analysis analysis);

    //删除
    void delById(String ids);

    //搜索
    List<AnalysisBean> search(String cid,String keyword);

    //导出excel文件
    void daochu(String ids);
}
