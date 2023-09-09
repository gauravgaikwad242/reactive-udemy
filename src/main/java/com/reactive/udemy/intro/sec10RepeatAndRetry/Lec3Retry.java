package com.reactive.udemy.intro.sec10RepeatAndRetry;

import java.util.concurrent.atomic.AtomicInteger;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec3Retry {

    private static AtomicInteger atomicInteger = new AtomicInteger(-1);

     public static void main(String[] args) {
  
        //WRONG EXAMPLE
        //BUT IT RETRIES ON ERRROR


        getInt()
        .retry(2)
        .subscribe(Utility.subscriber());

    }

    private static Flux<Integer> getInt() {
        return Flux.range(1, 5)
                .doOnSubscribe((sub) -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("-- sub completed --"))
                .map(i -> 5/ atomicInteger.getAndIncrement() );

    }
}
