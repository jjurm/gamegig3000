package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.Constants;
import com.treecio.gamegig3000.entity.Entity;
import com.treecio.gamegig3000.graphics.Sprite;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.List;

public abstract class AbstractMob extends Entity {

    public AbstractMob(Vector2D pos, double angle, List<Sprite> sprites, double scale) {
        super(pos, angle, sprites, scale);
    }

    public double getDamage() {
        return getScale() * Constants.DAMAGE_K;
    }

}
