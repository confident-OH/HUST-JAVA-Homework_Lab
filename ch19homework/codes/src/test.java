import java.util.ArrayList;

class GeometricObject {}
class PolyGon extends GeometricObject {}
class Rectangle extends PolyGon {}

public class test {
    public static void main(String[] argv)
    {
        GeometricObject o = new Rectangle ();
        Class clz1 = o. getClass ();
        System.out.println(o.getClass().getSimpleName());
        Class<? extends PolyGon> clz4 = null;
        ArrayList<String> lists = new ArrayList<String>();
        clz4 = PolyGon.class;
        clz4 = Rectangle.class;


    }
}
