package com.hippp.harrypotter.game.level;

import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.enemy.AbstractEnemy;
import com.hippp.harrypotter.game.objects.AbstractObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel {

    @Getter
    protected boolean isLost;
    @Getter
    protected boolean isWon;
    @Getter
    boolean isRunning;
    @Getter
    @Setter
    private List<AbstractObject> availableObjects;
//    private AbstractObject[] availableObjects2;

    @Getter
    @Setter
    private List<AbstractEnemy> availableEnemies;

    @Getter
    @Setter
    private List<NPC> availableNPCs;
    private String levelName;
    @Getter
    private int levelNumber;
    @Getter
    private Wizard wizard;

    public AbstractLevel(String levelName, int levelNumber, Wizard wizard) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
        this.wizard = wizard;
    }

    public void init() {
        // TODO: init level
    }

    public void start() {
        // TODO: start level
    }

    public void addAvailableObject(AbstractObject object) {
        if (this.availableObjects == null) {
            this.availableObjects = new ArrayList<>();
        }
        this.availableObjects.add(object);
    }

    private void onLevelUp() {
    }


}
