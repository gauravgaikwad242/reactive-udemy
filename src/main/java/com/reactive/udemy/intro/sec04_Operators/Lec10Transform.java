package com.reactive.udemy.intro.sec04_Operators;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {
    public static void main(String[] args) {
        //TRANSFORM IS LIKE A CUSTOM OPERATOR
        //CAN BE USED IF SAME LOGIC IS APPLIED MULTIPLE TIM
        Flux.range(1,100)
                .transform(customOperationWhichIsRepetative())
                .subscribe(Utility.subscriber());


    }
    public static Function<Flux<Integer>,Flux<Integer>> customOperationWhichIsRepetative(){
        return flux -> flux
                .filter(i -> i%2!=0)
                .map(i-> i+5 )
                .doOnDiscard(Integer.class, p -> System.out.println("discarded because of even"+p));
    }
}
