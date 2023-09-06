package com.reactive.udemy.intro.sec06_Multithreading;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Schedulers;

public class Lec3MultiSubOn {
    public static void main(String[] args) {
        // closer to publisher will take precedence
        schedulerthread();
    }

    public static void schedulerthread() {
        Flux.generate(() -> 1, (Integer state, SynchronousSink<Integer> sink) -> {
            Utility.printThread("flux generate");
            sink.next(state);
            state++;
            return state;
        })
                .take(5)
                .doFirst(() -> Utility.printThread("dofirst"))

                .subscribeOn(Schedulers.parallel()) // parallel 2 //as the subscribeOn is closest to publisher this thread will be used
                .doOnSubscribe((sub) -> Utility.printThread("on subscribe"))
                .subscribeOn(Schedulers.boundedElastic()) // bounded 1
                // sub from donw to up so new thread will be assingned upwards
                .map(data -> {
                    Utility.printThread("map");
                    return data;
                })
                .doFirst(() -> Utility.printThread("i hope main ")) // main
                .doFinally((signal) -> Utility.printThread("finally"))
                .subscribe(Utility.subscriber());
        Utility.threadSleep(4);
    }

}
