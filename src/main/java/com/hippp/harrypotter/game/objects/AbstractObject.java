package com.hippp.harrypotter.game.objects;

import com.hippp.harrypotter.game.spell.normal.Spell;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class AbstractObject {

    @Getter
    @NonNull
    private String name;
    @Getter
    @NonNull
    private String description;
    @Getter
    @NonNull
    private boolean isUsable;
    @Getter
    @NonNull
    private boolean isConsumable;
    @Getter
    @NonNull
    private boolean isSettableInInventory;
    @Getter
    @NonNull
    private boolean isHoldable;
    @Getter
    boolean isHeld = false;
    @Getter
    @NonNull
    private boolean isDroppable;
    @Getter
    @NonNull
    private boolean isThrowable;
    @Getter
    private boolean hasBeenDestroyed = false;
    @Getter
    private long destroyedAt;
    @Getter
    @NonNull
    private int damageWhenThrown;
    @Getter
    @NonNull
    private double timeBeforeRespawn; // in seconds, 0 = no respawn

    @Getter
    @Setter
    private int[] position;
    @Getter
    private int size = 1;

    public AbstractObject takeObject(Spell spell) {
        if (this.isHoldable && spell.canMoveObject(this)) {
            return this;
        }
        return null;
    }

    public boolean isInteractable() {
        return this.isUsable || this.isHoldable || this.isDroppable || this.isThrowable;
    }


    public AbstractObject interact() {


        return null;
    }

    public boolean drop() {
        if (this.isDroppable && this.isHeld) {
            this.isHeld = false;
            return true;
        }
        return false;
    }

    public boolean throwObject() {
        if (this.isThrowable && this.isHeld) {
            this.isHeld = false;
            return true;
        }
        return false;
    }

    public void respawn() {
        if (this.isHasBeenDestroyed()
                && this.getTimeBeforeRespawn() > 0
                && this.getDestroyedAt() + this.getTimeBeforeRespawn() < System.currentTimeMillis()) {
            this.hasBeenDestroyed = false;

        }
    }


}
