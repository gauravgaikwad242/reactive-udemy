package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec6OnError {
    public static void main(String[] args) {
        Flux.range(1,10)
                .log()
                .map(i -> 10/(5-i)) // so we will get error when 10/0
//                .onErrorReturn(-1) //returns -1 when error occurs and STOPS

//                .onErrorResume(e -> {
//                    System.out.println("error occured "+e.getMessage());
//                    return fallback(); //here we can write fall back logic
//                }) //also stops on error STOPS

                .onErrorContinue((throwable, o) -> {
                    System.out.println("error happened"+ throwable.getMessage());
                    System.out.println("___because of the published value is ____"+o);
                })
                .subscribe(Utility.subscriber());
    }
    public static  Mono<Integer> fallback(){
        return Mono.fromSupplier(()-> -1);
    }
}
