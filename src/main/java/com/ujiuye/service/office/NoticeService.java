package com.ujiuye.service.office;

import com.ujiuye.pojo.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 获取全部通知的记录
     * @return
     */
    List<Notice> getAllNotices();
}
