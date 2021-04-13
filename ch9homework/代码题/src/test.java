public class test {
    public static void main(String[] argv){
        Fan a1 = new Fan();
        Fan a2 = new Fan();
        // 设置第一个电风扇的参数
        a1.setSpeed(Fan.FAST);
        a1.setRadius(10);
        a1.setColor("yellow");
        a1.setOn(true);
        // 设置第二个电风扇的参数，由于仅速度参数和默认参数不一样，因此仅修改了速度参数
        a2.setSpeed(Fan.MEDIUM);
        // 测试toString
        System.out.println(a1.toString());
        System.out.println(a2.toString());
    }
}
