package com.hippp.harrypotter.game.character;

import com.hippp.harrypotter.game.objects.AbstractObject;
import lombok.Getter;

import java.util.HashMap;

public abstract class Character {
    @Getter
    Integer life;

    @Getter
    Integer AttackDamage;

    @Getter
    String name;

    @Getter
    HashMap<String, AbstractObject> objectsNeeededToKill = new HashMap<>();

    public Character(String name, Integer life, Integer attackDamage) {
        this.name = name;
        this.life = life;
        AttackDamage = attackDamage;
    }


    public void attackDamage(int i) {
        if (objectsNeeededToKill.size() > 0) {
            return;
        } else {
            this.life -= i;
        }
    }

    public void addObjectNeededToKill(AbstractObject object) {
        this.objectsNeeededToKill.put(object.getName(), object);
    }


}
