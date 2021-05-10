package program2;

import java.util.*;

public class SyncQueue1<T> extends SyncQueue<T>{

    @Override
    public synchronized void produce(List<T> elements) {
        List<T> list = getList();
        if (!list.isEmpty()) return;
        System.out.print("Produce elements:");
        for (T element : elements) {
            System.out.print(" " + element);
            list.add(element);
        }
        System.out.println();
    }

    @Override
    public synchronized List consume() {
        List<T> list = getList();
        if (list.isEmpty()) return null;
        List<T> consume = new ArrayList<>();
        System.out.print("Consume elements:");
        for (T element : list) {
            System.out.print(" " + element);
            consume.add(element);
        }
        System.out.println();
        list.clear();
        return consume;
    }
}