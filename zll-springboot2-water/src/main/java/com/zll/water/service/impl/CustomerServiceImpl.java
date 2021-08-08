package com.zll.water.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zll.water.entities.Customer;
import com.zll.water.mapper.CustomerMapper;
import com.zll.water.mapper.HistoryMapper;
import com.zll.water.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    /*
    @Autowired 根据类型自动装配CustomerMapper
     */
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public PageInfo<Customer> listCustomerForPage(Integer pageNum) {
        // 分页的核心：从第pageNum页开始，每页显示3条记录
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Customer> list = this.listCustomer();
        // 分页Bean，封装了分页查询的数据，将查询结果注入到分页对象(Bean)
        PageInfo<Customer> pageInfo =  new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Customer> listCustomer() {
        return customerMapper.listCustomer();
    }

    @Override
    public List<Customer> searchCustomer(String custName) {
        return customerMapper.searchCustomer(custName);
    }

    @Override
    public PageInfo<Customer> searchCustomerForPage(Integer pageNum, String custName) {
        PageHelper.startPage(pageNum, PAGE_SiZE);
        List<Customer> custList = this.searchCustomer(custName);
        PageInfo<Customer> pageInfo = new PageInfo<>(custList);
        return pageInfo;
    }

    @Override
    public int saveCustomer(Customer customer) {
        return customerMapper.saveCustomer(customer);
    }

    @Override
    public int deleteCustomerById(Integer cid) {
        if (historyMapper.selectHistoryByCid(cid) > 0) {
            return 0;
        }
        return customerMapper.deleteCustomerById(cid);
    }

    @Override
    public Customer getCustomerById(Integer cid) {
        return customerMapper.getCustomerById(cid);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,Error.class})
    public int batchDeleteCustomer(String ids) {
        // 处理前端传来的ids字符串
        ids = ids.replaceFirst(",", "");
        String[] split = StrUtil.split(ids, ",");
        List<Integer> idList = new ArrayList<>();
        for (String s : split) {
            idList.add(Integer.parseInt(s));
        }
        return customerMapper.batchDeleteCustomer(idList);
    }
}
