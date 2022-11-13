package com.kcbgroup.main.router;

import com.kcbgroup.main.handler.CustomerHandler;
import com.kcbgroup.main.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @ AUTHOR MKOECH
 **/
@Configuration
public class RouterConfig {

    private final CustomerHandler customerHandler;
    private final CustomerStreamHandler customerStreamHandler;
    @Autowired
    public RouterConfig(CustomerHandler customerHandler,CustomerStreamHandler customerStreamHandler) {
        this.customerHandler = customerHandler;
        this.customerStreamHandler = customerStreamHandler;
    }
@Bean
    public RouterFunction<ServerResponse>routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customer",customerHandler::loadCustomer)
                .GET("/router/customer/stream",customerStreamHandler::loadcustomerStreams)
                .GET("/route/customer/stream/{id}",customerHandler::getCustomer)
                .POST("/router/customer/save",customerHandler::saveCustomer)
                .build();
    }
}
