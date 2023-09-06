package com.reactive.udemy.intro.sec06_Multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec6ParallelExecution {
    /*
     * PARALLEL
     * RUNON
     */
    public static void main(String[] args) {
        /*
         * by default the task is switched to new thread and executed sequentially
         * 
         * we have to explicitly make the task parallel
         */
        // parallelRange();
        parallelToSequential();
        arrayListNotThreadSafe();
        makeArrayListThreadSafe();
    }

    public static void parallelRange() {
        Flux.range(0, 20)
                .doFirst(() -> Utility.printThread("Before Subcription"))
                .parallel(10)
                .runOn(Schedulers.boundedElastic())
                .doOnNext((next) -> Utility.printThread("On next" + next))
                .subscribe(Utility.subscriber());

        Utility.threadSleep(4);
    }
    public static void parallelToSequential() {
        Flux.range(0, 20)
                .doFirst(() -> Utility.printThread("Before Subcription"))
                .parallel(10)
                .runOn(Schedulers.boundedElastic())
                .map((d)->{
                    Utility.printThread("parallel threads");
                    return d;
                })
                .sequential() // HERE THE STREAM IS MADE SEQUENTIAL AGAIN
                .doOnNext((next) -> Utility.printThread("On next" + next))
                .subscribe(Utility.subscriber());

        Utility.threadSleep(4);
    }

    public static void arrayListNotThreadSafe() {
        List<Integer> list = new ArrayList<>();
        Flux.range(0, 2000)
                .doFirst(() -> Utility.printThread("Before Subcription"))
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe((data)->{
                    list.add(data);
                });

        Utility.threadSleep(4);
        System.out.println("size of arraylist "+list.size()); //size will not be 2000 less than 2000
    }
    public static void makeArrayListThreadSafe() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Flux.range(0, 2000)
                .doFirst(() -> Utility.printThread("Before Subcription"))
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe((data)->{
                    list.add(data);
                    // Utility.printThread("on subcribe");
                });

        Utility.threadSleep(4);
        System.out.println("size of arraylist "+list.size());
    }
}
