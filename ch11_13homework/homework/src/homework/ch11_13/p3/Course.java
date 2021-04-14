package homework.ch11_13.p3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Course implements Cloneable{
    private String courseName;
    private List<Person> students;
    private Person teacher;
    public Course(){students = new ArrayList<Person>();}
    public Course(String _courseName, Person _teacher){
        students = new ArrayList<Person>();
        this.courseName = _courseName;
        try{
            this.teacher = (Person) _teacher.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Error: faculty cannot be cloned!");
        }
    }

    public void register(Person s){
        if(s instanceof Student && !students.contains(s)){
            students.add(s);
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Person> getStudents() {
        return students;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void unregister(Person s){
        if(s instanceof Student && students.contains(s)){
            students.remove(s);
        }
    }

    public int getNumberOfStudent(){
        if(students == null) return 0;
        return students.size();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Course clone = (Course) super.clone();
        List<Person> item = new LinkedList<Person>();
        clone.students = new ArrayList<Person>(students);
        return clone;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        if(!super.equals(o)) return false;
        Course c = (Course) o;
        List<Person> test = new ArrayList<Person>();
        if(!c.getTeacher().equals(this.teacher)) return false;
        if(!c.courseName.equals(this.courseName)) return false;
        if(c.getNumberOfStudent() != this.getNumberOfStudent()) return false;
        for(Person st1:c.students){
            if(!this.students.contains(st1) || test.contains(st1) || (!(st1 instanceof Student))){
                return false;
            }
            test.add(st1);
        }
        return true;
    }

    @Override
    public String toString(){
        String s =  "Course: \n{\n" +
                "\tCourse name: " + this.courseName + ", \n" +
                "\tNumber of Students: " + getNumberOfStudent() + ", \n" +
                "\tStudents: \n\t{\n";
        for(Person st:students){
            s = s + "\t\t" + st.toString() + ", \n";
        }
        s = s + "\t}\n";
        s = s + "\tTeacher: " + teacher.toString() + "\n}\n";
        return s;
    }

}
