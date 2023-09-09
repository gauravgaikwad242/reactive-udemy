package com.reactive.udemy.intro.sec09Batching;

import java.time.Duration;
import java.util.List;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;

public class Lec4Overlap {
    public static void main(String[] args) {

        Flux<String> strFlux = eventStream(); // string

        Flux<List<String>> listHybridFlux = strFlux.buffer(3, 1); // here we will get last 3 items
        listHybridFlux.subscribe(Utility.subscriber());

        /*
         * might be useful for sampling
         */
        Flux<List<String>> listHybridFlux1 = strFlux.buffer(3, 5); // here we will get last 3 items
        listHybridFlux1.subscribe(Utility.subscriber());
        Utility.threadSleep(5);

        /*
         * reciver data : [event0, event1, event2]
         * reciver data : [event1, event2, event3]
         * reciver data : [event2, event3, event4]
         * reciver data : [event3, event4, event5]
         */

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(30))
                .map(i -> "event" + i);
    }
}
