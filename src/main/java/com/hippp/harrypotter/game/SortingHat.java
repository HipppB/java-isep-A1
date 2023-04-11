package com.hippp.harrypotter.game;

import com.hippp.harrypotter.game.character.house.House;

import java.util.Random;

public class SortingHat {

    public static House assign() {
        String[] houses = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};
        Random rand = new Random();
        return new House(houses[rand.nextInt(houses.length)]);
    }
}
