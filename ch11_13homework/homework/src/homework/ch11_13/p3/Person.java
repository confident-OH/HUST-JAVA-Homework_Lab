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

    /*
     * remember the String maybe null
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person o = (Person) obj;
        if(o.name == null && this.name == null) return this.age == o.age;
        if(o.name == null || this.name == null) return false;
        return this.age == o.age && this.name.equals(o.name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Person clone = (Person) super.clone();
        clone.name = new String(this.name);
        return clone;
    }
}
