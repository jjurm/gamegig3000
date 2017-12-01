package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Particle extends Entity {

    public static List<Sprite> star = new ArrayList<Sprite>(){{
        add(new Sprite(32, 4, 0, SpriteSheet.entities));
    }};

    private Vector2D v;
    private double scale;

    private static final int MARGIN = 32;

    public Particle(Vector2D pos, Vector2D v, double scale){
        super(pos, 0, star, scale);
        this.v = v;
        this.scale = scale;
    }

    @Override
    public void update(Input input){
        pos = pos.add(v);

        // check for out of screen and move to start.
        if (pos.getY() > App.Companion.getHEIGHT() + MARGIN) {
            pos = new Vector2D(pos.getX(), - MARGIN);
        }
    }

    @Override
    public double getRadius() {
        return scale * 16 * 4;
    }
}
