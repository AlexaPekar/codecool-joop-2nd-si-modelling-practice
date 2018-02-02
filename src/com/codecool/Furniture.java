package com.codecool;

public class Furniture extends Thing { //abstract

    private int weight;

    public Furniture(String name, int weight) {
        super(name);
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

}