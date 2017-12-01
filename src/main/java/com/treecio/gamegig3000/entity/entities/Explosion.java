package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.entity.Entity;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Explosion extends Entity {

    public static List<Sprite> sprites = new ArrayList<Sprite>(){{
        add(new Sprite(32, 0, 2,SpriteSheet.entities));
        add(new Sprite(32, 1, 2, SpriteSheet.entities));
        add(new Sprite(32, 2, 2,SpriteSheet.entities));
        add(new Sprite(32, 3, 2,SpriteSheet.entities));
        add(new Sprite(32, 4, 2,SpriteSheet.entities));
        add(new Sprite(32, 5, 2,SpriteSheet.entities));
    }};

    public static List<Sprite> sprites_short = new ArrayList<Sprite>(){{
        add(new Sprite(32, 0, 2,SpriteSheet.entities));
        add(new Sprite(32, 1, 2, SpriteSheet.entities));
        add(new Sprite(32, 2, 2,SpriteSheet.entities));
    }};

    public static final double explosionSpeed = 0.5;
    public static final double explosionGrowth = 0.01;


    public Explosion(Vector2D pos, double angle, List<Sprite> sprites, double scale) {
        super(pos, angle, sprites, scale);
    }

    @Override
    public double getRadius() {
        return 0;
    }

    public void update(@NotNull Input input) {


        if(Math.random() < explosionSpeed) spriteState++;

        spriteState%=spriteCount;
        this.scale += explosionGrowth;

        if(spriteState >= spriteCount-1) remove();
        killIfOut();
    }

    @Override
    public boolean collidesWith(Entity entity) {
        return false;
    }
}
