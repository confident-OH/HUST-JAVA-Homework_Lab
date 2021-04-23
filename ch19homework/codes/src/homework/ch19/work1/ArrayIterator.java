package homework.ch19.work1;

/**
 * 数组迭代器
 */
class  ArrayIterator<T> implements Iterator{
    private int pos = 0;
    private T[] a = null;

    public ArrayIterator(T[] array){
        a = array;
    }

    @Override
    public boolean hasNext() {
        return !(pos >= a.length);
    }

    @Override
    public T next() {
        if(hasNext()){
            T c = a[pos];
            pos ++;
            return c;
        }
        else
            return null;
    }
}
