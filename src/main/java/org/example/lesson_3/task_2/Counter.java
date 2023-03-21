package org.example.lesson_3.task_2;

public class Counter {
    private long counter;



    public Counter() {
        this.counter = 0L;
    }
    public void increase() {
        this.counter++;
    }
    public long getCounter() {
        return counter;
    }
}
