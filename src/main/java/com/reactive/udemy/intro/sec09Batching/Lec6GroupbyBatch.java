package com.reactive.udemy.intro.sec09Batching;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec6GroupbyBatch {

    public static void main(String[] args) {
        Flux.range(1, 30)
        .groupBy(i -> i%2)
        .subscribe(gf -> process(gf, gf.key()));
    
        Utility.threadSleep(3);
    }
    
    private static void process(Flux<Integer> flux, int key){
        System.out.println("method called only 2 times "+key);
        flux.subscribe(i -> System.out.println("key : "+key+" item : "+i));
    }
}
