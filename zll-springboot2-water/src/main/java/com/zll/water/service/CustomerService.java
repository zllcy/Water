package com.zll.water.service;

import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Customer;

import java.util.List;

public interface CustomerService {
    /**
     * 每页数量
     */
    int PAGE_SiZE = 3;

    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    PageInfo<Customer> listCustomerForPage(Integer pageNum );

    List<Customer> listCustomer();

    List<Customer> searchCustomer(String custName);

    /**
     * 表单搜索分页
     * @param pageNum 当前页码
     * @param custName 客户名称
     * @return 分页对象
     */
    PageInfo<Customer> searchCustomerForPage(Integer pageNum,String custName);

    int saveCustomer(Customer customer);

    int deleteCustomerById(Integer cid);

    Customer getCustomerById(Integer cid);

    int updateCustomer(Customer customer);

    int batchDeleteCustomer(String ids);
}
