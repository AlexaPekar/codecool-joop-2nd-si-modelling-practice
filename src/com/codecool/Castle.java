package com.codecool;

public class Castle {

    private String name;
    private Place[] places;
    private Character[] characters;

    public Castle(String name, Place[] places) {
        this.name = name;
        this.places = places;
        characters = new Character[0];
    }

    public Character createCharacter(Gender gender, String name) {
        Character character;
        switch (gender) {
            case MAN:
                character = new Man(gender, name);
                break;
            case WOMAN:
                character = new Woman(gender, name);
                break;
            default:
                throw new IllegalArgumentException();
        }
        addToCharacters(character);
        return character;
    }

    private void addToCharacters(Character character) {
        Character[] tempArray = new Character[characters.length + 1];
        for (int i = 0; i < characters.length; i++) {
            tempArray[i] = characters[i];
        }
        tempArray[tempArray.length - 1] = character;
        characters = tempArray;
    }
}