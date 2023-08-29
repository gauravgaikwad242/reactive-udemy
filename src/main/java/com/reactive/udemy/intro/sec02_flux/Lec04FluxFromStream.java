package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {
        List<Integer> intlist = Arrays.asList(1,2,3,4,5,6,7,8);
        Stream<Integer> intstream = intlist.stream();

        Flux.fromStream(intstream).subscribe(Utility.onNext());
        //second stream operation will give error because stream is lazy insted
        Flux.fromStream(intstream).subscribe(Utility.onNext());
        //instead we can pass
        Flux.fromStream(intlist.stream());

    }
}
