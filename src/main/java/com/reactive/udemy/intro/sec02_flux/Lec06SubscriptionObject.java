package com.reactive.udemy.intro.sec02_flux;

import com.reactive.udemy.Util.Utility;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06SubscriptionObject {

    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1,10)
                .subscribeWith(
                        new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription s) {
                                System.out.println("received subscription object : "+ s);
                                atomicReference.set(s);
//                                s.request(2);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                System.out.println("on next : "+ integer);
                            }

                            @Override
                            public void onError(Throwable t) {
                                System.out.println("on error : "+ t.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("completed : :");
                            }
                        }
                );
        Utility.threadSleep(2);
        atomicReference.get().request(2);
        Utility.threadSleep(2);
        atomicReference.get().request(2);
        Utility.threadSleep(2);
        atomicReference.get().cancel();
        Utility.threadSleep(2);
        atomicReference.get().request(2);
    }
}
