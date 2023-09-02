package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec5Delay {
    public static void main(String[] args) {

        Flux.range(1,100) //32
                .log()
                .delayElements(Duration.ofMillis(1))
                .subscribe(Utility.subscriber());

        Utility.threadSleep(10);
    }
}
