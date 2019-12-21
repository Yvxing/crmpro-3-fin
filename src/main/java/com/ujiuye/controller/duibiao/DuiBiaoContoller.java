package com.ujiuye.controller.duibiao;

import com.ujiuye.pojo.Datacollect;
import com.ujiuye.service.duibiao.DuiBiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/duibiao")
public class DuiBiaoContoller {

    @Autowired
    private DuiBiaoService duiBiaoService;

    /**
     * 回显数据
     * @return
     *
     * 此方法的返回值类型可以换为pageinfo  说明见对应impl类的注解
     * 可以减少数据的重复注入,大幅简化代码复杂程度
     */
    @RequestMapping("/showPng")
    @ResponseBody
    public List<Datacollect> duibiao(){
        return duiBiaoService.showData();
    }


}
