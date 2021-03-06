package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class ScaleMob extends AbstractMob{

    private double health;

    private final double frequency;
    private final double amplitude;


    private double phase = 2*Math.PI;

    public static final double defaultFrequency = 0.01;
    public static final double defaultAmplitude = 3;
    public static final double defaultHealth = 100;

    public static final double healthDecrement = 0.2;
    public static final double defaultScale = 4;
    public static final double explosionScale = 8;

    public static final double vSpeed = 1;

    public static List<Sprite> sprites = new ArrayList<Sprite>(){{
        add(new Sprite(32, 0, 1, SpriteSheet.entities));
    }};

    public ScaleMob(Vector2D pos, double scale, double health, double frequency, double amplitude){
        super(pos, Math.PI, sprites, scale);
        this.health = health;
        this.frequency = frequency;
        this.amplitude = amplitude;

    }

    @Override
    public void update(Input input) {
        this.scale = Math.abs(Math.sin(phase))*amplitude+1;
        this.phase = phase+frequency;

        this.phase += this.frequency;

        pos = pos.add(new Vector2D(0, vSpeed));


        killIfOut();

    }

    @Override
    public double getRadius() {
        return 16*scale;
    }
}
