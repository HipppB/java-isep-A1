package com.hippp.harrypotter.game;

import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.wand.Wand;
import com.hippp.harrypotter.game.level.AbstractLevel;
import com.hippp.harrypotter.game.level.FirstYear;
import lombok.Getter;
import lombok.Setter;

public class Game {

    @Getter
    @Setter
    String name;

    @Getter
    Wizard wizard;

    @Getter
    AbstractLevel currentLevel;

    public Game() {

    }

    public void newWizzard() {
        if (this.name == null) {
            this.name = "Harry Potter";
        }
        this.wizard = new Wizard(this.name);
    }

    public void giveWizardWand() {
        if (this.wizard == null) {
            throw new RuntimeException("No wizard found");
        }
        if (this.wizard.getWand() != null) {
            throw new RuntimeException("Wizard already has a wand");
        }
        Wand wand = new Wand();
        this.wizard.setWand(wand);
    }

    public void assignHouseToWizard() {
        if (this.wizard == null) {
            throw new RuntimeException("No wizard found");
        }
        if (this.wizard.getHouse() != null) {
            throw new RuntimeException("Wizard already has a house");
        }
        SortingHat sortingHat = new SortingHat();
        this.wizard.setHouse(sortingHat.assign(this.wizard));
    }


    public void startFirstYear() {
        this.wizard.setYear(1);
        FirstYear firstYear = new FirstYear(this.wizard);
        this.currentLevel = firstYear;

        currentLevel.init();

        currentLevel.start();

    }

    public void quit() {
        System.exit(0);
    }


}
