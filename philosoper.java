package com.company;

class Philosopher extends Thread {

    private Fork fork_left;
    private Fork fork_right;


    Philosopher(Fork fork_left, Fork fork_right) {
        this.fork_left = fork_left;
        this.fork_right = fork_right;

    }



    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                doAction(System.nanoTime() + ": Thinking");
                synchronized (fork_left) {
                    doAction(
                            System.nanoTime()
                                    + ": Picked up left fork");
                    synchronized (fork_right) {
                        // eating
                        doAction(
                                System.nanoTime()
                                        + ": Picked up right fork - eating");

                        doAction(
                                System.nanoTime()
                                        + ": Put down right fork");
                    }

                    // Back to thinking
                    doAction(
                            System.nanoTime()
                                    + ": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
