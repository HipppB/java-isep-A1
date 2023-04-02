package com.hippp.harrypotter.game.character.house;

import lombok.Getter;

@Getter()
public class House {

    @Getter()
    private String name;

    public House(String name) {
        this.name = name;
    }
}
