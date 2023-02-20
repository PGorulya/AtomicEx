package com.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicEx {

    static AtomicInteger counter = new AtomicInteger(0);
    static AtomicIntegerArray atomicArray = new AtomicIntegerArray(100);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new M1());
        Thread t2 = new Thread(new M1());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Result values of atomicArray: ");
        System.out.println(atomicArray.toString());

    }
}

class M1 implements Runnable {
    @Override
    public void run() {
        int index;
        while ((index = AtomicEx.counter.getAndIncrement()) < AtomicEx.atomicArray.length()) {
            AtomicEx.atomicArray.set(index, index);
            System.out.println("Thread: " + Thread.currentThread().getId() + " index = " + index + "  counter = " + AtomicEx.counter.get());
        }
    }
}

//class M1 implements Runnable {
//
//    @Override
//    public void run() {
//
//        while (AtomicEx.counter.incrementAndGet() < AtomicEx.atomicArray.length()) {
//            AtomicEx.atomicArray.set(AtomicEx.counter.get(), AtomicEx.counter.get());
//            System.out.println("Thread: " + Thread.currentThread().getId() + " index = " + AtomicEx.counter.get() + "  counter = " + AtomicEx.counter.get());
//        }
//    }
//}

