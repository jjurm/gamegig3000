package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class Entity {

    public Vector2D pos;
    public double angle = 0;
    public Sprite sprite;
    private boolean removed = false;
    private double scale;

    public Entity(Vector2D pos, double angle, Sprite sprite, double scale) {
        this.pos = pos;
        this.sprite = sprite;
        this.angle = angle;
        this.scale = scale;
    }

    public abstract void update(Input input);

    public void render(Graphics2D graphics) {
        drawRadius(graphics);

        AffineTransform at = new AffineTransform();

        at.translate(pos.getX(), pos.getY());
        at.rotate(angle);
        at.scale(scale, scale);
        at.translate(-sprite.getSize() / 2, -sprite.getSize() / 2);

        graphics.drawImage(sprite.getImage(), at, null);
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

    public abstract double getRadius();

    public void drawRadius(Graphics2D g) {
        g.setColor(new Color(255, 0, 0, 50));
        double r = getRadius();
        g.fillOval((int) (pos.getX() - r / 2), (int) (pos.getY() - r / 2), (int) r, (int) r);
    }
}
