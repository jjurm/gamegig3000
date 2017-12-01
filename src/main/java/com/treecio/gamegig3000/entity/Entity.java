package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Renderable;
import com.treecio.gamegig3000.Updatable;
import com.treecio.gamegig3000.graphics.Sprite;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

public abstract class Entity implements Renderable, Updatable {

    private boolean alive = true;
    public Vector2D pos;
    public double angle = 0;
    public List<Sprite> sprites;
    private boolean removed = false;

    public boolean isAlive() {
        return alive;
    }

    public double getScale() {
        return scale;
    }

    private double scale;

    protected int spriteState;
    protected int spriteCount;

    public Entity(Vector2D pos, double angle, List<Sprite> sprites, double scale) {
        this.pos = pos;
        this.sprites = sprites;
        this.angle = angle;
        this.scale = scale;
        this.spriteState = 0;
        spriteCount = sprites.size();
    }

    @Override
    public void render(@NotNull Graphics2D graphics) {
        drawRadius(graphics);
        AffineTransform at = new AffineTransform();
        Sprite currentSprite = sprites.get(spriteState);

        at.translate(pos.getX(), pos.getY());
        at.rotate(angle);
        at.scale(scale, scale);
        at.translate(-currentSprite.getSize() / 2, -currentSprite.getSize() / 2);

        graphics.drawImage(currentSprite.getImage(), at, null);
    }


    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    protected void move(Vector2D move) {
        pos = pos.add(move);
    }

    protected void killIfOut() {
        double d = getRadius() * 2;
        if (pos.getX() < -d || pos.getX() > App.Companion.getWIDTH() + d
                || pos.getY() < -d || pos.getY() > App.Companion.getHEIGHT() + d) {
            kill();
        }
    }

    public abstract double getRadius();

    public void drawRadius(Graphics2D g) {
        g.setColor(new Color(255, 0, 0, 50));
        double r = getRadius();
        g.fillOval((int) (pos.getX() - r), (int) (pos.getY() - r), (int) r * 2, (int) r * 2);
    }

    public boolean collidesWith(Entity entity) {
        return isAlive() && entity.isAlive() && pos.distance(entity.pos) < (getRadius() + entity.getRadius());
    }

    public void kill() {
        alive = false;
    }

}
