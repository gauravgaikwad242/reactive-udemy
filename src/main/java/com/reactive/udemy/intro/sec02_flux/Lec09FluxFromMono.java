package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09FluxFromMono {
    public static void main(String[] args) {
        //mono to flux
        Mono<String> stringMono = Mono.just("Ranger");
        Flux<String> stringFlux = Flux.from(stringMono);
        stringFlux.subscribe(Utility.onNext());

        //flux to mono
        Mono<Integer> fluxtomono = Flux.range(0, 10).next(); //takes the first emmited
        Mono<Integer> fluxtomono1 = Flux.range(0, 10).filter(i -> i > 5).next(); //takes the first emmited
        fluxtomono.subscribe(Utility.onNext());
        fluxtomono1.subscribe(Utility.onNext());
    }
}
