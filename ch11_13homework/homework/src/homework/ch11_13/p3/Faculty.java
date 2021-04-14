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
        return facultyId == faculty.facultyId && title.equals(faculty.title) && email.equals(faculty.email);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
