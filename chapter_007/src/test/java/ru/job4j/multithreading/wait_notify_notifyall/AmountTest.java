package ru.job4j.multithreading.wait_notify_notifyall;

import org.junit.Test;

import static org.junit.Assert.*;

public class AmountTest {

    @Test
    public void whenTheProducerAddsAndTheConsumerPicksUp() throws InterruptedException{
        Amount amount = new Amount();
        Producer producer = new Producer(amount);
        Consumer consumer = new Consumer(amount);
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
      //  Thread.sleep(200);
        threadConsumer.start();
        threadProducer.join();
        threadConsumer.join();
    }

}