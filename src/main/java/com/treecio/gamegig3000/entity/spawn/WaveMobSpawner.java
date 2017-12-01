package com.treecio.gamegig3000.entity.spawn;

import com.treecio.gamegig3000.Game;
import com.treecio.gamegig3000.entity.entities.Mob;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class WaveMobSpawner implements Spawner<Mob> {

    private int width;
    private int height;

    private final long FIRST_DELAY;
    private final int MAX_ENTITIES = 20;

    private long lastWave;
    private long delay;

    public WaveMobSpawner(int width, int height, long delay){
        this.width = width;
        this.height = height;
        this.lastWave = System.currentTimeMillis();
        this.FIRST_DELAY = this.delay = delay;
    }

    @Override
    public Mob spawn(){
        return new Mob(
                new Vector2D((int)(Math.random()*width), -(int)(Math.random()*height)),
                Math.random()*Math.PI*2,
                (Math.random()+0.5)*Mob.defaultFrequency,
                (Math.random()+0.5)*Mob.defaultAmplitude,
                4,
                (Math.random()+0.5)*Mob.defaultSpeed,
                (Math.random()+0.5)*Mob.defaultHealth);
    }

    @Override
    public void update(){
        long now = System.currentTimeMillis();
        if (now > lastWave + delay){
            double count = Math.min(((FIRST_DELAY-delay)/(double)FIRST_DELAY), 1);

            for (int i = 0; i < count*MAX_ENTITIES; i++){
                Game.INSTANCE.addMob(spawn());
            }

            delay *= 0.95;
            if (delay < 2000) delay = 2000;
            lastWave = now;
        }
    }
}
