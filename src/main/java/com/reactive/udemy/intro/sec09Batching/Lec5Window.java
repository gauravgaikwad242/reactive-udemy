package com.reactive.udemy.intro.sec09Batching;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec5Window {
    /*
     * like buffer but instead of returning list it returns flux
     */

     private static AtomicInteger atomicInteger = new AtomicInteger(1);

         public static void main(String[] args) {

        Flux<String> strFlux = eventStream(); // string

        //WE CAN USE DURATION LIKE BUFFER WITH WINDOW ALSO
        Flux<Flux<String>> listHybridFlux = strFlux.window(5); // here we will get last 3 items
        listHybridFlux
        .flatMap(data -> saveDataToDB(data))
        .subscribe(Utility.subscriber());

            Utility.threadSleep(50);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event :: " + i);
    }

    private static Mono<Integer> saveDataToDB(Flux<String> flux){
        return flux
        .doOnNext((next)->{
            System.out.println("saving data to db : "+ next);
        })
        .doOnComplete(()-> {System.out.println("Flux batch saved to db successfully");})
        .then(Mono.just(atomicInteger.getAndIncrement())); //then is used to indicate complete signal .then()

    }
}
