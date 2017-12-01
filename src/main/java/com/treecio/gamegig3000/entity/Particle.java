package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Particle extends Entity {

    public static Sprite star = new Sprite(32, 4, 0, SpriteSheet.entities);

    private Vector2D v;

    private static final int MARGIN = 32;

    public Particle(Vector2D pos, Vector2D v){
        super(pos, 0, star);
        this.v = v;
    }

    @Override
    public void update(Input input){
        pos = pos.add(v);

        // check for out of screen and move to start.
        if (pos.getY() > App.Companion.getHEIGHT() + MARGIN) {
            pos = new Vector2D(pos.getX(), - MARGIN);
        }
    }

}
