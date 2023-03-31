package com.hippp.harrypotter.game.character.enemy;

import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.actions.ActionAttack;


public class Enemy extends AbstractEnemy {

    public Enemy(String name, Integer life, Integer attackDamage) {
        super(name, life, attackDamage);
    }

    public ActionAbstract getAttackAction() {
        return new ActionAttack(super.getAttackDamage());
    }
}
