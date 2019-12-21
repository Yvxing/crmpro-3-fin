package com.ujiuye.service.poject;

import com.ujiuye.pojo.Function;

import java.util.List;

public interface FunctionService {
    /**
     * 根据模块的id获取所有的功能数据
     * @param mid
     * @return
     */
    List<Function> getFunctionsByModuleId(int mid);
}
