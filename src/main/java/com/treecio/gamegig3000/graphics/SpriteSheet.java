package com.treecio.gamegig3000.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int size;
	private BufferedImage image;


	public static SpriteSheet entities = new SpriteSheet("res/textures/spritesheet.png", 256);

	public SpriteSheet(String path, int size) {
		this.path = path;
		this.size = size;
		load();
	}

	public BufferedImage getImage() {
		return this.image;
	}


	private void load() {
		try {
			this.image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
