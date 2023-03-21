package org.example.lesson_3.task_2;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterThread implements Callable<String> {
    private final static Lock lock = new ReentrantLock();
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public String call() throws Exception {
        try {
            lock.lock();
            counter.increase();
        }finally {
            lock.unlock();
        }
        return String.format("counter = %d, currentThreadName %s", counter.getCounter(), Thread.currentThread().getName());
    }
}
