package com.ujiuye.controller.customer;

import com.ujiuye.pojo.Customer;
import com.ujiuye.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 查询，可附带条件,  连带分页
     * @param currentpage
     * @param keyindex
     * @param keyCode
     * @param num
     * @return
     */
    @RequestMapping("/showInfo")
    @ResponseBody
    public List<Customer> showInfo(int currentpage,String keyindex,String keyCode,String num){
            return  customerService.showALLOrByCondition(currentpage, keyindex, keyCode, num);
    }


    /**
     * 添加
     * @param customer
     * @return
     * @throws ParseException
     */
    @RequestMapping("/saveCustomer")
    public String saveCustomer(Customer customer) throws ParseException {
        customerService.addCustomer(customer);
        return "/customer.jsp";
    }


}
