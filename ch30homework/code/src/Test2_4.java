import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2_4 {
    public static class Resource {
        private int value = 0;
        public int sum (int amount) {
            int newValue = value + amount;
            try {Thread.sleep(5);} catch (InterruptedException e) {}
            System.out.println("sum" + newValue);
            return newValue;
        }
        public int sub (int amount) {
            int newValue = value - amount;
            try {Thread.sleep(5);} catch (InterruptedException e) {}
            System.out.println("sub" + newValue);
            return newValue;
        }
    }

    public static void main (String [] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Resource r = new Resource ();
        int loops1 = 10, loops2 = 5, amount = 5;
        Runnable sumTask = () -> r.sum(amount);
        Runnable subTask = () -> r.sub(amount);
        for (int i = 0; i < loops1; i++) {es. execute(sumTask);}
        for (int i = 0; i < loops2; i++) {es. execute(subTask);}
        es. shutdown ();
        while (! es. isTerminated ()) {}
    }
}
