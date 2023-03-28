package com.hippp.harrypotter.game.objects;

public class Rock extends AbstractObject {
    public Rock(boolean isHoldableAndThrowable, int timeBeforeRespawn) {
        super("rock", "I am a rock", false, false, false, isHoldableAndThrowable, false, isHoldableAndThrowable, 10, timeBeforeRespawn);
    }

    public Rock(boolean isHoldableAndThrowable) {
        super("rock", "I am a rock", false, false, false, isHoldableAndThrowable, false, isHoldableAndThrowable, 10, 0);
    }

    public Rock() {
        super("rock", "I am a rock", false, false, false, false, false, false, 10, 0);
    }

    @Override
    public AbstractObject interact() {
        if (this.isHoldable()) {
            this.isHeld = true;
            return this;
        }
        return null;
    }
}
