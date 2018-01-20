package com.codecool;

public class Tool extends Thing {

    protected String functionality;

    protected Tool(String name, String functionality) {
        super(name);
        this.functionality = functionality;
    }

    public String getFunctionality() {
        return functionality;
    }
}