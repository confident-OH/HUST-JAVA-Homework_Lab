package homework.ch19.work1;


/**
 * 容器类，内部用Object[]保存元素
 */
class Container {
    private Object[] elements;
    private int elementsCount = 0;
    private int size = 0;

    public Container(int size){
        elements = new Object[size];
        this.size = size;
    }

    public boolean add(Object e){
        if(elementsCount < size){
            elements[elementsCount ++] = e;
            return true;
        }
        else{
            return  false;
        }
    }

    /**
     * 返回容器的迭代器
     * @return
     */
    public Iterator iterator(){
        return new ArrayIterator(elements);
    }
}