package com.reactive.udemy.intro.sec03_Flux;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec7FluxGenerateCounter {
    public static void main(String[] args) {
        //state of generate 
        //state is required as we use new instance for every generate emittion
        Flux.generate(()-> 1, (state, sink)->{
            sink.next(state);
            state++;
            return state;
        })
        .take(5)
        .subscribe(Utility.onNext());
    }
}
