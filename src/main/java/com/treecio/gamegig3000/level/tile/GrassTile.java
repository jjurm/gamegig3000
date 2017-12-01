package com.treecio.gamegig3000.level.tile;

import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, Sprite.grass);
	}
}
