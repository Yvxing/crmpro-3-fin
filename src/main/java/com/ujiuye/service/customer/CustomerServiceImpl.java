package com.ujiuye.service.customer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.dao.CustomerMapper;
import com.ujiuye.pojo.Customer;
import com.ujiuye.pojo.CustomerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;


    /**
     * 查询，附带条件
     * @param currentpage
     * @param keyindex
     * @param keyCode
     * @param num
     * @return
     *
     * 可以将返回值类型换为pageinfo    pageinfo的构造方法中，填入了list后，可以将数据保存在pageinfo的对象中,对数据
     * 的遍历时在jsp 中对于ajax得到的返回值使用    。list  即可以使用each进行遍历
     * 对于当前页，上一页和下一页，首尾页都可以直接使用属性的方式直接引用
     *
     * 在下面的写法中不需要进行foreach遍历     再将pageinfo构造方法中传入数据的list对象后直接返回pageinfo的变量即可
     */
    public List<Customer> showALLOrByCondition(int currentpage, String keyindex, String keyCode, String num) {

        CustomerExample customerExample = new CustomerExample();
        PageHelper.startPage(currentpage,2);

        if (!keyindex.equals("0") && keyCode!=null && !num.equals("0")){
            //均不为空
            if (keyindex.equals("1")){
                //公司名称
                customerExample.createCriteria().andComnameLike(keyCode);
            }else{
                //联系人名字
                customerExample.createCriteria().andCompanypersonLike(keyCode);
            }
            customerExample.setOrderByClause("id desc");

            List<Customer> customers = customerMapper.selectByExample(customerExample);
            PageInfo<Customer> info = new PageInfo<Customer>(customers);
            int pageNum = info.getPageNum();
            int pages = info.getPages();
            int prePage = info.getPrePage();
            if (prePage<1){
                prePage=1;
            }
            int nextPage = info.getNextPage();
            if (nextPage==0){
                nextPage=pages;
            }

            for (Customer c: customers) {
                c.setCusPrePage(prePage);
                c.setCusNextPage(nextPage);
                c.setCusCurrentPage(pageNum);
                c.setCusTotalPage(pages);
            }

            return  customers;
        } else if (!keyindex.equals("0") && keyCode !=null){
            //没有选择排序
            if (keyindex.equals("1")){
                customerExample.createCriteria().andComnameLike(keyCode);
            }else{
                customerExample.createCriteria().andCompanypersonLike(keyCode);
            }
            customerExample.setOrderByClause("id desc");

            List<Customer> customers = customerMapper.selectByExample(customerExample);             //分页
            PageInfo<Customer> info = new PageInfo<Customer>(customers);    //类似于初始化的过程
            int pageNum = info.getPageNum();            //当前页
            int totalpage = info.getPages();                //总页数
            int prePage = info.getPrePage();                //上一页
            if (prePage<1){
                prePage=1;
            }
            int nextPage = info.getNextPage();              //下一页
            if (nextPage==0){
                nextPage=totalpage;
            }

            for (Customer c: customers) {                   //将信息带入到页面，但页面实际只使用了第一条数据中的信息?
                c.setCusCurrentPage(pageNum);
                c.setCusTotalPage(totalpage);
                c.setCusPrePage(prePage);
                c.setCusNextPage(nextPage);
            }

            return  customers;
        }else if (!num.equals("0")){
            //只选择了排序
            customerExample.setOrderByClause("id desc");  //在这里返回值的列表结果被反向填入，要实现页面中的升序排列，需要按desc降序装入

            List<Customer> customers = customerMapper.selectByExample(customerExample);
            PageInfo<Customer> info = new PageInfo<Customer>(customers);         //由于上面设置了开始页和页容量，这里直接传入数据的集合
            //当前页
            int pageNum = info.getPageNum();
            //总页数
            int totalpage = info.getPages();
            //前一页
            int prePage = info.getPrePage();
            if(prePage<1){
                prePage=1;
            }
            //下一页
            int nextPage = info.getNextPage();
            if (nextPage==0){
                nextPage=totalpage;
            }

            for (Customer c: customers) {
                c.setCusPrePage(prePage);
                c.setCusNextPage(nextPage);
                c.setCusCurrentPage(pageNum);
                c.setCusTotalPage(totalpage);
            }
            return customers;
        }else {
            //没有任何条件
            List<Customer> customers = customerMapper.selectByExample(null);
            PageInfo<Customer> info = new PageInfo<Customer>(customers);

            //上一页
            int prePage = info.getPrePage();
            if (prePage<1){
                prePage=1;
            }
            //总页数
            int totalPages = info.getPages();
            //下一页
            int nextPage = info.getNextPage();
            if (nextPage==0){
                nextPage=totalPages;
            }
            //当前页
            int pageNum = info.getPageNum();

            for (Customer c: customers) {
                c.setCusNextPage(nextPage);
                c.setCusPrePage(prePage);
                c.setCusCurrentPage(pageNum);
                c.setCusTotalPage(totalPages);
            }
            return customers;
        }
    }

    /**
     * 添加新的客户
     * @param customer
     */
    public void addCustomer(Customer customer) throws ParseException {
        DateFormat df = new SimpleDateFormat("y-M-d");
        String format = df.format(new Date());
        Date parse = df.parse(format);
        customer.setAddtime(parse);

        customerMapper.insert(customer);
    }
}
