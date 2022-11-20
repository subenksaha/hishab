package co.hishab.test.dtos;

public class CreatePlayer {
    String name;
    Float age;

    public CreatePlayer(String name, Float age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }
}
