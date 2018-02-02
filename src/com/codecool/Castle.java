package com.codecool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Castle {

    private String name;
    public Character[] characters;
    public Place[] places;
    public Character activeCharacter;
    public boolean winning;

    private Castle(String name, Place[] places) {
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

    public void printPlaces() {
        int counter = 0;
        for (Place place : places) {
            System.out.println("\t[" + counter + "] " + place.getName());
            counter++;
        }
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
            System.out.println("\n\tCharacter class not found!");
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
            System.out.println("\tSerialized data of the character is saved in characters.ser.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void createCharacter(String name, Gender gender, int energy, int strength, Furniture[] stocks) {
        Character character;
        switch (gender) {
            case MAN:
                character = new Character(name, gender, 80, 100, new Furniture[0]);
                break;
            case WOMAN:
                character = new Character(name, gender, 100, 80, new Furniture[0]);
                break;
            default:
                throw new IllegalArgumentException();
        }
        addToCharacters(character);
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
        if (winning == true) {
            System.out.println("\tCongrats, everything is in place, you're the freakin' WINNER!");
        }
        System.out.println("\n\n\tSee you again!");
        System.exit(0);
    }

    public void deserializePlaces() {
        try {
            FileInputStream fileIn = new FileInputStream("../data/places.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            places = (Place[]) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("\tPlace class not found!");
            c.printStackTrace();

        }
    }

    public void serializePlaces(Place[] places) {
        try {
            FileOutputStream fileOut = new FileOutputStream("../data/places.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(places);
            out.close();
            fileOut.close();
            System.out.println("\tSerialized data of the place is saved in places.ser.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private boolean contains(String[] items, String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void checkWinning() {
        int counter = 0;
        int fakeCounter = 0;
        String[] idealKitchenFurni = {"table", "chair", "cooker", "fridge"};
        String[] idealBedroomFurni = {"bed", "bedside cabinet", "night light", "wardrobe"};
        String[] idealBathroomFurni = {"toilet", "sink", "mirror", "tub"};
        String[] idealLibraryFurni = {"bookshelf", "desk", "table lamp", "armchair"};
        String[] idealLivingroomFurni = {"sofa", "fireplace", "piano", "coffe table"};
        String[] idealTrainingFurni = {"rope", "treadmill", "stationary bike", "wall bars"};

        Furniture[] kitchenFurniture = places[0].getFurniture();
        for (Furniture furni : kitchenFurniture) {
            if (contains(idealKitchenFurni, furni.getName())) {
                fakeCounter = 0;
            } else {
                counter++;
            }
        }
        Furniture[] bedroomFurniture = places[1].getFurniture();
        for (Furniture furni : bedroomFurniture) {
            if (contains(idealBedroomFurni, furni.getName())) {
                fakeCounter = 0;
            } else {
                counter++;
            }
        }
        Furniture[] bathroomFurniture = places[2].getFurniture();
        for (Furniture furni : bathroomFurniture) {
            if (contains(idealBathroomFurni, furni.getName())) {
                fakeCounter = 0;
            } else {
                counter++;
            }
        }
        Furniture[] libraryFurniture = places[3].getFurniture();
        for (Furniture furni : libraryFurniture) {
            if (contains(idealLibraryFurni, furni.getName())) {
                fakeCounter = 0;
            } else {
                counter++;
            }
        }
        Furniture[] livingroomFurniture = places[4].getFurniture();
        for (Furniture furni : livingroomFurniture) {
            if (contains(idealLivingroomFurni, furni.getName())) {
                fakeCounter = 0;
            } else {
                counter++;
            }
        }
        Furniture[] trainingFurniture = places[5].getFurniture();
        for (Furniture furni : trainingFurniture) {
            if (contains(idealTrainingFurni, furni.getName())) {
                fakeCounter = 0;
            } else {
                counter++;
            }
        }
        if (counter != 0) {
            winning = false;
        } else {
            winning = true;
        }
    }
}
