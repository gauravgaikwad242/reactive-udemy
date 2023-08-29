package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromArrayOrList {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6,7);
        Flux.fromIterable(list).subscribe(Utility.onNext());

        Integer[] inrarr = {1,2,3,4,5};
        Flux.fromArray(inrarr).subscribe(Utility.onNext());
    }
}
