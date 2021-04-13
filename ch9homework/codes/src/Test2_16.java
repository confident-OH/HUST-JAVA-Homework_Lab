import java.io.IOException;


public class Test2_16 {
    public void m1() throws RuntimeException{
        throw new RuntimeException();
    }


    public void m2(C ddd){
        m1();
    }

}
