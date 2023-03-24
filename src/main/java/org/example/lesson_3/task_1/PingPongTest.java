package org.example.lesson_3.task_1;

public class PingPongTest {
    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();

        PingThread ping = new PingThread(pingPong);
        PongThread pong = new PongThread(pingPong);

        Thread pingThread = new Thread(ping);
        Thread pongThread = new Thread(pong);

        pingThread.start();
        pongThread.start();

        Thread.sleep(5_000);

        pingThread.interrupt();
        pongThread.interrupt();

    }
}
