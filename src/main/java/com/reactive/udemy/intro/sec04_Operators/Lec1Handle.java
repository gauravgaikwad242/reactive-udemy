package com.reactive.udemy.intro.sec04_Operators;

import java.time.LocalDateTime;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class Lec1Handle {
    public static void main(String[] args) {
        //handle is combination of filter and map
        //not literally but we can impletment as filter and map
        //it takes dataemitted & synchronousssink(similar to generate) as an argument

        //USEFUL IF WE GET AN FLUX AS RETURN TYPE AND WE WANT TO APPLY LOGIC SIMILART TO GENERATE
        Flux
        .generate((SynchronousSink<String> syncSink) -> syncSink.next(LocalDateTime.now().toString()))
        .handle((dataemitted, syncSink)->{
            syncSink.next(dataemitted);
            if(dataemitted.contains("56")){ //if date contains 56 then stop
                syncSink.complete();
            }
        }).subscribe(Utility.onNext());
    }
}
