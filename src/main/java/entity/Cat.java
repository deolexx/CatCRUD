package entity;

public class Cat {
    protected  int id;
    protected String name;
    protected String age;
    protected String owner;

    public Cat() {
    }

    public Cat(int id) {
        this.id = id;
    }

    public Cat(int id, String name, String age, String owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
