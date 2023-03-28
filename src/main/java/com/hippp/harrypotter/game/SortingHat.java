package com.hippp.harrypotter.game;

import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.house.House;

import java.util.Random;

public class SortingHat {

    public static House assign(Wizard wizard){

        System.out.println("Sorting Hat is choosing the perfect house for you");

        // Create an array
        String[] houses = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};

        Random rand = new Random();
        // return the house
        return new House(houses[rand.nextInt(houses.length)]);
    }
}
