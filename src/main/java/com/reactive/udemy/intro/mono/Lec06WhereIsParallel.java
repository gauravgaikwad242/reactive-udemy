package com.reactive.udemy.intro.mono;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec06WhereIsParallel {
    public static void main(String[] args) {
        // synchronousLogs(); //here logs will be synchnous
        asyncLogs();
        Utility.threadSleep(3);
    }

    public static Mono<String> logs() {
        System.out.println("method Execution started");
        return Mono.fromSupplier(() -> {
            System.out.println("method executing");
            Utility.threadSleep(2);
            System.out.println("method execution Finished");
            return "Rings of Power";
        });
    }

    public static void synchronousLogs() {
        // synchronous and logs will be called one by one
        logs().subscribe();
        logs().subscribe();
        logs().subscribe();
    }

    public static void asyncLogs() {
        // asynchronous and logs will be called all at on
        logs()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
        logs().subscribeOn(Schedulers.boundedElastic()).subscribe();
        logs().subscribeOn(Schedulers.boundedElastic()).subscribe();

    }
}
