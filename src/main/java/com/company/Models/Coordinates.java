package com.company.Models;


public class Coordinates {
    private Double x; //Максимальное значение поля: 629, Поле не может быть null
    private Float y; //Значение поля должно быть больше -825, Поле не может быть null

    @Override
    public String toString() {
        return "Coordinates{" +
                "\n\t\tx=" + x +
                ",\n\t\ty=" + y +
                "\n\t}";
    }

    public Coordinates(Double x, Float y) {
        setX(x);
        setY(y);
    }

    public Coordinates() {
        setY((float)0);
        setX((double)0);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}