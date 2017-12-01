package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.Game;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;


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
        return pos.add(new Vector2D(Math.random()* 64, Math.random()*64));
    }

    private int moveX = 0;
    private int moveY = 0;

    @Override
    public void update(Input input) {

        Vector2D direction = Game.INSTANCE.getPlayer().pos.subtract(pos).normalize();

        angle = Math.atan2(direction.getY(), direction.getX())+Math.PI/2;
        pos = pos.add(direction.scalarMultiply(speed));

        /*if(Math.random() < relocateProbability) {
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
        }*/

        killIfOut();

    }

    @Override
    public double getRadius() {
        return 12*getScale();
    }
}
