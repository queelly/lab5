package models;

import utility.Validatable;

public class Coordinates implements Validatable {

    private final float x; //Максимальное значение поля: 592
    private final Double y; //Максимальное значение поля: 846, Поле не может быть null

    public Coordinates(float x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    @Override
    public String toString() {
        return "(X=" + x + ";Y=" + y + ")";
    }
}