package com.codecool;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("../data/characters.ser");
        File g = new File("../data/places.ser");

        Castle newCastle = Castle.createNewCastle();


        //examine if there's saved game
        if (g.exists()){
            newCastle.deserializePlaces();
        }
        if (f.exists()) {
            newCastle.deserializeCharacters();
        }
        newCastle.checkWinning();
        System.out.println(newCastle.winning);

        while (newCastle.winning == false) {
            if (newCastle.activeCharacter == null) {
                System.out.println("\n\n\tPlease, choose a character to play with!\n");
            } else {
                System.out.println("\n\n\tName: " + newCastle.activeCharacter.getName());
                System.out.println("\tEnergy: 100/" + newCastle.activeCharacter.getEnergy());
                System.out.println("\tStrength: 100/" + newCastle.activeCharacter.getStrength());
                System.out.print("\tStocks: ");
                newCastle.activeCharacter.printStocks();
                System.out.println("\n");
            }

            System.out.println("\t(1) Create new character");
            System.out.println("\t(2) Choose character to play with");
            System.out.println("\t(3) Find character");
            System.out.println("\t(4) List characters");
            System.out.println("\t(5) List places");
            System.out.println("\t(6) Pick up furniture");
            System.out.println("\t(7) Put down furniture");
            System.out.println("\t(8) Heal character(energy++)");            
            System.out.println("\t(0) Exit");
            String line = scanner.nextLine();
            if ("0".equals(line)) {
                break;

            } else if ("1".equals(line)) {
                handleCreate(newCastle);
                newCastle.serializeCharacters(newCastle.characters);

            } else if ("2".equals(line)) {
                handleChooseCharacter(newCastle);

            } else if ("3".equals(line)) {
                handleFind(newCastle);

            } else if ("4".equals(line)) {
                handleListCharacters(newCastle.getCharacters());

            } else if ("5".equals(line)) {
                handleListPlaces(newCastle.getPlaces());

            } else if ("6".equals(line)) {
                pickUpFurniture(newCastle);
                newCastle.serializeCharacters(newCastle.characters);
                newCastle.serializePlaces(newCastle.places);

            } else if ("7".equals(line)) {
                putDownFurniture(newCastle);
                newCastle.serializeCharacters(newCastle.characters);
                newCastle.serializePlaces(newCastle.places);

            } else if ("8".equals(line)) {
                handleHeal(newCastle);
            }
        }
        newCastle.quit();
    }

    private static void handleHeal(Castle newCastle) {
        if (newCastle.activeCharacter == null) {
            System.out.println("\tPlease, choose a character you wanna play with before healing");
        } else {
            newCastle.activeCharacter.setEnergy(30);
        }
    }

    private static void handleChooseCharacter(Castle newCastle) {
        System.out.println("\tPlease, enter the name of the character, you wanna play with!");
        String name = scanner.nextLine();
        Character myCharacter = newCastle.findCharacter(name);
        if (myCharacter == null) {
            System.out.println("\tThere's no such character in the castle!");
        } else {
            newCastle.setActiveCharacter(myCharacter);
            System.out.println("\tYou've chosen your character!");
        }
    }

    private static void handleListCharacters(Character[] characters) {
        if (characters.length == 0) {
            System.out.println("\tNobody is in the castle.");
        } else {
            System.out.println("\tThese characters are in the castle: ");
            for (Character character : characters) {
                System.out.print("\t\tName: " + character.getName() + "\tEnergy: " + character.getEnergy() + "\tStrength: " +character.getStrength() + "\tStocks: ");
                character.printStocks();
                System.out.println();
            }
        }
    }

    private static void handleListPlaces(Place[] places) {
        if (places.length == 0) {
            System.out.println("\tThe castle is empty.");
        } else {
            System.out.println("\tThese places are in the castle: ");
            for (Place place : places) {
                System.out.print("\t\t" + "Name: " + place.getName() + "\tFurniture: ");
                place.printFurniture();
                System.out.println();
            }
        }
    }

    private static void handleCreate(Castle newCastle) {
        System.out.println("\tPlease, enter the name of the character!");
        String name = scanner.nextLine();
        newCastle.createCharacter(name, 100, 0, new Furniture[0]);
        System.out.println("\tYour character is created.");
    }

    private static void handleFind(Castle newCastle) {
        System.out.println("\tPlease, enter the name of the character, you wanna find!");
        String name = scanner.nextLine();
        Character character = newCastle.findCharacter(name);
        if (character == null) {
            System.out.println("\tThere's no such character in the castle!");
        } else {
            System.out.print("\tThe given character is found!\n\tName: " + character.getName() + "\tEnergy: " + character.getEnergy() + "\tStrength: " + character.getStrength() + "\tStocks: ");
            character.printStocks();
            System.out.println();
        }
    }

    private static void pickUpFurniture(Castle newCastle) {
        if (newCastle.activeCharacter == null) {
            System.out.println("\tPlease, choose a character you wanna play with before picking up furniture!");
        } else {
            System.out.println("\tYou wanna pick up a furniture with character " + newCastle.activeCharacter.getName() + ".");
            System.out.println("\tEnter the index of the place you wanna pick up the furniture from!");
            newCastle.printPlaces();
            int indexPlace = scanner.nextInt();

            Place[] actualPlaces = newCastle.getPlaces();

            Furniture[] actualFurniture = actualPlaces[indexPlace].getFurniture();

            System.out.println("\tEnter the index of the furniture you wanna pick up!");
            System.out.print(actualPlaces[indexPlace].getName() + "\nFurniture: ");
            actualPlaces[indexPlace].printFurniture();
            System.out.println();
            int indexFurniture = scanner.nextInt();

            Furniture[] newFurniture = actualPlaces[indexPlace].removeFurniture(actualFurniture, indexFurniture);
            actualPlaces[indexPlace].setFurniture(newFurniture);

            Furniture pickedUpFurniture = actualPlaces[indexPlace].getFurniture(actualFurniture, indexFurniture);
            //new furniture in place
            for (int i =0; i<newFurniture.length; i++) {
                System.out.println(i+ ". " + newFurniture[i].getName() + " " +newFurniture[i].getWeight());
            }

            System.out.println(pickedUpFurniture.getName());
            newCastle.activeCharacter.addNewStock(pickedUpFurniture);

            //stocks of character
            Furniture[] stocks = newCastle.activeCharacter.getStocks();
            for (Furniture stock:stocks) {
                stock.getName();
            }

            newCastle.activeCharacter.setEnergy(-20);
            newCastle.activeCharacter.setStrength(0 - pickedUpFurniture.getWeight());
        }
    }

    private static void putDownFurniture(Castle newCastle) {
        if (newCastle.activeCharacter == null) {
            System.out.println("\tPlease, choose a character you wanna play with before putting down furniture!");
        } else {
            System.out.println("\tYou wanna put down a furniture with character " + newCastle.activeCharacter.getName() + ".");
            
            System.out.println("\tEnter the index of the furniture you wanna put down!");
            System.out.println("\tFurniture in your stock: ");
            newCastle.activeCharacter.printStocks();
            System.out.println();
            int indexFurniture = scanner.nextInt();
            
            System.out.println("\n\tEnter the index of the place you wanna put down the furniture!");
            newCastle.printPlaces();
            int indexPlace = scanner.nextInt();

            Furniture[] actualStock = newCastle.activeCharacter.getStocks();

            //remove furniture from character's stock
            Furniture[] newStocks = newCastle.activeCharacter.removeStock(indexFurniture);
            newCastle.activeCharacter.setStocks(newStocks);

            //get removed furniture
            Furniture putDownFurniture = newCastle.activeCharacter.getStock(actualStock, indexFurniture);

            Place[] actualPlaces = newCastle.getPlaces();

            Furniture[] actualFurniture = actualPlaces[indexPlace].getFurniture();
            
            Furniture[] newFurniture = actualPlaces[indexPlace].addNewFurniture(actualFurniture, putDownFurniture);
            actualPlaces[indexPlace].setFurniture(newFurniture);

            newCastle.activeCharacter.setStrength(putDownFurniture.getWeight());
        }
    }
}
