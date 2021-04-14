package homework.ch11_13.p3;

public class Person implements Cloneable {
    private String name;
    private int age;

    public Person(){}
    public Person(String _name, int _age){
        this.name = _name;
        this.age = _age;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int _age) {
        this.age = _age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString(){
        return "name: " + this.name + ", age: " + this.age;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return this.age == ((Person) obj).age && this.name.equals(((Person) obj).name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
