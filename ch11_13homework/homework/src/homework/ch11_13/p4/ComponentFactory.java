package homework.ch11_13.p4;

import java.util.Iterator;

public class ComponentFactory {
    public ComponentFactory(){ }
    public static Component create(){
        int id = 0;
        Component computer = new CompositeComponent(id++, "Think Pad", 0.0);

        Component keyboard = new AtomicComponent(id++, "Keyboard", 20.0);
        Component mouse = new AtomicComponent(id++, "Mouse", 20.0);
        Component monitor = new AtomicComponent(id++, "Monitor", 1000.0);
        computer.add(keyboard);     //键盘加入computer
        computer.add(mouse);        //鼠标加入computer
        computer.add(monitor);      //显示器加入computer

        Component mainFrame= new CompositeComponent(id++, "Main frame", 0.0);
        Component hardDisk = new AtomicComponent(id++, "Hard disk",1000);
        Component powerSupplier = new AtomicComponent(id++, "Power supplier",500);
        mainFrame.add(hardDisk);
        mainFrame.add(powerSupplier);

        Component mainBoard = new CompositeComponent(id++, "Main board", 0.0);
        //创建CPU对象
        Component cpu = new AtomicComponent(id++, "CPU", 1500.0);
        //创建显卡对象
        Component videoCard = new AtomicComponent(id++, "Video card", 900);
        //创建网卡对象
        Component networkCard = new AtomicComponent(id++, "Network card", 100);
        mainBoard.add(cpu);         //cpu加入主板
        mainBoard.add(videoCard);   //videoCard加入主板
        mainBoard.add(networkCard); //networkCard加入主板

        mainFrame.add(mainBoard);   //mainBoard加入主机
        computer.add(mainFrame);    //将主机加入computer
        return computer;
    }

}
