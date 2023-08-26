package com.reactive.udemy.intro.mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplier {
    public static void main(String[] args) {

    }

    public static void fromSupplier() {
        // supplier is something which doesnt take argument
        // but returns data
        Supplier<String> supplier = () -> "Hobbits";
        Mono.fromSupplier(supplier);

        // alternate syntax
        Mono.fromSupplier(() -> {
            return "Lord of Rings";
        });

        // similarly we have callable
        Callable<String> stringCallable = () -> "Larry";
        Mono.fromCallable(stringCallable)
                .subscribe(
                        (data) -> System.out.println(data));
    }
}
