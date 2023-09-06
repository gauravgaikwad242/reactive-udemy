package com.reactive.udemy.intro.sec06_Multithreading;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Schedulers;

public class Lec4PublishOn {
    public static void main(String[] args) {
        /*
         * publish on is for downstream
         * that means when pipeline is build and the publisher starts publishing
         * it will notice in the path that there is publishOn so it will change the thread in way down
         * unlike subOn which changes thread on wayUp 
         */

        // schedulerthread();
        multiPublishOn();
    }

    public static void schedulerthread() {
        Flux.generate(() -> 1, (Integer state, SynchronousSink<Integer> sink) -> {
            Utility.printThread("flux generate");
            sink.next(state);
            state++;
            return state;
        })
                .take(5)
                .map(data -> {
                    Utility.printThread("map in main"); //main
                    return data;
                })
                .doFirst(() -> Utility.printThread("dofirst"))
                .doOnSubscribe((sub) -> Utility.printThread("on subscribe"))
                .publishOn(Schedulers.boundedElastic())
                .map(data -> {
                    Utility.printThread("map in scheduler"); //elastic
                    return data;
                })
                .doFirst(() -> Utility.printThread("i hope main ")) // main
                .doFinally((signal) -> Utility.printThread("finally"))
                .subscribe(Utility.subscriber());
        Utility.threadSleep(4);
    }


    public static void multiPublishOn() {
        Flux.generate(() -> 1, (Integer state, SynchronousSink<Integer> sink) -> {
            Utility.printThread("flux generate");
            sink.next(state);
            state++;
            return state;
        })
                .take(5)
                .map(data -> {
                    Utility.printThread("map in main"); //main
                    return data;
                })
                .doFirst(() -> Utility.printThread("dofirst"))
                .doOnSubscribe((sub) -> Utility.printThread("on subscribe"))
                .publishOn(Schedulers.boundedElastic())
                .map(data -> {
                    Utility.printThread("map in scheduler"); //elastic
                    return data;
                })
                .publishOn(Schedulers.parallel())
                .map(data -> {
                    Utility.printThread("map in parallel"); //parallel //all the code after this will be in parallel thread
                    return data + 1;
                })
                .doFirst(() -> Utility.printThread("i hope elastic ")) // elastic
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> Utility.printThread("i hope main ")) // main
                .doFinally((signal) -> Utility.printThread("finally"))
                .subscribe(Utility.subscriber());
        Utility.threadSleep(4);
    }
}
