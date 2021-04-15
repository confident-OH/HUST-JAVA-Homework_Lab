package homework.ch11_13.p4;

import javax.swing.*;
import java.util.ArrayList;

public class CompositeIterator implements Iterator{
    protected ArrayList<Iterator> iterators;
    public CompositeIterator() {iterators = new ArrayList<Iterator>();}
    public CompositeIterator(Iterator iterator) {
        iterators = new ArrayList<Iterator>();
        iterators.add(iterator);
    }

    @Override
    public boolean hasNext() {
        return iterators.get(0).hasNext();
    }

    @Override
    public Component next() {
        return iterators.get(0).next();
    }
}
