package program1;

import java.util.*;

class Container<T>{
    private List<T> elements = new ArrayList<>();

    /**
     * 添加元素
     * @param e 要添加的元素
     */
    public  void add(T e){
        elements.add(e);
    }

    /**
     * 删除指定下标的元素
     * @param index 指定元素下标
     * @return 被删除的元素
     */
    public T remove(int index){
        return elements.remove(index);
    }

    /**
     * 获取容器里元素的个数
     * @return 元素个数
     */
    public int size(){
        return elements.size();
    }

    /**
     * 获取指定下标的元素
     * @param index 指定下标
     * @return 指定下标的元素
     */
    public T get(int index){
        return elements.get(index);
    }
}

