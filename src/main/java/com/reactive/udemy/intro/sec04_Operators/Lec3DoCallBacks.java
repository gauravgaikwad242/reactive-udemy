package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.stream.Stream;

public class Lec3DoCallBacks {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------");
        Flux.create((FluxSink<Integer> fluxSink) -> {
            Stream.of(1,2,3,4,5,6,78,8,9,0)
                    .forEach(x -> fluxSink.next(x));
                    fluxSink.complete();
        })
                .doOnComplete(()-> System.out.println("is map do on complete completes before later"))
                .map(x-> x+1)
                .doOnComplete(()->{
                    System.out.println("this log from dooncomplete; this runs before onComplete from subsciption");
                })
                .subscribe(Utility.subscriber());

        System.out.println("----------------------------------------------------------------");

        Flux.create((FluxSink<Integer> fluxSink) -> {
            Stream.of(1,2,3,4,5,6,78,8,9,0)
                    .forEach(x -> fluxSink.next(x));
            fluxSink.complete();
        })
                .doFirst(()->System.out.println("1 do fist - runs fist time on subscription"))
                .doFirst(()-> System.out.println("2 do first"))
                .doFirst(()-> System.out.println("3 od fist - this one runs fist as subscirption starts from bottom"))
                .subscribe(Utility.subscriber());

        System.out.println("----------------------------------------------------------------");

        Flux.create((FluxSink<Integer> fluxSink) -> {
            Stream.of(1,2,3,4,5,6,78,8,9,0)
                    .forEach(x -> fluxSink.next(x));
            fluxSink.complete();
        })
                .doOnNext(i -> System.out.println("on next triggered "+ i))
                .doOnSubscribe((i)-> System.out.println("subscription started "+i))
                .subscribe(Utility.subscriber());

        System.out.println("----------------------------------------------------------------");

        Flux.create((FluxSink<Integer> fluxSink) -> {
            Stream.of(1,2,3,4,5,6,78,8,9,0)
                    .forEach(x -> fluxSink.next(x));
            fluxSink.complete();
        })
                .doOnTerminate(()-> System.out.println("do on terminate 1"))
                .doOnTerminate(()-> System.out.println("do on terminate 2"))
                .subscribe(Utility.subscriber());

    }
}
