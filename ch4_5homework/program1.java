import java.util.Scanner;

public class program1 {
    public static void main(String[] argv){
        Scanner CRead = new Scanner(System.in);
        System.out.print("Enter a string in English: ");
        String str = CRead.nextLine();
        int[] table = program1.count(str);
        program1.show(table);
        CRead.close();
    }

    /**
     * Count a int[], storing the num of each letter of input string.
     * @param s
     * @return 
     */
    private static int[] count(String s){
        //store the num of each letter of input string
        int[] table = new int[26]; 
        int len = s.length();
        for(int i = 0; i<len; i++){
            //Traversal string
            char c = s.charAt(i);
            //count the char
            if(c>='a' && c<='z'){
                table[c - 'a']++;
            }
            else if(c>='A' && c<='Z'){
                table[c - 'A']++;
            }
        }
        return table;
    }
    
    /**
     * Show the int[]
     * @param t
     */
    private static void show(int[] t){
        for(int i = 0; i<t.length; i++){
            String s = String.valueOf((char)('a'+i));
            System.out.print(s + ": " + t[i] + "\t");
        }
        System.out.println();
    }
}
