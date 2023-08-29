package com.reactive.udemy.intro.mono;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lec09Assignment {
    public static void main(String[] args) {
    readFileAsync("src/main/resources/files/file1.txt").subscribeOn(Schedulers.boundedElastic()).subscribe(Utility.onNext());
    readFileAsync("src/main/resources/files/file1.txt").subscribeOn(Schedulers.boundedElastic()).subscribe(Utility.onNext());
    readFileAsync("src/main/resources/files/file1.txt").subscribeOn(Schedulers.boundedElastic()).subscribe(Utility.onNext());
    }

    public static Mono<String> readFileAsync(String path){
        return Mono.fromSupplier(()->{
            String data;
            try {
                System.out.println("started reading files"+"\n"+path);
                 data = Files.readString(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return data;
        });
    }
}
