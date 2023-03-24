package org.example.lesson_3.task_1;

public class PongThread implements Runnable {
    private PingPong pingPong;
    public PongThread(PingPong pingPong) {
        this.pingPong = pingPong;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
             pingPong.printPong();
        }
    }
}
