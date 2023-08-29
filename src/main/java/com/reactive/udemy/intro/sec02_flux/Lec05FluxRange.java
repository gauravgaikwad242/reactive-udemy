package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.range(10,14); //starts at 10 and then next 14
        integerFlux.subscribe(Utility.onNext());
        /*
data received : data :- 10
data received : data :- 11
data received : data :- 12
data received : data :- 13
data received : data :- 14
data received : data :- 15
data received : data :- 16
data received : data :- 17
data received : data :- 18
data received : data :- 19
data received : data :- 20
data received : data :- 21
data received : data :- 22
data received : data :- 23
         */
    }
}
