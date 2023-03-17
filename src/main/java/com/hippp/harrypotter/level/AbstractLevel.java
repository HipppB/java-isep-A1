package com.hippp.harrypotter.level;

public abstract class AbstractLevel {

     private String levelName;
     private int levelNumber;

    public AbstractLevel(String levelName, int levelNumber) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
    }


    private void onLevelUp() {}

}
