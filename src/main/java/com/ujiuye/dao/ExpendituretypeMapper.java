package com.ujiuye.dao;

import com.ujiuye.pojo.Expendituretype;
import com.ujiuye.pojo.ExpendituretypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpendituretypeMapper {
    int countByExample(ExpendituretypeExample example);

    int deleteByExample(ExpendituretypeExample example);

    int deleteByPrimaryKey(Integer etid);

    int insert(Expendituretype record);

    int insertSelective(Expendituretype record);

    List<Expendituretype> selectByExample(ExpendituretypeExample example);

    Expendituretype selectByPrimaryKey(Integer etid);

    int updateByExampleSelective(@Param("record") Expendituretype record, @Param("example") ExpendituretypeExample example);

    int updateByExample(@Param("record") Expendituretype record, @Param("example") ExpendituretypeExample example);

    int updateByPrimaryKeySelective(Expendituretype record);

    int updateByPrimaryKey(Expendituretype record);
}