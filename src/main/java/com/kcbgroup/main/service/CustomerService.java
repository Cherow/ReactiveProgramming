package com.kcbgroup.main.service;

import com.kcbgroup.main.dao.CustomerDao;
import com.kcbgroup.main.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @ AUTHOR MKOECH
 **/
@Service

public class CustomerService {

    private final CustomerDao dao;

    @Autowired
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }


    public List<Customer> getCustomers()  {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Time Execution   " +(end-start));
        return customers;

    }



    public Flux<Customer> getCustomersStreams()  {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStreams();
        long end = System.currentTimeMillis();
        System.out.println("Time Execution streams  " +(end-start));
        return customers;

    }





}
