package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Projectile extends Entity{

    private final double speed;

    public static List<Sprite> bullet = new ArrayList<Sprite>(){{
        add(new Sprite(32, 6, 0, SpriteSheet.entities));
    }};

    public Projectile(Vector2D pos, double angle, List<Sprite> sprites, double scale, double speed){
        super(pos, angle, sprites, scale);
        this.speed = speed;

    }

    @Override
    public void update(@NotNull Input input){
        Vector2D velocity = new Vector2D(-Math.sin(angle), -Math.cos(angle));
        pos = pos.add(velocity.scalarMultiply(speed));
    }

    @Override
    public double getRadius() {
        return 10;
    }
}
