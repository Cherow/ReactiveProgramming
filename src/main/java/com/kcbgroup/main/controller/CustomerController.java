package com.kcbgroup.main.controller;

import com.kcbgroup.main.dto.Customer;
import com.kcbgroup.main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.List;

/**
 * @ AUTHOR MKOECH
 **/
@RequestMapping("/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;
@Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping()
    public List<Customer> getCustomers()  {
    return customerService.getCustomers();

    }


    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersStreams()  {
        return customerService.getCustomersStreams();

    }
}
