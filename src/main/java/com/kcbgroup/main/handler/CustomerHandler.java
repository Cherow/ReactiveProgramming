package com.kcbgroup.main.handler;

import com.kcbgroup.main.dao.CustomerDao;
import com.kcbgroup.main.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @ AUTHOR MKOECH
 **/
@Service
public class CustomerHandler {
    @Autowired
    private final CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public Mono<ServerResponse>loadCustomer(ServerRequest request){
        Flux<Customer> customerList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customerList,Customer.class);
    }


    public  Mono<ServerResponse> getCustomer(ServerRequest request){
       int customerId = Integer.parseInt(request.pathVariable("id")) ;
//       This returns a flux but since we want only a mono we set as a mono using .take.single() or .next
       customerDao.getCustomersList().filter(c -> c.getId() == customerId).take(1).single();

       Mono<Customer> customer = customerDao.getCustomersList().filter((c -> c.getId() ==customerId)).last();
       return ServerResponse.ok().body(customer,Customer.class);

    }

    public Mono<ServerResponse> saveCustomer (ServerRequest request){
        //since we dont have a db we print out the customer from the request
        Mono<Customer> customer = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customer.map(dto -> dto.getId() +" : " + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);

    }

}