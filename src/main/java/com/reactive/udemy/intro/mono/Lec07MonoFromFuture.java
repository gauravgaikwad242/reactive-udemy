package com.reactive.udemy.intro.mono;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class Lec07MonoFromFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //to run as completable futures
        runFutures();
//runFutureWithMono();

    }

    public static void runFutureWithMono(){
        System.out.println("mono with future start");
        Mono<String> mono1 = Mono.fromFuture(getFuture(5));
        Mono<String> mono2 = Mono.fromFuture(getFuture(2));
        Mono<String> mono3 = Mono.fromFuture(getFuture(3));
        Consumer<String> consumer = (e)->{
            System.out.println("future data recieved"+e);
        };
        mono1.subscribe(consumer);
        mono2.subscribe(consumer);
        mono3.subscribe(consumer);
        System.out.println("mono with future end");
        Utility.threadSleep(5);
    }

    public static void runFutures(){
        CompletableFuture<String> future1 = getFuture(5);
        CompletableFuture<String> future2 = getFuture(2);
        CompletableFuture<String> future3 = getFuture(3);
        future1.thenAcceptAsync((str)->{
            System.out.println("completed"+str);
        });
        future2.thenAcceptAsync((str)->{
            System.out.println("completed"+str);
        });
        future3.thenAcceptAsync((str)->{
            System.out.println("completed"+str);
        });
        CompletableFuture.allOf(future1,future2,future3).join();
    }
    public static CompletableFuture<String> getFuture(int index){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("future execution started "+index);
            Utility.threadSleep(index);
            return "Successfully returned data!"+index;
        });
    }

}
