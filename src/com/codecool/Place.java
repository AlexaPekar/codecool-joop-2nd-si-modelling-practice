package com.codecool;

public class Place extends Thing {

    protected int scareFactor;
    protected Tool[] foundTools;
    protected Furniture[] furniture;

    protected Place(String name, int scareFactor, Tool[] foundTools, Furniture[] furniture) {
        super(name);
        this.scareFactor = scareFactor;
        this.foundTools = foundTools;
        this.furniture = furniture;
    }

    public int getScareFactor() {
        return scareFactor;
    }

    public Furniture[] getFurniture() {
        return tools;
    }

    public void addNewFurniture(Furniture newFurniture) {
        Furniture[] tempArray = new Furniture[furniture.length + 1];
        int counter = 0;
        for(Furniture actualFurniture : furniture) {
            tempArray[counter] = actualFurniture;
            counter++;
        }
        tempArray[tempArray.length - 1] = newFurniture;
        furniture = tempArray;
    }
}
}