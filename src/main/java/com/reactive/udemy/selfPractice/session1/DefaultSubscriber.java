package com.reactive.udemy.selfPractice.session1;

import com.reactive.udemy.Util.Utility;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber<U> implements Subscriber<U> {

    private Subscription sub;
    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("subscription started");
        this.sub = s;
        this.sub.request(2);
    }

    @Override
    public void onNext(U o) {
        System.out.println("received new object :: "+o);
        System.out.println("started doing some time consuming operation");
        Utility.threadSleep(5);
        System.out.println("completed the time consuming operation");
        System.out.println("requesting next value");
        sub.request(2);

    }

    @Override
    public void onError(Throwable t) {
        System.out.println("error is thrown"+t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("completed Subscription");
    }
}
