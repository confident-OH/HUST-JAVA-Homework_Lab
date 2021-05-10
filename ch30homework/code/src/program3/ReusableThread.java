package program3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReusableThread extends Thread{
    final Lock lock = new ReentrantLock();

    final Condition isTask = lock.newCondition();
    final Condition unTask = lock.newCondition();

    private Runnable runTask = null;  //保存接受的线程任务

    //只定义不带参数的构造函数
    public ReusableThread(){
        super();
    }

    /**
     * 覆盖Thread类的run方法
     */
    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (runTask == null) {
                    isTask.await();
                }
                runTask.run();
                runTask = null;
                unTask.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 提交新的任务
     * @param task 要提交的任务
     */
    public void submit(Runnable task){
        lock.lock();
        try {
            while (runTask != null) {
                unTask.await();
            }
            runTask = task;
            isTask.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

