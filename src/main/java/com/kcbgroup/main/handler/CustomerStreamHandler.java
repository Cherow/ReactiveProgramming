package com.kcbgroup.main.handler;

import com.kcbgroup.main.dao.CustomerDao;
import com.kcbgroup.main.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @ AUTHOR MKOECH
 **/
@Service
public class CustomerStreamHandler {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerStreamHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> loadcustomerStreams(ServerRequest request){
        Flux<Customer> customerStream = customerDao.getCustomersStreams();
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerStream,Customer.class);

    }
}
