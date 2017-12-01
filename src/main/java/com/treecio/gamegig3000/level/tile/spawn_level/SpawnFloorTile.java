package com.treecio.gamegig3000.level.tile.spawn_level;

import com.treecio.gamegig3000.level.tile.Tile;
import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;

public class SpawnFloorTile extends Tile {

	public SpawnFloorTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, Sprite.spawn_floor);
	}
}
