package com.codecool;

import java.io.Serializable;

public class Place extends Thing {

    public Furniture[] furniture;

    public Place(String name, Furniture[] furniture) {
        super(name);
        this.furniture = furniture;
    }

    public String getName() {
        return name;
    }

    public static Place createKitchen() {
        Furniture[] furniture = new Furniture[4];
        furniture[0] = new Furniture("table", 10);
        furniture[1] = new Furniture("chair", 4);
        furniture[2] = new Furniture("cooker", 50);
        furniture[3] = new Furniture("fridge", 40);
        return new Place("Kitchen", furniture);
    }

    public static Place createBedroom() {
        Furniture[] furniture = new Furniture[4];
        furniture[0] = new Furniture("bed", 50);
        furniture[1] = new Furniture("bedside cabinet", 5);
        furniture[2] = new Furniture("night light", 2);
        furniture[3] = new Furniture("wardrobe", 50);
        return new Place("Bedroom", furniture);
    }

    public static Place createBathroom() {
        Furniture[] furniture = new Furniture[4];
        furniture[0] = new Furniture("toilet", 20);
        furniture[1] = new Furniture("sink", 10);
        furniture[2] = new Furniture("mirror", 5);
        furniture[3] = new Furniture("tub", 30);
        return new Place("Bathroom", furniture);
    }

    public static Place createLibrary() {
        Furniture[] furniture = new Furniture[4];
        furniture[0] = new Furniture("bookshelf", 10);
        furniture[1] = new Furniture("desk", 20);
        furniture[2] = new Furniture("table lamp", 2);
        furniture[3] = new Furniture("armchair", 30);
        return new Place("Library", furniture);
    }

    public static Place createLivingroom() {
        Furniture[] furniture = new Furniture[4];
        furniture[0] = new Furniture("sofa", 50);
        furniture[1] = new Furniture("fireplace", 50);
        furniture[2] = new Furniture("piano", 80);
        furniture[3] = new Furniture("coffe table", 10);
        return new Place("Living room", furniture);
    }

    public static Place createTrainingGround() {
        Furniture[] furniture = new Furniture[4];
        furniture[0] = new Furniture("rope", 10);
        furniture[1] = new Furniture("treadmill", 40);
        furniture[2] = new Furniture("stationary bike", 30);
        furniture[3] = new Furniture("wall bars", 50);
        return new Place("Training ground", furniture);
    }

    public Furniture[] getFurniture() {
        return furniture;
    }

    public void printFurniture() {
        int counter = 0;
        for (Furniture furni : furniture) {
            System.out.print("[" + counter + "]" + furni.getName() + "(" + furni.getWeight() + "kg)  ");
            counter++;
        }
    }

    public Furniture[] addNewFurniture(Furniture[] furniture, Furniture newFurniture) {
        Furniture[] tempArray = new Furniture[furniture.length + 1];
        int counter = 0;
        for(Furniture actualFurniture : furniture) {
            tempArray[counter] = actualFurniture;
            counter++;
        }
        tempArray[tempArray.length - 1] = newFurniture;
        return tempArray;
    }

    public Furniture getFurniture(Furniture[] furniture, int index) {
        return furniture[index];
    }

    public void setFurniture(Furniture[] furniture) {
        this.furniture = furniture;
    }

    public Furniture[] removeFurniture(Furniture[] furniture, int index){
        Furniture[] tempArray = new Furniture[furniture.length - 1];
        int indexNum = 0;
        for (int i = 0; i < tempArray.length; i++) {
            if (index == i) {
                indexNum = 1;
            }
            tempArray[i] = furniture[i + indexNum];
        }
        return tempArray;
    }
}
