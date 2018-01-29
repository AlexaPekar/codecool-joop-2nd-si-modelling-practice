package com.codecool;

import java.io.Serializable;

public class Thing implements Serializable {

    //private static final long serialVersionUID = 1982579909381770716L;

    protected String name;

    protected Thing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}