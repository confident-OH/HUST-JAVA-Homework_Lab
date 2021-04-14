package homework.ch11_13.p3;

import java.sql.Struct;

public class Student extends Person {
    private String classNo;
    private String department;
    private int studentId;
    public Student(){ }
    public Student(String name, int age, int _studentId, String _department, String _classNo){
        super(name, age);
        this.department = _department;
        this.studentId = _studentId;
        this.classNo = _classNo;
    }

    public void setClassNo(String s){
        this.classNo = s;
    }
    public String getClassNo(){
        return this.classNo;
    }
    public void setDepartment(String s){
        this.department = s;
    }
    public String getDepartment(){
        return this.department;
    }
    public void setStudentId(int s){
        this.studentId = s;
    }
    public int getStudentId(){
        return this.studentId;
    }

    @Override
    public String toString(){
        return super.toString() + ", studentId: " + this.studentId + ", department: " + this.department + ", classNo: " + this.classNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        if(student.studentId != this.studentId) return false;
        boolean th1 = false, th2 = false;
        if(student.classNo == null || this.classNo == null){
            if(student.classNo == null && this.classNo == null) th1 = true;
        }else {
            th1 = this.classNo.equals(student.classNo);
        }
        if(student.department == null || this.department == null){
            if(student.department == null && this.department == null) th2 = true;
        }else {
            th2 = this.department.equals(student.department);
        }
        return th1 && th2;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        clone.department = new String(this.department);
        clone.classNo = new String(this.classNo);
        return clone;
    }
}
