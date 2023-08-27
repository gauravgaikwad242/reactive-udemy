package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1)).subscribe(Utility.onNext());
        Utility.threadSleep(5);
    }
}
