package com.company;

public class Main {

    public static void main(String[] args) {
        //String[] names = {"Plato", "Aristotle", "Cicero", "Confucius", "Eratosthenes"};
        Fork[] fork = new Fork[5];
       final Philosopher[] philosopher = new Philosopher[5];

        for (int i = 0; i < fork.length; i++) {
            fork[i] = new Fork();
        }

        for (int i = 0; i < philosopher.length; i++) {
            Fork fork_left = fork[i];
            Fork fork_right = fork[(i + 1) % fork.length];

            if (i == philosopher.length - 1) {

                // The last philosopher picks up the right fork first
                philosopher[i] = new Philosopher(fork_right,fork_left);
            } else {
                philosopher[i] = new Philosopher(fork_left,fork_right);
            }
            Thread t
                    = new Thread( philosopher[i],"philosopher" + (i + 1));
            t.start();
        }
    }
}
