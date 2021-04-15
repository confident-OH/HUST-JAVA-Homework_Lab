package homework.ch11_13.p4;


public class CompositeComponent extends Component {
    protected ComponentList childs;
    public CompositeComponent(){childs = new ComponentList();}
    public CompositeComponent(int id, String name, double price){
        super(id, name, price);
        childs = new ComponentList();
    }

    @Override
    public void add(Component component) throws UnsupportedOperationException{
        childs.add(component);
    }

    @Override
    public void remove(Component component) throws UnsupportedOperationException {
        if(childs!=null){
            childs.remove(component);
        }
    }

    @Override
    public double calcPrice() {
        double result = 0;
        for(Component item:childs){
            result += item.calcPrice();
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new CompositeIterator(childs);
    }

    @Override
    public String toString() {
        return "CompositeComponent{" +
                "childs=" + childs +
                "} " + super.toString();
    }
}
