package com.codecool;

public class Character extends Thing {
    
    public int energy;
    public int strength;
    public Furniture[] stocks;

    public Character(String name, int energy, int strength, Furniture[] stocks) {
        super(name);
        this.energy = energy;
        this.strength = strength;
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int value) {
        energy += value;
        if (energy > 100) {
            energy = 100;
        }
        else if (energy <= 0) {
            System.out.println("Your character is too tired, cannot escape this way, must stay in the castle forever!");
            System.exit(0);
        }
        System.out.println("Energy changed by " + value + "!");
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int value) {
        strength += value;
        System.out.println("Strength changed by " + value + "!");
    }

    public Furniture[] getStocks() {
        return stocks;
    }

    public void addNewStock(Furniture newFurniture) {
        Furniture[] tempArray = new Furniture[stocks.length + 1];
        int counter = 0;
        for(Furniture actualStock : stocks) {
            tempArray[counter] = actualStock;
            counter++;
        }
        tempArray[tempArray.length - 1] = newFurniture;
        stocks = tempArray;
    }

    public Furniture removeStock(int index){
        Furniture[] tempArray = new Furniture[stocks.length - 1];
        int indexNum = 0;
        Furniture myFurniture = null;
        for (int i = 0; i < tempArray.length; i++) {
            if (index == i) {
                indexNum = 1;
                myFurniture = tempArray[i];
            }
            tempArray[i - indexNum] = stocks[i];
        }
        stocks = tempArray;
        return myFurniture;
    }



//    public Tool getTool() {
//        return tool;
//    }
/*
    public Tool[] getTools() {
        return tools;
    }

    public void addNewTool(Tool tool) {
        Tool[] tempTools = new Tool[tools.length + 1];
        int counter = 0;
        for(Tool actualTool : tools) {
            tempTools[counter] = actualTool;
            counter++;
        }
        tempTools[tempTools.length - 1] = tool;
        tools = tempTools;
    }
*/
}