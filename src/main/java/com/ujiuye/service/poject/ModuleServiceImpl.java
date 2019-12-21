package com.ujiuye.service.poject;

import com.ujiuye.dao.ModuleMapper;
import com.ujiuye.pojo.Module;
import com.ujiuye.pojo.ModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService{
    @Autowired
    private ModuleMapper moduleMapper;

//    根据需求的外键获取所有的module
    public List<Module> getModulesByAnalysisFk(int analysisFk) {
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.createCriteria().andAnalysisFkEqualTo(analysisFk);

        List<Module> modules = moduleMapper.selectByExample(moduleExample);
        return modules;
    }

//    根据主键获取唯一的module
    public Module getModuleByid(int id) {
        return moduleMapper.selectByPrimaryKey(id);
    }
}
