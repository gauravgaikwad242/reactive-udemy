package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

public class Lec4LimitRate {
    public static void main(String[] args) {
        //TODO : WHY DO WE NEED THIS ASK COPILOT
        Flux.range(1,1000)
                .log()
//                .limitRate(50)// no of elements fetched in //75 %
                .limitRate(100, 90) // 100 elements 90 %
                .subscribe(Utility.subscriber());

    }
}
