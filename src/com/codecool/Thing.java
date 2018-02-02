package com.codecool;

import java.io.Serializable;

class Thing implements Serializable {

    String name;

    Thing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}