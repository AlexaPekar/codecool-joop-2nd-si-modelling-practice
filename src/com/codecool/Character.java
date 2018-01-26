package com.codecool;

public class Character extends Thing {
    
    public int energy;
    public int strength;

    public Character(String name, int energy, int strength) {
        super(name);
        this.energy = energy;
        this.strength = strength;
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

    public void setStrengthI(int value) {
        strength += value;
        System.out.println("Strength changed by " + value + "!");
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