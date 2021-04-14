package homework.ch11_13.p3;

public class CourseTest {
    public static void main(String[] argv) throws CloneNotSupportedException{
        Person teacher1 = new Faculty("Da luobo", 44, 233333, "professor", "confidentoh@qq.com");
        System.out.println(teacher1.toString());
        Person teacher2 = new Faculty("Gu xiwu", 44, 6666666, "coprofessor", "ddd@qq.com");
        System.out.println(teacher2.toString());
        Person student1 = new Student("Zeng Yangqing", 20, 201811111, "computer science", "ACM1801");
        System.out.println(student1.toString());
        Person student2 = new Student("Liu Jingjing", 20, 201822222, "Counter", "04");
        System.out.println(student2.toString());
        Person student3 = new Student("Lv Pengwan", 19, 201833333, "computer science", "CS1806");
        System.out.println(student3.toString());
        Person student4 = new Student("Xue jinghui", 20, 201844444, "CS", "ACM1801");
        System.out.println(student4.toString());
        Course cl1 = new Course("JAVA", teacher2);
        System.out.println(cl1.toString());
        cl1.register(student1);
        cl1.register(student1);
        cl1.register(student2);
        System.out.println(cl1.toString());
        cl1.unregister(student2);
        cl1.unregister(student2);
        cl1.register(student3);
        System.out.println(cl1.toString());
        Course cl2 = (Course) cl1.clone();
        cl2.register(student2);
        cl2.register(student4);
        System.out.println(cl1.toString());
        System.out.println(cl2.toString());
    }
}
