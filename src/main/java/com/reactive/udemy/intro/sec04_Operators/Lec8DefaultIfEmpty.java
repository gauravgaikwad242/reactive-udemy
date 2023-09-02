package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec8DefaultIfEmpty {
    //to use when the an empty value can be produce
    //so to fallback on that
    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(i -> i>10)
                .defaultIfEmpty(-1) //this value will be published
                .subscribe(Utility.subscriber());
    }
}
