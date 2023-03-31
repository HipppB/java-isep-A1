package com.hippp.harrypotter.game.actions;

import com.hippp.harrypotter.game.character.Character;
import lombok.Getter;

public class ActionAttack extends ActionAbstract {
    @Getter
    private int damage;

    private ActionTalk actionTalk;

    public ActionAttack(int damage) {
        super("Attack", ActionTypes.FIGHT, 0);
        this.damage = damage;
    }

    public int execute(Character character) {
        character.attackDamage(this.damage);
        return this.damage;
    }

}
