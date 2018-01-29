package com.codecool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Castle {

    public String name;
    public Character[] characters;
    public Place[] places;

    public Castle(String name, Character[] characters) {
        this.name = name;
        this.characters = characters;
    }

    public Castle(String name, String charactersPath) throws FileNotFoundException {
        this.name = name;
        this.characters = readCharacterFromFile(charactersPath);
    }

    static Castle createNewCastle() throws FileNotFoundException {
        return new Castle("Croft Manor", "../data/characters.csv");
    }

    public Character[] getCharacters(){
        return characters;
    }

    public int lineCounter(String csvPath) {
        int cnt = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            while ((reader.readLine()) != null) {
                cnt++;
            }
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return cnt;
    }

    public Character[] readCharacterFromFile(String csvPath) throws FileNotFoundException {
        int numOFLines = lineCounter(csvPath);
        int lineNumber = 0;
        String line = "";
        Character[] characters = new Character[numOFLines];
        try (BufferedReader characterReader = new BufferedReader(new FileReader(csvPath))) {
            while ((line = characterReader.readLine()) != null) {
                String[] attributes = line.split(",");
                characters[lineNumber] = createCharacter(attributes);
                lineNumber++;
            }
            characterReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return characters;
    }

    public Character createCharacter(String[] attributes) {
        Character character;
        character = new Character(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
        return character;
    }

    public Character createCharacter(String name, int energy, int strength) {
        return new Character(name, 100, 0);
    }

    public void addToCharacters(Character character) {
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
    }


    public void writeCharacterToFile() {
        String[] attributes = null;
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("../data/characters.csv"));
            StringBuilder sb = new StringBuilder();
            for (Character character : characters) {
                int counter = 0;
                attributes = recompressCharacter(character);
                for (String att : attributes) {
                    sb.append(att);
                    counter++;
                    if (counter != 10){
                        sb.append(",");
                    }
                }
                sb.append("\n");
            }
            String mystr = sb.toString();
            mystr = mystr.substring(0, mystr.length()-1);
            bw.write (mystr);
            bw.close();
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }
    }
    
    public String[] recompressCharacter(Character character) {
        String attributes[] = new String[3];
        attributes[0] = character.getName();
        attributes[1] = Integer.toString(character.getEnergy());
        attributes[2] = Integer.toString(character.getStrength());
        return attributes;
    }
}