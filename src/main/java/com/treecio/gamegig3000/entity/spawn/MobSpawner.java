package com.treecio.gamegig3000.entity.spawn;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Game;
import com.treecio.gamegig3000.entity.entities.Mob;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class MobSpawner implements Spawner<Mob> {

    private int widthRange, heightRange;
    private final double spawnChance = 0.01;


    public MobSpawner(int widthRange, int heightRange){
        this.widthRange = widthRange;
        this.heightRange = heightRange;
    }

    @Override
    public Mob spawn() {
        return new Mob(
                new Vector2D((int)(Math.random()*widthRange), -32),
                Math.random()*Math.PI*2,
                (Math.random()+0.5)*Mob.defaultFrequency,
                (Math.random()+0.5)*Mob.defaultAmplitude,
                4,
                (Math.random()+0.5)*Mob.defaultSpeed,
                (Math.random()+0.5)*Mob.defaultHealth);
    }

    public boolean canSpawn(long time) {
        double desired = 1 + time / App.Companion.getFPS() / 2;
        double count = Game.INSTANCE.getMobs().size();
        double diff = desired - count;
        double prob = diff / 8;

        return Math.random() < diff;
    }

    @Override
    public void update(){

    }
}
