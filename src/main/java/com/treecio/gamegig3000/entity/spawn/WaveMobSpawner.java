package com.treecio.gamegig3000.entity.spawn;

import com.treecio.gamegig3000.Game;
import com.treecio.gamegig3000.entity.entities.Mob;
import com.treecio.gamegig3000.entity.entities.ScaleMob;
import com.treecio.gamegig3000.entity.entities.TeleMob;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class WaveMobSpawner implements Spawner<Mob> {

    private int width;
    private int height;

    private final long FIRST_DELAY;
    private final int MAX_MOBS = 3;
    private final int MAX_TELEMOBS = 1;
    private final int MAX_SCALEMOBS = 1;


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

    public TeleMob spawnTelemob(){
        return new TeleMob(
                new Vector2D((int)(Math.random()*width), -(int)(Math.random()*height)),
                1,
                0,
                TeleMob.defaultSpeed);
    }
    public ScaleMob spawnScaleMob(){
        return new ScaleMob(
                new Vector2D((int)(Math.random()*width), -(int)(Math.random()*height)), 4.0, 100.0, ScaleMob.defaultFrequency, ScaleMob.defaultAmplitude);
    }

    @Override
    public void update(){
        long now = System.currentTimeMillis();
        if (now > lastWave + delay){

            delay *= 0.90;
            if (delay < 500) delay = 500;

            double count = Math.min(((FIRST_DELAY-delay)/(double)FIRST_DELAY), 1);

            for (int i = 0; i < count*MAX_MOBS; i++){
                Game.INSTANCE.addMob(spawn());
            }

            for (int i = 0; i < Math.max(count*MAX_TELEMOBS-1,0); i++){
                Game.INSTANCE.addTeleMob(spawnTelemob());
            }

            for (int i = 0; i < count*MAX_SCALEMOBS; i++){
                Game.INSTANCE.addScaleMob(spawnScaleMob());
            }


            lastWave = now;
        }
    }
}
