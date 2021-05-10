package program2;

import java.util.*;
/**
 * 一个线程安全同步队列，模拟多线程环境下的生产者消费者机制
 * 一个生产者线程通过produce方法向队列里产生元素
 * 一个消费者线程通过consume方法从队列里消费元素
 * @param <T> 元素类型
 */
public abstract class SyncQueue<T> {
    /**
     * 保存队列元素
     */
    private ArrayList<T> list = new ArrayList<>();

    public ArrayList<T> getList(){
        return list;
    }

    /**
     * 生产数据
     * @param elements  生产出的元素列表，需要将该列表元素放入队列
     * @throws InterruptedException
     */
    public abstract void produce(List<T> elements);

    /**
     * 消费数据
     * @return 从队列中取出的数据
     * @throws InterruptedException
     */
    public abstract List<T> consume();
}
