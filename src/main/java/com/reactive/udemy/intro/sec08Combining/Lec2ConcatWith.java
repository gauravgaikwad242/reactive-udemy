package com.reactive.udemy.intro.sec08Combining;

import java.util.List;
import java.util.stream.Stream;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec2ConcatWith {
    public static void main(String[] args) {

        /*
         * concatwith: it concats all the publisher and
         * publishes data in sequence of concatination
         */
        getNamesFlux(6)
                .concatWith(getNamesFlux(3))
                .concatWith(getNamesFlux(5))
                .subscribe(Utility.subscriber());
        /*
         * output:
         * reciver data : 6 7 ghqf
         * reciver data : 6 1 denr
         * reciver data : 6 5 dgpn
         * reciver data : 6 7 hfqr
         * reciver data : 6 2 dkme
         * reciver data : 6 3 ehph
         * reciver data : 3 9 dhie
         * reciver data : 3 0 hjnf
         * reciver data : 3 4 gfqq
         * reciver data : 5 4 gljg
         * reciver data : 5 6 henq
         * reciver data : 5 6 fhmd
         * reciver data : 5 2 bmld
         * reciver data : 5 8 dhmn
         */

    }

    public static Flux<String> getNamesFlux(int index) {
        return Flux.generate((syncSink) -> {
            syncSink.next(index + " " + (int) (Math.random() * 10) + " " + getName());
        })
                .take(index)
                .cast(String.class);
    }

    public static Flux<String> getNamesFlux() {
        return Flux.generate((syncSink) -> {
            syncSink.next((int) (Math.random() * 10) + " " + getName());
        })
                .cast(String.class);
    }

    public static String getName() {
        String a = List.of("a", "b", "c", "d", "e", "d", "e", "f", "g", "h").get((int) (Math.random() * 10));
        String b = List.of("d", "e", "f", "g", "h", "i", "j", "k", "l", "m").get((int) (Math.random() * 10));
        String c = List.of("i", "j", "k", "l", "m", "n", "i", "p", "q", "r").get((int) (Math.random() * 10));
        String d = List.of("n", "i", "p", "q", "r", "d", "e", "f", "g", "h").get((int) (Math.random() * 10));
        return a + b + c + d;
    }

    public static String getName1() {
        String a = Stream.of("a", "b", "c", "d", "e").findAny().get();
        String b = Stream.of("d", "e", "f", "g", "h").findAny().get();
        String c = Stream.of("i", "j", "k", "l", "m").findAny().get();
        String d = Stream.of("n", "i", "p", "q", "r").findAny().get();
        return b + a + c + d;
    }
}
