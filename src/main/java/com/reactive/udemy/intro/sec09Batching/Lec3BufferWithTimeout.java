package com.reactive.udemy.intro.sec09Batching;

import java.time.Duration;
import java.util.List;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec3BufferWithTimeout {
        public static void main(String[] args) {
        
        Flux<String> strFlux = eventStream(); //string
        Flux<List<String>> listFlux = strFlux.buffer(Duration.ofSeconds(2)); //buffer collects items every two seconds
        // listFlux.subscribe(Utility.subscriber());
        // Utility.threadSleep(5);

        /*
         * but problem is when the emmittion is too high in 2 secs
         */
        /*
         * buffer timeout is way inbetween buffer(5) and buffer(Duration.ofSeconds(2))
         */

         Flux<List<String>> listHybridFlux = strFlux.bufferTimeout(5,Duration.ofSeconds(2));
         listHybridFlux.subscribe(Utility.subscriber());
         Utility.threadSleep(5);



    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(30))
        .map(i -> "event"+i);
    }
}
