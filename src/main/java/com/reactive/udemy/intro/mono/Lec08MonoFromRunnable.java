package com.reactive.udemy.intro.mono;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalTime;

public class Lec08MonoFromRunnable {
    public static void main(String[] args) {

        //can be use to run time consuming processes
        //like promises used to write files or to run commands in parallel
        Mono<Object> mono1 = Mono.fromRunnable(getRunnableOFTimeConsumingProcess()).subscribeOn(Schedulers.boundedElastic());
        Mono<Object> mono2 = Mono.fromRunnable(getRunnableOFTimeConsumingProcess()).subscribeOn(Schedulers.boundedElastic());
        Mono<Object> mono3 = Mono.fromRunnable(getRunnableOFTimeConsumingProcess()).subscribeOn(Schedulers.boundedElastic());

        mono1.subscribe(Utility.onNext());
        mono2.subscribe(Utility.onNext());
        mono3.subscribe(Utility.onNext());
        Utility.threadSleep(7);
    }

    public static Runnable getRunnableOFTimeConsumingProcess(){
        return ()->{
            System.out.println("running for loop");
            int k = 0;
            for (int i = 0; i <=5 ; i++) {
                Utility.threadSleep(1);
                k=+i;
            }
            System.out.println(k+" time "+ LocalTime.now());
        };
    }
}
