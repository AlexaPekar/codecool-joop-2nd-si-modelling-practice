package com.codecool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Castle {

    public String name;
    public Character[] characters;
    public Place[] places;
    public Character activeCharacter;

    public Castle(String name, Place[] places) {
        this.name = name;
        this.characters = new Character[0];
        this.places = places;
    }

    public static Castle createNewCastle() {
        Place[] places = new Place[6];
        places[0] = Place.createKitchen();
        places[1] = Place.createBedroom();
        places[2] = Place.createBathroom();
        places[3] = Place.createLibrary();
        places[4] = Place.createLivingroom();
        places[5] = Place.createTrainingGround();
        return new Castle("Croft Manor", places);
    }

    public void setActiveCharacter(Character character) {
        activeCharacter = character;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public Place[] getPlaces() {
        return places;
    }

    public void deserializeCharacters() {
        try {
            FileInputStream fileIn = new FileInputStream("../data/characters.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            characters = (Character[]) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Character class not found");
            c.printStackTrace();

        }
    }

    public void serializeCharacters(Character[] characters) {
        try {
            FileOutputStream fileOut = new FileOutputStream("../data/characters.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(characters);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in characters.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void createCharacter(String name, int energy, int strength, Furniture[] stocks) {
        addToCharacters(new Character(name, 100, 0, new Furniture[0]));
    }

    private void addToCharacters(Character character) {
        Character[] tempArray = new Character[characters.length + 1];
        for (int i = 0; i < characters.length; i++) {
            tempArray[i] = characters[i];
        }
        tempArray[tempArray.length - 1] = character;
        characters = tempArray;
    }

    public Character findCharacter(String name) {
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }

    public void quit() {
        System.out.println("See you again!");
        serializeCharacters(characters);
    }

    public void deserializePlaces() {
        try {
            FileInputStream fileIn = new FileInputStream("../data/places.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            characters = (Character[]) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Place class not found");
            c.printStackTrace();

        }
    }
/*
    public void serializeCharacters(Character[] characters) {
        try {
            FileOutputStream fileOut = new FileOutputStream("../data/characters.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(characters);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in characters.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
*/
}
