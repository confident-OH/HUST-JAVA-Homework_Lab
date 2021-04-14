package homework.ch11_13.p3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course implements Cloneable{
    private String courseName;
    private List<Person> students;
    private Person teacher;
    public Course(){students = new ArrayList<Person>(); teacher = new Faculty();}
    public Course(String _courseName, Person _teacher) throws CloneNotSupportedException{
        students = new ArrayList<Person>();
        this.courseName = _courseName;
        this.teacher = (Faculty) _teacher.clone();
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
        clone.students = new ArrayList<Person>();
        for(int i = 0; i<getNumberOfStudent(); i++){
            clone.students.add((Student)this.students.get(i).clone());
        }
        clone.teacher = (Faculty) this.teacher.clone();
        clone.courseName = new String(this.courseName);
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseName, course.courseName) &&
                students.containsAll(course.students)&&
                course.students.containsAll(this.students) &&
                Objects.equals(teacher, course.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, students, teacher);
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
