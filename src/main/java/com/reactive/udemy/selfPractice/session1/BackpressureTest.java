package com.reactive.udemy.selfPractice.session1;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

public class BackpressureTest {
    public static void main(String[] args) {
        System.out.println("started -------------------------------------");
        Flux.create((FluxSink<Integer> fluxSink)->{
            fluxSink.next(1);
                    fluxSink.next(531);
                    fluxSink.next(4515);
                    fluxSink.next(631);
                    fluxSink.next(21635);
                    fluxSink.next(21236);
                    fluxSink.complete();
                })
//                .subscribeOn(Schedulers.boundedElastic())
//                .delayElements(Duration.ofMillis(50))
                .subscribe(new DefaultSubscriber<Integer>());
        System.out.println("finished ------------------------------------");
        Utility.threadSleep(20);
    }
}
