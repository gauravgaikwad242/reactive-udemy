package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {
    public static void main(String[] args) {
        Flux<String> dataStream = Flux.just("abc","efg","hid");
        dataStream.subscribe(
                Utility.onNext()
        );
    }
}
