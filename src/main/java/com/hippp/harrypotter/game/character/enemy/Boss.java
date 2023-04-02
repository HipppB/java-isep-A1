package com.hippp.harrypotter.game.character.enemy;

import com.hippp.harrypotter.game.objects.AbstractObject;

public class Boss extends AbstractEnemy {
    AbstractObject[] objectNeededToKill;

    public Boss(String name, Integer life, Integer attackDamage) {
        super(name, life, attackDamage);
    }
}
