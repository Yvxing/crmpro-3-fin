package com.ujiuye.service.poject;

import com.ujiuye.dao.FunctionMapper;
import com.ujiuye.pojo.Function;
import com.ujiuye.pojo.FunctionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService{
    @Autowired
    private FunctionMapper functionMapper;

    /**
     * 根据模块的外键获得所有的功能
     * @param mid
     * @return
     */
    public List<Function> getFunctionsByModuleId(int mid) {
        FunctionExample functionExample = new FunctionExample();
        functionExample.createCriteria().andModeleFkEqualTo(mid);

        List<Function> functions = functionMapper.selectByExample(functionExample);
        return functions;
    }
}
