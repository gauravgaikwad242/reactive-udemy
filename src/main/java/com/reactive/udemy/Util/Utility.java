package com.reactive.udemy.Util;

import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Utility {
    public static void threadSleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Consumer<Object> onNext(){
        return (e)->{
            System.out.println("data received : data :- "+e);
        };
    }
    public static Subscriber<Object> subscriber(){
        return new CustomSubscriber();
    }

    public static void printThread(String addon){
        System.out.println(addon + " : "+Thread.currentThread().getName());
    }
}
