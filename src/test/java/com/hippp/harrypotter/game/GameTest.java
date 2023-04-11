package com.hippp.harrypotter.game;


import com.hippp.harrypotter.game.level.AbstractLevel;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    @Test
    void Game() {
        Game game = new Game();
        this.giveWizardWandAndHouse(game);
        this.startGame(game);

    }

    private void startGame(Game game) {
        assert game.getCurrentLevel() == null;
        assert game.getWizard() != null;
        game.startFirstYear();
        this.firstYear(game);
        this.secondYear(game);
    }

    private void firstYear(Game game) {
        AbstractLevel level = game.getCurrentLevel();
        assert level != null;
        assert level.getAvailableObjects().size() == 3;
        assert level.getAvailableEnemies().size() == 1;
        assert level.getAvailableNPCs().size() == 4;
        this.checkStatus(level, true, false);
        assert level.getWizard().getLife() == 100;
        assert level.getWizard().getAttackDamage() == 10;
        level.getWizard().attackDamage(10);
        assert level.getWizard().getLife() == 90;


    }

    private void secondYear(Game game) {
        game.startSecondYear();
    }

    private void checkStatus(AbstractLevel level, boolean isRunning, boolean isLost) {
        level.checkStatus();
        assert level.isRunning() == isRunning;
        assert level.isLost() == isLost;
    }

    void newWizzard(Game game) {
        assert game.getWizard() == null;
        assert game.getName() == null;
        game.setName("Harry Potter");
        assert Objects.equals(game.getName(), "Harry Potter");
        game.setName(null);
        assert game.getName() == null;
        game.newWizzard();
        assert game.getWizard() != null;
        assert Objects.equals(game.getName(), "Harry Potter");

    }

    void giveWizardWandAndHouse(Game game) {
        assertThrows(RuntimeException.class, game::giveWizardWand);
        assertThrows(RuntimeException.class, game::assignHouseToWizard);
        this.newWizzard(game);
        game.giveWizardWand();
        assertThrows(RuntimeException.class, game::giveWizardWand);
        game.assignHouseToWizard();
        assertThrows(RuntimeException.class, game::assignHouseToWizard);

    }
}