class SubClass extends SuperClass{
    static int i = 15;
    static{
        System.out.println(" static in SubClass");
    }
    SubClass( ){
        System.out.println("SubClass is called");
    }
    public static void main(String[] args){
        int i = SubClass.i;
        new SubClass( );
        new SuperClass( );
    }
}
