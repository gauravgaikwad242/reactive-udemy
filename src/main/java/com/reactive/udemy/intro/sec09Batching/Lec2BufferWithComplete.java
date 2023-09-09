package com.reactive.udemy.intro.sec09Batching;

import java.time.Duration;
import java.util.List;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec2BufferWithComplete {
    /*
     * what if the emmitted size is less than buffer size
     * it recives items and publishes as soon as the complete signal is recieved
     */
    public static void main(String[] args) {
        // eventStream()
        // .subscribe(Utility.subscriber());
        // Utility.threadSleep(5);

        Flux<String> strFlux = eventStream(); //string
        Flux<List<String>> listFlux = strFlux.buffer(5); //buffer collects items and publishes at once hence list
        listFlux.subscribe(Utility.subscriber());
        Utility.threadSleep(5);

        /*
         * output : reciver data : [event0, event1, event2]
         */


    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(300))
        .take(3)
        .map(i -> "event"+i);
    }
}
