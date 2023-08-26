package com.reactive.udemy.intro.mono;

import reactor.core.publisher.Mono;

public class Lec06WhyNotUseJust {

    public static void main(String[] args) {

        // if we use just to getUser the method will start execution even though we are
        // not consuming
        Mono<Mono<String>> mono = Mono.just(getUser());

        // instead if we use supplier we will not get logs from method as it not getting
        // executed
        Mono<Mono<String>> mono1 = Mono.fromSupplier(() -> getUser());

        // so use Just only when data is readily available
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<String> getUser() {
        System.out.println("method execution started");
        return Mono.fromSupplier(() -> {
            System.out.println("goint to fetch data");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "admin123";

        });
    }
}
