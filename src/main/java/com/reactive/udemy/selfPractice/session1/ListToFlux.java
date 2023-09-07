package com.reactive.udemy.selfPractice.session1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class ListToFlux {
    
    static Queue<String> list = new LinkedList<>();
    public static void main(String[] args) {
        list.add("one");
        list.add("three");
        list.add("two");
        Flux.fromIterable(list)
        .subscribe(Utility.subscriber());
        list.add("four");

  
    }
}
