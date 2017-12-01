package com.treecio.gamegig3000.entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;


public abstract class Entity {

	public int x, y;
	public double angle = 0;
	public Sprite sprite;
	private boolean removed = false;

	public Entity(int x, int y, double angle, Sprite sprite){
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public abstract void update();

	public void render(Graphics2D graphics){
		AffineTransform at = new AffineTransform();

		at.translate(x,y);
		at.rotate(angle);
		at.translate(-sprite.SIZE/2, -sprite.SIZE/2);

		throw new RuntimeException("Uncomment after implementing sprite.getImage()");
		//graphics.drawImage(sprite.getImage(), at, null);
	}


	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}
