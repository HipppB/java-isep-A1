package com.hippp.harrypotter.game.level;

import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.enemy.AbstractEnemy;
import com.hippp.harrypotter.game.objects.AbstractObject;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractLevel {

    @Getter
    boolean isRunning;
    @Getter
    @Setter
    private AbstractObject[] availableObjects;

    @Getter
    @Setter
    private AbstractEnemy[] availableEnemies;

    @Getter
    @Setter
    private NPC[] availableNPCs;
    private String levelName;
    private int levelNumber;
    private Wizard wizard;

    public AbstractLevel(String levelName, int levelNumber, Wizard wizard) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
    }

    public void init() {
        // TODO: init level
    }

    public void start() {
        // TODO: start level
    }

    private void onLevelUp() {
    }

}
