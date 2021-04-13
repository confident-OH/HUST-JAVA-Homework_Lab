import java.util.Scanner;

public class Test3 {
	public static void main(String[] argv){
		Scanner scanInt = new Scanner(System.in);
		int rowNum = scanInt.nextInt();
		int[][] arr = Test3.createArray(rowNum);
		if(arr == null){
			System.out.println("Invalid Input!");
		}
		else{
			Test3.printArray(arr);
		}
		scanInt.close();
	}
    /**
     *  创建一个不规则二维数组
     *  第一行row列
     *  第二行row - 1列
     *  ...
     *  最后一行1列
	 *	数组元素值都为默认值
     * @param row 行数
     * @return 创建好的不规则数组
     */
    public static  int[][] createArray(int row){
		if(row<=0) return null;
        int[][] array = new int[row][];
		for(int i = 0; i<row; i++){
			array[i] = new int[row - i];
		}
		return array;
    }

    /**
     * 逐行打印出二维数组，数组元素之间以空格分开
     * @param a
     */
    public static  void printArray(int[][] a){
		for(int i = 0; i<a.length; i++){
			for(int j = 0; j<a[i].length; j++){
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
    }
}