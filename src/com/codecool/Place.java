package com.codecool;

public class Place extends Thing {

    public Furniture[] furniture;

    public Place(String name, Furniture[] furniture) {
        super(name);
        this.furniture = furniture;
    }

    public Furniture[] getFurniture() {
        return furniture;
    }

    public void addNewFurniture(Furniture[] furniture, Furniture newFurniture) {
        Furniture[] tempArray = new Furniture[furniture.length + 1];
        int counter = 0;
        for(Furniture actualFurniture : furniture) {
            tempArray[counter] = actualFurniture;
            counter++;
        }
        tempArray[tempArray.length - 1] = newFurniture;
        furniture = tempArray;
    }

    public void removeFurniture(Furniture[] furniture, int index){
        Furniture[] tempArray = new Furniture[furniture.length - 1];
        int indexNum = 0;
        for (int i = 0; i < tempArray.length; i++) {
            if (index == i) {
                indexNum = 1;
            }
            tempArray[i - indexNum] = furniture[i];
        }
        furniture = tempArray;
    }
}
