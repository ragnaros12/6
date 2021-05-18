package com.company.Models;

import com.company.Main;

public class Person {
    private long height; //Значение поля должно быть больше 0
    private long weight; //Значение поля должно быть больше 0

    @Override
    public String toString() {
        return "Person{" +
                "\n\t\theight=" + height +
                ",\n\t\tweight=" + weight +
                "\n\t}";
    }

    public Person(long height, long weight) {
        setHeight(height);
        setWeight(weight);
    }

    public Person() {
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}