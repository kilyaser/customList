package org.example.lesson_3.task_1;

public class PingThread implements Runnable {
    private PingPong pingPong;
    public PingThread(PingPong pingPong) {
        this.pingPong = pingPong;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            pingPong.printPing();
        }
    }
}
