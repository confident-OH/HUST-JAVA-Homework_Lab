package homework.ch11_13.p4;

import java.util.ArrayList;

public class ComponentList extends ArrayList<Component> implements Iterator {
    private int position;
    public ComponentList() {position = 0;}

    @Override
    public boolean hasNext(){
        if(position<this.size()) return true;
        else return false;
    }

    @Override
    public Component next(){
        if(hasNext()){
            return this.get(position++);
        }
        else {
            return null;
        }
    }
}
