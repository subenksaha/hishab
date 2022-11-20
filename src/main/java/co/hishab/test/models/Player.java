package co.hishab.test.models;


import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Player {
    UUID id;
    String name;
    Float age;
    Integer score;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Player(UUID id, String name, Float age, Integer score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = null;
    }

    static public Player from(String name, Float age){
        UUID id = UUID.randomUUID();
        Integer score = 0;
        return new Player(id, name, age, score);
    }
}
