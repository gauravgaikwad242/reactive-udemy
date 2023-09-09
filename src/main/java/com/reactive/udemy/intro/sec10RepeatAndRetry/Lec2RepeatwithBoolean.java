package com.reactive.udemy.intro.sec10RepeatAndRetry;

import java.util.concurrent.atomic.AtomicInteger;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec2RepeatwithBoolean {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {

        getInt()
        .repeat(()-> atomicInteger.get() < 20) //if the integeris  less than 10 repeat
        .subscribe(Utility.subscriber());
        
    }

    private static Flux<Integer> getInt(){
        return Flux.range(1,3)
                    .doOnSubscribe((sub)->System.out.println("Subscribed"))
                    .doOnComplete(()->System.out.println("-- sub completed --"))
                    .map(i -> atomicInteger.getAndIncrement());

    }
}
