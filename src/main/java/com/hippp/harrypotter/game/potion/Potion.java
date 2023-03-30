package com.hippp.harrypotter.game.potion;

import lombok.Getter;

public class Potion {

    @Getter
    private String name;

    public Potion(String name) {
        this.name = name;
    }
}
