package com.reactive.udemy.intro.mono;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class Lec04MonoEmptyOrError {
    public static void main(String[] args) {

        userNameRepository(3).subscribe(
                data -> {
                    System.out.println("user fetched successfully: " + data);
                },
                error -> {
                    System.out.println("error occurred" + error);
                },
                () -> {
                    System.out.println("operation completed");
                });
    }

    public static Mono<String> userNameRepository(int id) {
        Map<Integer, Mono<String>> monoMap = new HashMap<>();
        monoMap.put(1, Mono.just("Samir Patel"));
        monoMap.put(2, Mono.empty());
        monoMap.put(3, Mono.error(new RuntimeException("failed to fetch user with id " + id)));
        return monoMap.get(id);
    }
}
