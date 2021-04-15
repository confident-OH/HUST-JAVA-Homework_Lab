package homework.ch11_13.p4;

public class AtomicComponent extends Component {
    AtomicComponent() { }
    AtomicComponent(int id, String name, double price){
        super(id, name, price);
    }

    @Override
    public void add(Component component) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public double calcPrice() {
        return this.price;
    }

    @Override
    public void remove(Component component) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator getIterator() {
        return new NullIterator();
    }
}
