package com.treecio.gamegig3000.graphics;

import java.awt.image.BufferedImage;

public class Sprite {

	private int x, y;
	private int size;
	private SpriteSheet sheet;
	private BufferedImage image;
	
	public static Sprite ship = new Sprite(32, 0, 0, SpriteSheet.entities);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.size = size;
		this.x = x * size;
		this.y = y * size;
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
