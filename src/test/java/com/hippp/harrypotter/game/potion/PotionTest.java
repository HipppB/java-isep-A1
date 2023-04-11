package com.hippp.harrypotter.game.potion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PotionTest {
    @Test
    void constructor() {
        Potion potion = new Potion("Potion");
        assertEquals("Potion", potion.getName());
    }

}