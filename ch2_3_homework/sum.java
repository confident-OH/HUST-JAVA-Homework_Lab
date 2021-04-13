import java.util.Scanner;

public class sum{
    public static void main(String[] argv){
        Scanner NumberScan = new Scanner(System.in);
        System.out.print("Enter a number between 0 and 1000:");
        int num = NumberScan.nextInt();
        int result = 0;
        if(num<0||num>1000){
            //输入超出题目要求的数据范围
            System.out.println("The number out of range");
        }
        else{
            while(num>0){
                result += num % 10;
                num /= 10;
            }
            System.out.println("The sum of the digits is " + result);
        }
        NumberScan.close();
    }
}