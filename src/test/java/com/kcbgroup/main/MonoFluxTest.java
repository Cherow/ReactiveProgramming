package com.kcbgroup.main;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

/**
 * @ AUTHOR MKOECH
 **/
public class MonoFluxTest {
    @Test
    public void testMono(){
        Mono<?> stringMono = Mono.just(1)
                .then(Mono.error(new RuntimeException("Exception Occurred")))
                .log();

//        invoke the  subscribe method
        stringMono.subscribe(System.out::println
               ,(p)-> System.out.println(p.getMessage()));
    }

    @Test
    public void fluxTest(){
        Flux<?> flux = Flux.just("mercy","neph","ruth")
                .concatWithValues("AWS","Spring boot")
//                .concatWith(Flux.error(new RuntimeException("Exception Occurred in Flux")))
                .concatWithValues("Azure","Spring")

                .log();
        flux.subscribe(System.out::println);
        System.out.println(IntStream.rangeClosed(1,50).map(i -> i*i).count());


    }

}



