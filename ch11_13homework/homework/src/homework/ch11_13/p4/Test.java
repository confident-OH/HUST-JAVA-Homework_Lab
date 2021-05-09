package homework.ch11_13.p4;

public class Test {
    public Test(){ }
    public static void main(String[] argv){
        Component computer = ComponentFactory.create();
        System.out.println("id: " + computer.getId() + ", name: " +
                computer.getName() + ", price:" + computer.getPrice());
        Iterator it = computer.iterator(); // get the iterator
        while (it.hasNext()){
            Component c = it.next();
            System.out.println("id: " + c.getId() + ", name: " +
                    c.getName() + ", price:" + c.getPrice());
        }
    }
}
