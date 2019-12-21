package com.ujiuye.controller.projectM;

import com.ujiuye.pojo.Function;
import com.ujiuye.service.poject.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    /**
     * 根据module的id获取对应的全部记录的function记录的集合
     * @param mid
     * @return
     */
    @RequestMapping("/findFsByAid")
    @ResponseBody
    public List<Function> getfuns(int mid){
        return  functionService.getFunctionsByModuleId(mid);
    }

}
