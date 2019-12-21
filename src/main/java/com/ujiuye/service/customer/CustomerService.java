package com.ujiuye.service.customer;

import com.ujiuye.pojo.Customer;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {

    /**
     * 查询，附带条件
     * @param currentpage
     * @param keyindex
     * @param keyCode
     * @param num
     *
     * 此处可以将返回值的类型直接换为pageinfo  类型
     * @return
     */
List<Customer> showALLOrByCondition(int currentpage,String keyindex,String keyCode,String num);


    /**
     * 添加客户
     */
void addCustomer(Customer customer) throws ParseException;

}
