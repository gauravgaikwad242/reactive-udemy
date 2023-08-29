package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec03MultiSubscriber {
    public static void main(String[] args) {
        Flux<Integer> intFlux = Flux.just(1,2,3,4,5,6,7);
        Flux<Integer> evenFlux = intFlux.filter((in)-> in % 2 == 0);
        intFlux.subscribe(Utility.onNext());
        evenFlux.subscribe(Utility.onNext());
    }
}
