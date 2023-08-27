package com.reactive.udemy.intro.sec03_Flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec1FluxCreate {
    public static void main(String[] args) {
        //utility to create subscriber object

        //we can use create for custom logic like running commands or any logic which we will add in method
        getFlux();
        //TODO: WHAT IS SINK
        //TODO: WHAT IS THREADSAFE
    }

    public static void getFlux(){
        Flux.create(fluxSink -> {
           fluxSink.next("Emmitting data");
           fluxSink.next("Emmitting data");
           fluxSink.complete();
        }).subscribe(Utility.subscriber());
    }


}
