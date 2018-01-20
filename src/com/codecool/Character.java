package com.codecool;

public class Character extends Thing {
    
    protected Gender gender;
    protected int bravery;
    protected int stamina;
    protected int energy;
    protected Tool[] tools;

    protected Character(String name, Gender gender, int bravery, int stamina, int energy, Tool[] tools) {
        super(name);
        this.gender = gender;
        this.bravery = bravery;
        this.stamina = stamina;
        this.energy = energy;
        this.tools = tools;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getBravery() {
        return bravery;
    }

    public void setBravery(int value) {
        bravery += value;
        if (bravery > 100) {
            bravery = 100;
        }
        else if (bravery <= 0) {
            System.out.println("Your character is too scared, cannot escape this way, must stay in the castle forever!");
            System.exit(0);
        }
        System.out.println("Bravery changed by " + value + "!");
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int value) {
        stamina += value;
        if (stamina > 100) {
            stamina = 100;
        }
        else if (stamina <= 0) {
            System.out.println("Your character is too weak, cannot escape this way, must stay in the castle forever!");
            System.exit(0);
        }
        System.out.println("Stamina changed by " + value + "!");
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
}