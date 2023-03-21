package org.example.lesson_3.task_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CounterTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            CounterThread counterThread = new CounterThread(counter);
            Future result = executorService.submit(counterThread);
            System.out.println(result.get());
        }
        executorService.shutdown();


    }
}
