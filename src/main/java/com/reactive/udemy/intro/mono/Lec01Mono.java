package com.reactive.udemy.intro.mono;

import reactor.core.publisher.Mono;

public class Lec01Mono {
    public static void main(String[] args) {
        // ways to create a mono

        subscriber();

    }

    public static void fromJust() {
        // basic way
        // use only when the data is directly available
        // dont use if it is coming from a method, as the method will be processed first
        Mono<Integer> mono = Mono.just(1);
    }

    public static Mono<String> toSubscribe() {
        return Mono.just("MegaMind");
    }

    public static void subscriber() {
        // toSubscribe().subscribe();
        toSubscribe().subscribe(
                // onNext :
                data -> {
                    System.out.println(data);
                },
                // onError
                error -> {
                    System.out.println(error);
                },
                // onComplete
                () -> {
                    System.out.println("completed");
                });
    }
}
