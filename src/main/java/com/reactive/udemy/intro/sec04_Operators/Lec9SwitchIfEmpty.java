package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec9SwitchIfEmpty {
    //for fallback mechanism if flux is empty
    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(i -> i>10)
                .switchIfEmpty(fallback()) //this value will be published
                .subscribe(Utility.subscriber());
    }
    public static Flux<Integer> fallback(){
        return Flux.range(20,2);
    }
}
