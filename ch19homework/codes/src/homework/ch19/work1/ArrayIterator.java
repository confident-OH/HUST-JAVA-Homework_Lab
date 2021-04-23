package homework.ch19.work1;

/**
 * 数组迭代器
 */
class  ArrayIterator implements Iterator{
    private int pos = 0;
    private Object[] a = null;

    public ArrayIterator(Object[] array){
        a = array;
    }

    @Override
    public boolean hasNext() {
        return !(pos >= a.length);
    }

    @Override
    public Object next() {
        if(hasNext()){
            Object c = a[pos];
            pos ++;
            return c;
        }
        else
            return null;
    }
}
