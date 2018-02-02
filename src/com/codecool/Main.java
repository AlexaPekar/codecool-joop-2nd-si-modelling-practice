package com.codecool;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.InputMismatchException;

class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("../data/characters.ser");
        File g = new File("../data/places.ser");

        Castle newCastle = Castle.createNewCastle();


        //examine if there's saved game
        if (g.exists()) {
            newCastle.deserializePlaces();
        }
        if (f.exists()) {
            newCastle.deserializeCharacters();
        }

        clearScreen();
        System.out.println("\n\n\tWelcome to the furnishing simulation!");
        System.out.println("\n\tThere's a huge mess in this castle!\n\n\tYour mission is to place all the furniture to their ideal location!");
        System.out.println("\n\tEnter any character to enter the castle!");
        System.out.print("\t");
        scanner.next();

        while (newCastle.winning == false) {
            clearScreen();

            newCastle.checkWinning();

            if (newCastle.activeCharacter == null) {
                System.out.println("\n\n\tPlease, choose a character to play with!\n");
            } else {
                System.out.println("\n\n\tThe ideal place of the furniture:");
                System.out.println("\tKitchen: [table] [chair] [cooker] [fridge]");
                System.out.println("\n\tBedroom: [bed] [bedside cabinet] [night light] [wardrobe]");
                System.out.println("\n\tBathroom: [toilet] [sink] [mirror] [tub]");
                System.out.println("\n\tLibrary: [bookshelf] [desk] [table lamp] [armchair]");
                System.out.println("\n\tLivingroom: [sofa] [fireplace] [piano] [coffe table]");
                System.out.println("\n\tTraining Ground: [rope] [treadmill] [stationary bike] [wall bars]");

                System.out.println("\n\tName: " + newCastle.activeCharacter.getName());
                System.out.println("\tGender: " + newCastle.activeCharacter.getGender());
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
            System.out.println("\t(6) Pick up furniture(energy:-20|strength:-weight)");
            System.out.println("\t(7) Put down furniture(strength:+weight)");
            System.out.println("\t(8) Heal character(energy:+30)");
            System.out.println("\t(0) Exit");
            System.out.println("\n\tPlease, choose an option!");
            System.out.print("\t");
            String line = scanner.nextLine();

            String back;
            clearScreen();
            switch (line) {
                case "1":
                    handleCreate(newCastle);
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "2":
                    handleChooseCharacter(newCastle);
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "3":
                    handleFind(newCastle);
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "4":
                    handleListCharacters(newCastle.getCharacters());
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "5":
                    handleListPlaces(newCastle.getPlaces());
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "6":
                    pickUpFurniture(newCastle);
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "7":
                    putDownFurniture(newCastle);
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "8":
                    handleHeal(newCastle);
                    System.out.println("\n\tEnter any character to go back!");
                    System.out.print("\t");
                    back = scanner.next();
                    break;
                case "0":
                    newCastle.quit();
                    break;
            }
        }
        newCastle.quit();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void handleHeal(Castle newCastle) {
        if (newCastle.activeCharacter == null) {
            System.out.println("\n\n\tPlease, choose a character you wanna play with before healing!");
        } else {
            newCastle.activeCharacter.setEnergy(30);
            newCastle.serializeCharacters(newCastle.characters);
        }
    }

    private static void handleChooseCharacter(Castle newCastle) {
        System.out.println("\n\n\tPlease, enter the name of the character, you wanna play with!");
        System.out.print("\t");
        String name = scanner.nextLine();
        Character myCharacter = newCastle.findCharacter(name);
        if (myCharacter == null) {
            System.out.println("\n\tThere's no such character in the castle!");
        } else {
            newCastle.setActiveCharacter(myCharacter);
            System.out.println("\n\tYou've chosen your character!");
        }
    }

    private static void handleListCharacters(Character[] characters) {
        if (characters.length == 0) {
            System.out.println("\n\n\tNobody is in the castle.");
        } else {
            System.out.println("\n\n\tThese characters are in the castle: ");
            for (Character character : characters) {
                System.out.print("\n\t\tName: " + character.getName() + "\tGender: " + character.getGender() + "\tEnergy: " + character.getEnergy() + "\tStrength: " + character.getStrength() + "\tStocks: ");
                character.printStocks();
                System.out.println();
            }
        }
    }

    private static void handleListPlaces(Place[] places) {
        if (places.length == 0) {
            System.out.println("\n\n\tThe castle is empty.");
        } else {
            System.out.println("\n\n\tThese places are in the castle: ");
            for (Place place : places) {
                System.out.print("\n\t\t" + "Name: " + place.getName() + "\tFurniture: ");
                place.printFurniture();
                System.out.println();
            }
        }
    }

    private static void handleCreate(Castle newCastle) {
        Gender gender;
        System.out.println("\n\n\tPlease, enter the name of the character!");
        System.out.print("\t");
        String name = scanner.nextLine();
        System.out.println("\n\n\tPlease, enter the gender of the character!(MAN/WOMAN)");
        System.out.print("\t");
        String genderAsString = scanner.nextLine();
        try {
            gender = Gender.valueOf(genderAsString);
        } catch (IllegalArgumentException ex) {
            System.out.println("\n\tNot valid gender!");
            return;
        }
        Character character = newCastle.findCharacter(name);
        if (character == null) {
            newCastle.createCharacter(name, gender, 100, 100, new Furniture[0]);
            System.out.println("\n\tYour character is created.");
            newCastle.serializeCharacters(newCastle.characters);
        } else {
            System.out.println("\n\tThis character is already in the castle.");
        }
    }

    private static void handleFind(Castle newCastle) {
        System.out.println("\n\n\tPlease, enter the name of the character, you wanna find!");
        System.out.print("\t");
        String name = scanner.nextLine();
        Character character = newCastle.findCharacter(name);
        if (character == null) {
            System.out.println("\n\tThere's no such character in the castle!");
        } else {
            System.out.print("\n\tThe given character is found!\n\tName: " + character.getName() + "\tGender: " + character.getGender() + "\tEnergy: " + character.getEnergy() + "\tStrength: " + character.getStrength() + "\tStocks: ");
            character.printStocks();
            System.out.println();
        }
    }

    private static void pickUpFurniture(Castle newCastle) {
        int indexPlace = -1;
        int indexFurniture = -1;
        if (newCastle.activeCharacter == null) {
            System.out.println("\n\n\tPlease, choose a character you wanna play with before picking up furniture!");
        } else {
            System.out.println("\n\n\tYou wanna pick up a furniture with character " + newCastle.activeCharacter.getName() + ".");
            System.out.println("\n\tEnter the index of the place you wanna pick up the furniture from!");
            newCastle.printPlaces();
            System.out.println();
            System.out.print("\t");

            try {
                indexPlace = scanner.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("Invalid input!");
                return;
            }

            if (indexPlace < 0 || indexPlace > newCastle.getPlaces().length - 1) {
                System.out.println("\n\tInvalid input!");

            } else {

                Place[] actualPlaces = newCastle.getPlaces();

                Furniture[] actualFurniture = actualPlaces[indexPlace].getFurniture();

                System.out.println("\n\tEnter the index of the furniture you wanna pick up!");
                System.out.print("\t");
                System.out.print(actualPlaces[indexPlace].getName() + "\n\tFurniture: ");
                actualPlaces[indexPlace].printFurniture();
                System.out.println();
                System.out.print("\t");

                try {
                    indexFurniture = scanner.nextInt();
                } catch (InputMismatchException a) {
                    System.out.println("Invalid input!");
                }

                if (indexFurniture < 0 || indexFurniture > actualPlaces[indexPlace].getFurniture().length - 1) {
                    System.out.println("\n\tInvalid input!");

                } else {

                    Furniture[] newFurniture = actualPlaces[indexPlace].removeFurniture(actualFurniture, indexFurniture);
                    actualPlaces[indexPlace].setFurniture(newFurniture);

                    Furniture pickedUpFurniture = actualPlaces[indexPlace].getFurniture(actualFurniture, indexFurniture);

                    newCastle.activeCharacter.addNewStock(pickedUpFurniture);

                    newCastle.activeCharacter.setEnergy(-20);
                    newCastle.activeCharacter.setStrength(0 - pickedUpFurniture.getWeight());
                    newCastle.serializeCharacters(newCastle.characters);
                    newCastle.serializePlaces(newCastle.places);
                }
            }
        }
    }

    private static void putDownFurniture(Castle newCastle) {
        int indexFurniture = -1;
        int indexPlace = -1;
        if (newCastle.activeCharacter == null) {
            System.out.println("\n\n\tPlease, choose a character you wanna play with before putting down furniture!");
        } else {
            System.out.println("\n\n\tYou wanna put down a furniture with character " + newCastle.activeCharacter.getName() + ".");

            System.out.println("\n\tEnter the index of the furniture you wanna put down!");
            System.out.println("\n\tFurniture in your stock: ");
            System.out.print("\t");
            newCastle.activeCharacter.printStocks();
            System.out.println();
            System.out.print("\t");

            try {
                indexFurniture = scanner.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("Invalid input!");
                return;
            }

            if (indexFurniture < 0 || indexFurniture > newCastle.activeCharacter.getStocks().length - 1) {
                System.out.println("\n\tInvalid input!");

            } else {

                System.out.println("\n\tEnter the index of the place you wanna put down the furniture!");
                newCastle.printPlaces();
                System.out.println();
                System.out.print("\t");

                try {
                    indexPlace = scanner.nextInt();
                } catch (InputMismatchException a) {
                    System.out.println("Invalid input!");
                }

                if (indexPlace < 0 || indexPlace > newCastle.getPlaces().length - 1) {
                    System.out.println("\n\tInvalid input!");

                } else {

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
                    newCastle.serializeCharacters(newCastle.characters);
                    newCastle.serializePlaces(newCastle.places);
                }
            }
        }
    }
}
