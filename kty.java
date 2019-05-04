
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


class Chopstick {

    private static int cnt = 0;
    private int num = cnt++;



    @Override
    public String toString() {

        return "Chopstick " + num;
    }
}

class Philosopher extends Thread {

    private static Random rnd = new Random();
    private static int cnt = 0;
    private int num = cnt++;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    static int waiting = 0;

    public Philosopher(Chopstick left, Chopstick right) {

        leftChopstick = left;

        rightChopstick = right;

        start();
    }

    public void think() {

        System.out.println(this + " is thinking");


        if (waiting > 0) {


            try {


                sleep(rnd.nextInt(waiting));


            } catch (InterruptedException e) {


                throw new RuntimeException(e);


            }

        }
    }

    public void eat() {

        synchronized (leftChopstick) {


            System.out.println(this + " has " + this.leftChopstick + " Waiting for " + this.rightChopstick);


            synchronized (rightChopstick) {


                System.out.println(this + " eating");


            }

        }
    }

    @Override
    public String toString() {

        return "Philosopher " + num;
    }

    @Override
    public void run() {

        while (true) {


            think();


            eat();

        }
    }
}

class DiningPhilosophers {

    private static boolean gotoDeadLock =true;

    public static void main(String[] args) {


        Philosopher[] phil = new Philosopher[5];

        Philosopher.waiting = 3;

        Chopstick left = new Chopstick(), right = new Chopstick(), first = left;

        int i = 0;

        while (i < phil.length - 1) {


            phil[i++] = new Philosopher(left, right);


            left = right;


            right = new Chopstick();

        }



        if (gotoDeadLock) {


            phil[i] = new Philosopher(left, first);

        } else

        {


            phil[i] = new Philosopher(first, left);

        }

        if (args.length >= 4) {


            int delay = 3;


            if (delay != 0) {


                Timeout timeout = new Timeout(delay * 1000, "Timed out");


            }

        }
    }
}

class Timeout extends Timer {

    public Timeout(int delay, final String msg) {

        super(true);

        schedule(new TimerTask() {



            @Override


            public void run() {


                System.out.println(msg);


                System.exit(0);


            }

        }, delay);
    }
}
