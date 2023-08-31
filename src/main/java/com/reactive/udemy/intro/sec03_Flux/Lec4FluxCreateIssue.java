package com.reactive.udemy.intro.sec03_Flux;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec4FluxCreateIssue {
    public static void main(String[] args) {

        //TODO : REWATCH THIS LECTURE
        //we use only one instance of fluxsink
        //here not able to reproduce the issue
        //but basically fluxsink keeps on emmiting data until it is completed
        //even if take only 3 items
        Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        })
        .take(3)
        .subscribe(Utility.onNext());
    }
}
