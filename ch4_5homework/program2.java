public class program2 {
    public static void main (String[] argv){
        boolean lable = true;
        String[] str = new String[5];
        for(int i = 0; i<5; i++){
            lable = true;
            String License_plate = program2.RandomStr(); //get a random License
            for(String StrE:str){
                //Determine whether the string is repeated
                if(License_plate.equals(StrE)){
                    // Find it repeated
                    lable = false;
                    break;
                }
            }
            if(lable){
                // If not repeated, print the license
                System.out.println(License_plate);
                str[i] = License_plate;
            }else{
                // If repeated, abandon it;
                i--;
            }
        }
    }
    
    /**
     * Generate a random License sequence;
     * @return
     */
    private static String RandomStr(){
        StringBuffer License_plate = new StringBuffer();
        License_plate.append((char)('A' + Math.random()*('Z' - 'A' + 1)));
        License_plate.append((char)('A' + Math.random()*('Z' - 'A' + 1)));
        License_plate.append((char)('A' + Math.random()*('Z' - 'A' + 1)));
        License_plate.append((char)('0' + Math.random()*('9' - '0' + 1)));
        License_plate.append((char)('0' + Math.random()*('9' - '0' + 1)));
        License_plate.append((char)('0' + Math.random()*('9' - '0' + 1)));
        License_plate.append((char)('0' + Math.random()*('9' - '0' + 1)));
        return License_plate.toString();
    }
}
