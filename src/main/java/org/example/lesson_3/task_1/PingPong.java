package org.example.lesson_3.task_1;



public class PingPong {
    private final Object monitor = new Object();
    private boolean isPing;
    public void printPing() {
        synchronized (monitor) {
            try {
                while (this.isPing) {
                    monitor.wait();
                }
                System.out.println("Ping");
                Thread.sleep(500);
                this.isPing = true;
                monitor.notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void printPong(){
        synchronized (monitor) {
            try {
                while (!this.isPing) {
                    monitor.wait();
                }
                System.out.println("Pong");
                Thread.sleep(500);
                this.isPing = false;
                monitor.notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
