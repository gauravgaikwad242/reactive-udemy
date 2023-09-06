package com.reactive.udemy.intro.sec06_Multithreading;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Schedulers;

public class Lec2Schedulers {
    public static void main(String[] args) {
        //bounded elastic - network i/o timetaking -  4 core 40 thread
        //parallel - cpu intensive - 4 core 4 thread
        //single - as single dedicated thread for on off tasks
        //immediate - current thread

        // Operators
        //subscribeOn - upstream
        //publishOn - downstream


        // mainthread();
        // schedulerthread();
        schedulerthread1();
        
    }

    public static void mainthread() {
        Flux.range(0, 40)
                .take(5)
                .doFirst(() -> Utility.printThread("dofirst"))
                .map(data -> {
                    Utility.printThread("map");
                    return data + 3;
                })
                .doFinally((signal) -> Utility.printThread("finally"))
                .subscribe(Utility.subscriber());
    }

    public static void schedulerthread() {
        Flux.generate(() -> 1, (Integer state, SynchronousSink<Integer> sink) -> {
            Utility.printThread("flux generate");
            sink.next(state);
            state++;
            return state;
        })
                .subscribeOn(Schedulers.boundedElastic())
                .take(5)
                .doFirst(() -> Utility.printThread("dofirst")) //main //HERE AS sub comes from down to up , while pipeline building
                                                                    //the subOn is noticed later hence this is in main thread
                .doOnSubscribe((sub) -> Utility.printThread("on subscribe")) //main
                .map(data -> {
                    Utility.printThread("map"); //bounded elastic
                    return data;
                })
                .doFinally((signal) -> Utility.printThread("finally"))
                .subscribe(Utility.subscriber());
        Utility.threadSleep(4);
    }
    public static void schedulerthread1() {
        Flux.generate(() -> 1, (Integer state, SynchronousSink<Integer> sink) -> {
            Utility.printThread("flux generate");
            sink.next(state);
            state++;
            return state;
        })
                
                .take(5)
                .doFirst(() -> Utility.printThread("dofirst"))  //at very first
                .doOnSubscribe((sub) -> Utility.printThread("on subscribe")) //when subscription happens
                .subscribeOn(Schedulers.boundedElastic()) //here when code will run from down to up for building pipeline it will
                                                        //notice this subscribeon and immediately change the thread - WHILE BUILDING
                .map(data -> {
                    Utility.printThread("map"); //bounded elastic
                    return data;
                })
                .doFinally((signal) -> Utility.printThread("finally"))
                .subscribe(Utility.subscriber());
        Utility.threadSleep(4);
    }
}
