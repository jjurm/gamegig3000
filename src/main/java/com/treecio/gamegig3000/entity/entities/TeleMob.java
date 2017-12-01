package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.entity.Entity;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TeleMob extends AbstractMob {
    private double health;
    private double speed;

    public static final double healthDecrement = 0.2;
    public static final double defaultHealth = 100;
    public static final double defaulsRelocateProbability = 0.01;
    public static final double defaultSpeed = 4;
    public static final double explosionScale = 6;


    private double relocateProbability;


    public static List<Sprite> sprites = new ArrayList<Sprite>(){{
        add(new Sprite(32, 7, 0, SpriteSheet.entities));
    }};

    public TeleMob(Vector2D pos, double health, double relocateProbability, double speed){
        super(pos, 0, sprites, 4);
        this.health = health;
        this.relocateProbability = relocateProbability;
        this.speed = speed;
    }


    public Vector2D randomPos(){
        return new Vector2D(32+ Math.random()* (App.Companion.getWIDTH()-32), 32 + Math.random()*(App.Companion.getHEIGHT()-32));
    }

    private int moveX = 0;
    private int moveY = 0;
    @Override
    public void update(Input input) {
        if(Math.random() < relocateProbability) {
            this.pos = randomPos();
            this.moveX = 0;
            this.moveY = 0;
        }

        if(this.moveX > 0) this.move(new Vector2D(1, 0));
        if(this.moveY > 0) this.move(new Vector2D(0, 1));

        if(this.moveX == 0 && this.moveY == 0) {
            Vector2D v = randomPos();
            moveX = (int) (v.getX()-pos.getX());
            moveY = (int) (v.getY() - pos.getY());
        }

        health-=healthDecrement;

        if(health<0){
            this.remove();
        }

    }

    @Override
    public double getRadius() {
        return 32*getScale();
    }
}
