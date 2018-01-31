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
            System.out.println("\n\tYour character is too tired, cannot place the items to the right place, must stay in the castle forever!");
            System.exit(0);
        }
        System.out.println("\n\tEnergy changed by " + value + "!");
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int value) {
        strength += value;
        if (strength > 100) {
            strength = 100;
        }
        else if (strength <= 0) {
            System.out.println("\n\tYour character is too weak, cannot place the items to the right place, must stay in the castle forever!");
            System.exit(0);
        }
        System.out.println("\tStrength changed by " + value + "!\n");
    }

    public Furniture[] getStocks() {
        return stocks;
    }

    public void printStocks() {
        int counter = 0;
        for (Furniture stock : stocks) {
            System.out.print("[" + counter + "]" +stock.getName() + "(" + stock.getWeight() + "kg)  ");
            counter++;
        }
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

    public Furniture getStock(Furniture[] furniture, int index) {
        return furniture[index];
    }

    public void setStocks(Furniture[] furniture) {
        this.stocks = furniture;
    }

    public Furniture[] removeStock(int index){
        Furniture[] tempArray = new Furniture[stocks.length - 1];
        int indexNum = 0;
        for (int i = 0; i < tempArray.length; i++) {
            if (index == i) {
                indexNum = 1;
            }
            tempArray[i] = stocks[i + indexNum];
        }
        return tempArray;
    }
}