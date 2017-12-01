package com.treecio.gamegig3000.entity.prijectile;


import com.treecio.gamegig3000.entity.Entity;
import com.treecio.gamegig3000.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	
	public Projectile(int x, int y, double dir){
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	protected void move(){
		
	}
}
