package com.ujiuye.service.poject;

import com.ujiuye.pojo.Module;

import java.util.List;

public interface ModuleService {

    /**
     * 根据需求的外键查询拥有的所有模块
     * @param analysisFk
     * @return
     */
    List<Module> getModulesByAnalysisFk(int analysisFk);

    /**
     * 根据模块的外键查询某一某块模块
     * @param id
     * @return
     */
    Module getModuleByid(int id);
}
