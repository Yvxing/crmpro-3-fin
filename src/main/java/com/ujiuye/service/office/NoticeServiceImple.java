package com.ujiuye.service.office;

import com.ujiuye.dao.NoticeMapper;
import com.ujiuye.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImple implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 获取全部notice
     * @return
     */
    public List<Notice> getAllNotices() {
        return noticeMapper.selectByExample(null);
    }
}
