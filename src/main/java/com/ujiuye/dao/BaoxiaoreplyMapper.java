package com.ujiuye.dao;

import com.ujiuye.pojo.Baoxiaoreply;
import com.ujiuye.pojo.BaoxiaoreplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaoxiaoreplyMapper {
    int countByExample(BaoxiaoreplyExample example);

    int deleteByExample(BaoxiaoreplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Baoxiaoreply record);

    int insertSelective(Baoxiaoreply record);

    List<Baoxiaoreply> selectByExample(BaoxiaoreplyExample example);

    Baoxiaoreply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Baoxiaoreply record, @Param("example") BaoxiaoreplyExample example);

    int updateByExample(@Param("record") Baoxiaoreply record, @Param("example") BaoxiaoreplyExample example);

    int updateByPrimaryKeySelective(Baoxiaoreply record);

    int updateByPrimaryKey(Baoxiaoreply record);
}