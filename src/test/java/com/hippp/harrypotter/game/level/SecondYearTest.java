package com.hippp.harrypotter.game.level;

import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.house.House;
import org.junit.jupiter.api.Test;

class SecondYearTest {


    @Test
    void initIfGryffindor() {
        Wizard wizard = new Wizard("Harry Potter");
        wizard.setHouse(new House("Gryffindor"));
        SecondYear secondYear = new SecondYear(wizard);
        secondYear.init();
        assert secondYear.getAvailableEnemies().size() == 1;
        assert secondYear.getAvailableNPCs().size() == 1;
        assert secondYear.getAvailableObjects().size() == 8;
    }

    @Test
    void initIfNotGryffindor() {
        Wizard wizard = new Wizard("Harry Potter");
        wizard.setHouse(new House("Not Gryffindor"));
        SecondYear secondYear = new SecondYear(wizard);
        secondYear.init();
        assert secondYear.getAvailableEnemies().size() == 1;
        assert secondYear.getAvailableNPCs().size() == 1;
        assert secondYear.getAvailableObjects().size() == 8;
    }

    @Test
    void summonObject() {
    }

    @Test
    void checkStatus() {
    }
}