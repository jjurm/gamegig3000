package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Projectile extends Entity{

    private final double speed;

    public static Sprite bullet = new Sprite(32, 6, 0, SpriteSheet.entities);

    public Projectile(Vector2D pos, double angle, Sprite sprite, double scale, double speed){
        super(pos, angle, sprite, scale);
        this.speed = speed;

    }

    @Override
    public void update(Input input){
        Vector2D velocity = new Vector2D(-Math.sin(angle), -Math.cos(angle));
        pos = pos.add(velocity.scalarMultiply(speed));
    }
}
