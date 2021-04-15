package homework.ch11_13.p4;

import java.util.Objects;

abstract public class Component{
    protected int id;
    protected String name;
    protected double price;
    public Component(){
        name = new String("");
    }
    public Component(int _id, String _name, double _price){
        id = _id;
        name = new String(_name);
        price = _price;
    }

    public abstract void add(Component component) throws UnsupportedOperationException;

    public abstract double calcPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return id == component.id &&
                Double.compare(component.price, price) == 0 &&
                Objects.equals(name, component.name);
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public abstract Iterator getIterator();

    public abstract void remove(Component component) throws UnsupportedOperationException;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
