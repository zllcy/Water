package com.zll.water.mapper;

import com.zll.water.entities.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerMapper {
    /**
     获取所有客户信息
     */
    List<Customer> listCustomer();

    /**
     * @Param("custName") 不能删除
     * @param custName
     * @return
     */
    List<Customer> searchCustomer(@Param("custName") String custName);

    int saveCustomer(Customer customer);

    int deleteCustomerById(Integer cid);

    /**
     * 用于客户回显信息
     */
    Customer getCustomerById(Integer cid);

    int updateCustomer(Customer customer);

    int batchDeleteCustomer(@Param("idList") List<Integer> idList);

    /**
     * 查询客户拥有的水票数量
     * @param cid
     * @return
     */
    @Select("select cust_ticket from customer where cid = #{cid}")
    Integer selectCustomerTicket(Integer cid);
}
