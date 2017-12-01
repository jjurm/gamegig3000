package com.treecio.gamegig3000.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Sprite {

	private int x, y;
	private int size;
	private SpriteSheet sheet;
	private BufferedImage image;

	public static List<Sprite> gameOverSprite = new ArrayList<Sprite>(){{
		add(new Sprite(32, 0, 3, SpriteSheet.entities));
		add(new Sprite(32, 1, 3, SpriteSheet.entities));
		add(new Sprite(32, 2, 3, SpriteSheet.entities));
		add(new Sprite(32, 3, 3, SpriteSheet.entities));
		add(new Sprite(32, 4, 3, SpriteSheet.entities));
		add(new Sprite(32, 5, 3, SpriteSheet.entities));
		add(new Sprite(32, 6, 3, SpriteSheet.entities));
		add(new Sprite(32, 7, 3, SpriteSheet.entities));
	}};

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.sheet = sheet;
		load();
	}

	private void load() {
		this.image = sheet.getImage().getSubimage(this.size*x, this.size*y, this.size, this.size);
	}

	public int getSize(){
		return this.size;
	}

	public BufferedImage getImage() {
		return this.image;
	}



}
