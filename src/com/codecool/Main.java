package com.codecool;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("../data/characters.ser");
        Castle newCastle = Castle.createNewCastle();
        if (f.exists()) {
            newCastle.deserializeCharacters();
        }
        System.out.println(newCastle);
        System.out.println("Available commands: :listCharacters, :listPlaces :create, :find, :exit, :pickUpFurniture, :putDownFurniture, :chooseCharacter");
        while (true) {
            String line = scanner.nextLine();
            if (":exit".equals(line)) {
                break;

            } else if (":listCharacters".equals(line)) {
                handleListCharacters(newCastle.getCharacters());

            } else if (":listPlaces".equals(line)) {
                handleListPlaces(newCastle.getPlaces());

            } else if (":create".equals(line)) {
                handleCreate(newCastle);

            } else if (":find".equals(line)) {
                handleFind(newCastle);

            } else if (":chooseCharacter".equals(line)) {
                handleChooseCharacter(newCastle);

            } else if ("pickUpFurniture".equals(line)) {
                pickUpFurniture(newCastle);

            } else if ("putDownFurniture".equals(line)) {
                putDownFurniture(newCastle);
            }
        }
        newCastle.quit();
    }

    private static void handleChooseCharacter(Castle newCastle) {
        System.out.println("Please, enter the name of the character, you wanna play with!");
        String name = scanner.nextLine();
        Character myCharacter = newCastle.findCharacter(name);
        if (myCharacter == null) {
            System.out.println("There's no such character in the castle!");
        } else {
            newCastle.setActiveCharacter(myCharacter);
            System.out.println("You choosed your character!");
        }
    }

    private static void handleListCharacters(Character[] characters) {
        if (characters.length == 0) {
            System.out.println("Nobody is in the castle.");
        } else {
            System.out.println("These characters are in the castle: ");
            for (Character character : characters) {
                System.out.println("\tName: " + character.getName() + "\tEnergy: " + character.getEnergy() + "\tStrength: " +character.getStrength() + "\tStocks: " + character.getStocks());
            }
        }
    }

    private static void handleListPlaces(Place[] places) {
        if (places.length == 0) {
            System.out.println("The castle is empty.");
        } else {
            System.out.println("These places are in the castle: ");
            for (Place place : places) {
                System.out.println("\t" + "Name: " + place.getName() + "\tFurniture: " + Arrays.toString(place.getFurniture()));
            }
        }
    }

    private static void handleCreate(Castle newCastle) {
        System.out.println("Please, enter the name of the character!");
        String name = scanner.nextLine();
        newCastle.createCharacter(name, 100, 0, new Furniture[0]);
        System.out.println("Your character is created.");
    }

    private static void handleFind(Castle newCastle) {
        System.out.println("Please, enter the name of the character, you wanna find!");
        String name = scanner.nextLine();
        Character character = newCastle.findCharacter(name);
        if (character == null) {
            System.out.println("There's no such character in the castle!");
        } else {
            System.out.println("This character is found!\tName: " + character.getName() + "\tEnergy: " + character.getEnergy() + "\tStrength: " + character.getStrength() + "\tStocks: " + character.getStocks());
        }
    }

    private static void pickUpFurniture(Castle newCastle) {
        System.out.println("Add index of Place!");
        int indexPlace = scanner.nextInt();
        System.out.println("Add index of Furniture!");
        int indexFurniture = scanner.nextInt();
        Place[] tempPlaces = newCastle.getPlaces();
        Furniture[] tempFurniture = tempPlaces[indexPlace].getFurniture();
        Furniture newFurniture = Place.removeFurniture(tempFurniture, indexFurniture);
        newCastle.activeCharacter.addNewStock(newFurniture);
    }

    private static void putDownFurniture(Castle newCastle) {
        System.out.println("Add index of Place!");
        int indexPlace = scanner.nextInt();
        System.out.println("Add index of Furniture!");
        int indexFurniture = scanner.nextInt();
        Furniture newFurniture = newCastle.activeCharacter.removeStock(indexFurniture);
        Place[] tempPlaces = newCastle.getPlaces();
        Furniture[] tempFurniture = tempPlaces[indexPlace].getFurniture();
        Place.addNewFurniture(tempFurniture, newFurniture);
    }
}
