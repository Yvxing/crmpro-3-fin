package com.ujiuye.controller.office;

import com.ujiuye.pojo.Notice;
import com.ujiuye.service.office.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    /**
     * 获取全部的消息
     * @return
     */
    @RequestMapping("/showAllNotice")
    @ResponseBody
    public List<Notice> showAllNotice(){
        return noticeService.getAllNotices();
    }

}
