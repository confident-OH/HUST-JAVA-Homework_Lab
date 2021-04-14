package homework.ch11_13.p3;

public class Faculty extends Person {
    private int facultyId;
    private String title;
    private String email;
    public Faculty(){ }
    public Faculty(String name, int age, int facultyId, String title, String email){
        super(name, age);
        this.facultyId = facultyId;
        this.title = title;
        this.email = email;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString() + ", facultyId: " + this.facultyId + ", title: " + this.title + ", email: " + this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Faculty faculty = (Faculty) o;
        if(faculty.facultyId != this.facultyId) return false;
        boolean th1 = false, th2 = false;
        if(this.email == null || faculty.email == null){
            if(this.email == null && faculty.email == null) th1 = true;
        }else{
            th1 = this.email.equals(faculty.email);
        }
        if(this.title == null || faculty.title == null){
            if(this.title == null && faculty.title == null) th2 = true;
        }else {
            th2 = this.title.equals(faculty.title);
        }
        return th1 && th2;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Faculty clone = (Faculty) super.clone();
        clone.title = new String(this.title);
        clone.email = new String(this.email);
        return clone;
    }
}
