package com.ujiuye.controller.office;

import com.ujiuye.pojo.Archives;
import com.ujiuye.service.office.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/archives")
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;

    /**
     * 返回要显示的所有信息
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public List<Archives> getAll(){
        return  archivesService.getAll();
    }
}
