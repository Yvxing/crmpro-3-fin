package com.ujiuye.service.duibiao;

import com.ujiuye.dao.DatacollectMapper;
import com.ujiuye.pojo.Datacollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuiBiaoServiceImpl implements DuiBiaoService{
    @Autowired
    private DatacollectMapper datacollectMapper;

    /**
     * 回显全部数据
     * @return
     */
    public List<Datacollect> showData() {
        return datacollectMapper.selectByExample(null);
    }
}
