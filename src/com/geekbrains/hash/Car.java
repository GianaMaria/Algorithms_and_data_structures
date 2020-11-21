package com.geekbrains.hash;

public class Car {
    String model;
    int number;
    String color;

    public Car(String model, int number, String color) {
        this.model = model;
        this.number = number;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (number != car.number) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return color != null ? color.equals(car.color) : car.color == null;
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
