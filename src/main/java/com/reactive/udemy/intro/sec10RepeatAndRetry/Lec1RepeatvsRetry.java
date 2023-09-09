package com.reactive.udemy.intro.sec10RepeatAndRetry;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec1RepeatvsRetry {
    /*
     * repeat : resubscribin after complete
     * retry : resub after error
     * 
     * REAPEAT
     */
    public static void main(String[] args) {
        
        getIntFlux()
        .repeat(2) //this will subscribe 2 more times so totally 3 times
        .subscribe(Utility.subscriber());

    }
    private static Flux<Integer> getIntFlux(){
        return Flux.range(1,3)
                    .doOnSubscribe((sub)->System.out.println("Subscribed"))
                    .doOnComplete(()->System.out.println("-- sub completed --"));
    }
}
