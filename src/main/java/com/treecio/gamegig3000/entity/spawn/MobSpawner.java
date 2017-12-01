package com.treecio.gamegig3000.entity.spawn;

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
        return new Mob(new Vector2D((int)(Math.random()*widthRange),
                (int) (Math.random()*heightRange/2)),
                Math.random()*Math.PI*2,
                4,
                (Math.random()+0.5)*Mob.defaultFrequency,
                (Math.random()+0.5)*Mob.defaultAmplitude,
                (Math.random()+0.5)*Mob.defaultHealth);
    }

    public boolean canSpawn(long time) {
        time = time/1000000;

        int level = (int) Math.sqrt(time/30)+1;
        return (Math.random() < (level*spawnChance*0.5));
    }
}
