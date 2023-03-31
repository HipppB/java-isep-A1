package com.hippp.harrypotter.game.character;

import lombok.Getter;

public abstract class Character {
    @Getter
    Integer life;

    @Getter
    Integer AttackDamage;

    @Getter
    String name;

    public Character(String name, Integer life, Integer attackDamage) {
        this.name = name;
        this.life = life;
        AttackDamage = attackDamage;
    }


    public void attackDamage(int i) {
        this.life -= i;
    }
}
