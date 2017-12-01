package com.treecio.gamegig3000.level.tile.spawn_level;

import com.treecio.gamegig3000.level.tile.Tile;
import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;

public class SpawnHedgeTile extends Tile {

	public SpawnHedgeTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, Sprite.spawn_hedge);
	}
	
	public boolean solid(){
		return true;
	}
	
	public boolean breakable(){
		return true;
	}

}
