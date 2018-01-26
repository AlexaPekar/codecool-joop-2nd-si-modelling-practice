package com.codecool;

public class Tool extends Thing {

    protected String functionality;

    public Tool(String name, String functionality) {
        super(name);
        this.functionality = functionality;
    }

    public String getFunctionality() {
        return functionality;
    }
}