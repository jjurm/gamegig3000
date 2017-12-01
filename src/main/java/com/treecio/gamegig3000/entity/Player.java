package com.treecio.gamegig3000.entity;


import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.input.Keyboard;

import java.awt.Graphics;

public class Player extends com.treecio.gamegig3000.entity.Entity {

    private Keyboard input;
    private Sprite sprite;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public void update() {
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }

    }

    private void move(int xa, int ya) {
        x += xa;
        y += ya;
    }


    public void render(Graphics graphics) {


    }

}
