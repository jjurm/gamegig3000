package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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

	public abstract void update(Input input);

	public void render(Graphics2D graphics){
		AffineTransform at = new AffineTransform();

		at.translate(x,y);
		at.rotate(angle);
		at.scale(4,4);
		at.translate(-sprite.getSize()/2, -sprite.getSize()/2);

		graphics.drawImage(sprite.getImage(), at, null);
	}


	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}
