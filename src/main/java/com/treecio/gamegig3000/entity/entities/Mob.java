package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.Constants;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.entity.Entity;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Mob extends AbstractMob {


    public static List<Sprite> mob = new ArrayList<Sprite>(){{
        add(new Sprite(32, 1, 0,SpriteSheet.entities));
        add(new Sprite(32, 2, 0,SpriteSheet.entities));
        add(new Sprite(32, 3, 0,SpriteSheet.entities));
    }};


    private final double frequency;
    private final double amplitude;
    private final double speed;
    private double health;


    private double phase = 2*Math.PI;

    public static final double defaultFrequency = 0.1;
    public static final double defaultAmplitude = 2;
    public static final double defaultHealth = 100;
    public static final double explosionScale = 100;

    public static final double healthDecrement = 0.2;
    public static final double defaultSpeed = 4;

    /**
     * @param pos
     * @param angle
     * @param frequency how fast does it change direction, relative to frames, divide the number by the number of frames to get per seconds
     * @param amplitude how much does it move to sides
     */
    public Mob(Vector2D pos, double angle, double frequency, double amplitude, double scale, double speed, double health) {
        super(pos, angle, mob, scale);
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.speed = speed;
        this.health = health;
        this.health = health;

    }

    public Mob(Vector2D pos, double angle, double scale) {
        super(pos, angle, mob, scale);
        this.amplitude = defaultAmplitude;
        this.frequency = defaultFrequency;
        this.speed = defaultSpeed;
        this.health = defaultHealth;
    }

    @Override
    public void update(@NotNull Input input) {
        this.move(new Vector2D(Math.cos(phase)*amplitude, speed));
        this.phase = phase+frequency;

        health-=healthDecrement;

        if(health<0){
            this.remove();
        }

        if(Math.random() < 0.1) spriteState++;
        spriteState %= spriteCount;

        killIfOut();
    }

    @Override
    public double getRadius() {
        return 40;
    }



}
