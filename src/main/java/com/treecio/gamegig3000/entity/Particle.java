package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;

public class Particle extends Entity{

    private int vx;
    private int vy;

    public Particle(int x, int y, Sprite sprite, int vx, int vy){
        super(x, y, 0, sprite);
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public void update(Input input){
        x += vx;
        y += vy;

        /* TODO: check for out of screen and move to start.
        */
    }

}
