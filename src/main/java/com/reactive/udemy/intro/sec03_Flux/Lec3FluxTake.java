package com.reactive.udemy.intro.sec03_Flux;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec3FluxTake {
    public static void main(String[] args) {
        
        Flux.range(0, 10)
        .log()
        .take(3)
        .log()
        .subscribe(Utility.onNext());

    }
}
