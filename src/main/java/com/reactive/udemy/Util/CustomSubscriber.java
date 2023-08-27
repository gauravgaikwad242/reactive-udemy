package com.reactive.udemy.Util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CustomSubscriber implements Subscriber<Object> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println("reciver data : "+o);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("got error : "+t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("emmission completed");
    }
}
