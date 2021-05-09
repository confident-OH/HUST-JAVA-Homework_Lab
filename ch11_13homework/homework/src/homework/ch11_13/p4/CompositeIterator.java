package homework.ch11_13.p4;

import java.util.ArrayList;
import java.util.List;

public class CompositeIterator implements Iterator{
    protected ArrayList<Iterator> iterators;
    public CompositeIterator() {iterators = new ArrayList<Iterator>();}
    public CompositeIterator(Iterator iterator) {
        iterators = new ArrayList<Iterator>();
        iterators.add(iterator);
    }
    public CompositeIterator(List<Iterator> iterators){
        this.iterators = new ArrayList<Iterator>(iterators);
    }

    @Override
    public boolean hasNext() {
        while (iterators.size() > 0 && !iterators.get(iterators.size() - 1).hasNext()){
            iterators.remove(iterators.size() - 1);
        }
        if(iterators.size() == 0) return false;
        return iterators.get(iterators.size() - 1).hasNext();
    }

    @Override
    public Component next() {
        while (!iterators.get(iterators.size() - 1).hasNext()){
            iterators.remove(iterators.size() - 1);
        }
        if(this.hasNext()){
            Component c = iterators.get(iterators.size() - 1).next();
            if(c instanceof CompositeComponent){
                iterators.add(((CompositeComponent) c).childs);
            }
            return c;
        }
        else return null;
    }
}
