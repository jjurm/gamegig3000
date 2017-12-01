package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;

public class Mob extends Entity {

    private final double frequency;
    private final double amplitude;

    private double phase = 2*Math.PI;

    public static final double defaultFrequency = 0.2;
    public static final double defaultAmplitude = 20;

    /**
     * @param x
     * @param y
     * @param angle
     * @param frequency how fast does it change direction, relative to frames, divide the number by the number of frames to get per seconds
     * @param amplitude how much does it move to sides
     */
    public Mob(int x, int y, double angle, double frequency, double amplitude) {
        super(x, y, angle, Sprite.mob);
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    public Mob(int x, int y, double angle) {
        super(x, y, angle, Sprite.mob);
        this.amplitude = defaultAmplitude;
        this.frequency = defaultFrequency;
    }

    @Override
    public void update(Input input) {
        this.move((int) (Math.cos(phase)*amplitude), (int) (Math.random()*2));
        this.phase = phase+frequency;
    }

    private void move(int xa, int ya){
        x += xa;
        y += ya;
    }
}
