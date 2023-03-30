package com.hippp.harrypotter.game.spell;

import lombok.Getter;

public abstract class AbstractSpell {
    @Getter
    private String name;

    public AbstractSpell(String name) {
        this.name = name;
    }
}
