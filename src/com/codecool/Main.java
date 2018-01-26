package com.codecool;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        Castle newCastle = Castle.createNewCastle();
        System.out.println("Available commands: :list, :create, :find, :genders, :exit");
        while (true) {
            String line = scanner.nextLine();
            if (":exit".equals(line)) {
                break;

            } else if (":list".equals(line)) {
                handleList(newCastle.getCharacters());

            } else if (":genders".equals(line)) {
                handleGenders();

            } else if (":create".equals(line)) {
                handleCreate(newCastle);

            } else if (":find".equals(line)) {
                handleFind(newCastle);
            }
        }
        newCastle.quit();
    }

    private static void handleList(Character[] characters) {
        System.out.println("These characters are in the castle: ");
        if (characters.length == 0) {
            System.out.println("Nobody is in the castle.");
        } else {
            for (Character character : characters) {
                System.out.println("\t" + "Name: " + character.getName() + "    Energy: " + character.getEnergy() + "    Strength: " +character.getStrength());
            }
        }
    }

    private static void handleGenders() {
        System.out.println("The available genders to enter the castle: (case-sensitive)");
        for (Gender gender : Gender.values()) {
            System.out.println("\t" + gender);
        }
    }

    private static void handleCreate(Castle newCastle) {
        System.out.println("Please, enter the name of the character!");
        String name = scanner.nextLine();

        System.out.println("Please, enter the gender of your character! (see :genders)");
        String gender = scanner.nextLine();


        Character newCharacter = newCastle.createCharacter(name, 100, 0);

        newCastle.addToCharacters(newCharacter);

        System.out.println("Your character is created.");
    }

    private static void handleFind(Castle newCastle) {
        System.out.println("Please, enter the name of the character, you wanna find!");
        String name = scanner.nextLine();

        Character character = newCastle.findCharacter(name);
        if (character == null) {
            System.out.println("There's no such character in the castle!");
        } else {
            System.out.format("This character is found!    Name: " + character.getName() + "    Energy: " + character.getEnergy() + "    Strength: " +character.getStrength(), character.getName());
        }
    }





}