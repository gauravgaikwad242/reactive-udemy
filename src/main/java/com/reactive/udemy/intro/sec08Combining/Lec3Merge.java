package com.reactive.udemy.intro.sec08Combining;

import java.time.Duration;
import java.util.stream.Stream;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

//TODO : didnt understand the diff between concatwith and mergewith
public class Lec3Merge {
    public static void main(String[] args) {
        Flux.merge(getNamesFlux(7),getNamesFlux(5),getNamesFlux(3))
        .subscribe(Utility.subscriber());

        Utility.threadSleep(10);
    }
    
    public static Flux<String> getNamesFlux(int index){
        return Flux.generate((syncSink)->{
            syncSink.next(index+" "+(int)(Math.random() * 10) + " "+getName());
        })
        .delayElements(Duration.ofSeconds((int)(Math.random() * 10)))
        .take(index)
        .cast(String.class);
    }

    public static String getName(){
        String a = Stream.of("a","b","c","d","e").findAny().get();
        String b = Stream.of("d","e","f","g","h").findAny().get();
        String c = Stream.of("i","j","k","l","m").findAny().get();
        String d = Stream.of("n","i","p","q","r").findAny().get();
        return a+b+c+d;
    }
    public static String getName1(){
        String a = Stream.of("a","b","c","d","e").findAny().get();
        String b = Stream.of("d","e","f","g","h").findAny().get();
        String c = Stream.of("i","j","k","l","m").findAny().get();
        String d = Stream.of("n","i","p","q","r").findAny().get();
        return b+a+c+d;
    }
}
