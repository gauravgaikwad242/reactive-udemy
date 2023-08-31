package com.reactive.udemy.intro.sec03_Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec5FluxGenerate {
    public static void main(String[] args) {
        //to solve the issue of create
        //each emmition of onnext will be new instance of consumable of synchronoussink

        Flux.generate(synchronoussink -> {
            synchronoussink.next(LocalDateTime.now());
        }).take(3)
        .subscribe(Utility.onNext());
    }
}
