package com.kcbgroup.main.dao;

import com.kcbgroup.main.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ AUTHOR MKOECH
 **/
@Component
public class CustomerDao {

    public static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getCustomers()  {
        return IntStream.rangeClosed(1,10)
                .peek(i -> System.out.println("processing count  : " + i))
                .peek(CustomerDao::sleepExecution)
                .mapToObj(i -> Customer.builder().id(i).name("Customer" + i).build()).collect(Collectors.toList());



    }


    public Flux<Customer> getCustomersStreams()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count stream flow: " + i))

                .map(i -> Customer.builder().id(i).name("customer" + i).build()).log();



    }


    public Flux<Customer> getCustomersList()  {
        return Flux.range(1,10)
                .doOnNext(i -> System.out.println("processing count stream flow: " + i))
                .map(i -> Customer.builder().id(i).name("customer" + i).build()).log();



    }



}
