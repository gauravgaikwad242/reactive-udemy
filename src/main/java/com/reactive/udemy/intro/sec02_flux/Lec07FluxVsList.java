package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Stream;

public class Lec07FluxVsList {
    public static void main(String[] args) {
        //main goal of this exercise to
        //tell that the list will be consumed as whole
        //while flux will start emitting data as soon as it has data available
        List<Integer> list = getList(5);
        System.out.println(list.toString());


        getFlux(5).subscribe(Utility.onNext());
    }

    public static List<Integer> getList(int i ){
        return Stream.iterate(1,n-> n+1).limit(i).map((data)->{
            Utility.threadSleep(1);
            return data;
        }).toList();
    }

    public static Flux<Integer> getFlux(int i ){
        return Flux.range(1,i).map((t)->{
            Utility.threadSleep(1);
            return t ;
        });
    }
}
