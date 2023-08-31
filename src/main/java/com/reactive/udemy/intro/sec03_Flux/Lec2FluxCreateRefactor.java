package com.reactive.udemy.intro.sec03_Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Consumer;

import com.reactive.udemy.Util.Utility;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class Lec2FluxCreateRefactor {
    public static void main(String[] args) {
        
        // Flux.create(fluxSink -> { }); //refactoring fluxsink consumer

        //here we are producing from outside of create (sink)
        DataProducer dataProducer = new DataProducer();
        Flux.create(dataProducer).subscribe(Utility.onNext());
        dataProducer.produce();
        dataProducer.produce();
        dataProducer.produce();
        Utility.threadSleep(2);

    }
}

class DataProducer implements Consumer<FluxSink<String>>{

    //for sink
    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> arg0) {
        this.fluxSink = arg0;
    }

    public void produce(){
        this.fluxSink.next(LocalDateTime.now().toString());
    }

}
