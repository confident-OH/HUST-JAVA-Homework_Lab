package homework.ch11_13.p4;

public class Test {
    public Test(){ }
    public static void main(String[] argv){
        Component computer = ComponentFactory.create();
        System.out.println("id: " + computer.getId() + ", name: " +
                computer.getName() + ", price:" + computer.getPrice());
        Iterator it = computer.getIterator(); // 首先得到迭代器
        while (it.hasNext()){
            Component c = it.next();
            //注意这里不能打印c.toString(), toString()方法会递归调用子组件的toString()
            System.out.println("id: " + c.getId() + ", name: " +
                    c.getName() + ", price:" + c.getPrice());
        }

    }
}
