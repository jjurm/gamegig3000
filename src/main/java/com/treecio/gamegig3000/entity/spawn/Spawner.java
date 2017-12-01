package com.treecio.gamegig3000.entity.spawn;

import com.treecio.gamegig3000.entity.Entity;

public interface Spawner<T extends Entity> {

    public T spawn();

    public void update();
}
