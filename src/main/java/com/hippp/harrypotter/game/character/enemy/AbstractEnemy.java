package com.hippp.harrypotter.game.character.enemy;

import com.hippp.harrypotter.game.character.Character;

public abstract class AbstractEnemy extends Character {
    public AbstractEnemy(String name, Integer life, Integer attackDamage) {
        super(name, life, attackDamage);
    }
}
