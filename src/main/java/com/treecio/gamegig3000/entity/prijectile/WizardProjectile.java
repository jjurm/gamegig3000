package com.treecio.gamegig3000.entity.prijectile;


import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;

public class WizardProjectile extends Projectile{

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		damage = 20;
		rateOfFire = 10;
		sprite = Sprite.grass;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update(){
		move();
	}

	protected void move(){
		x += nx;
		y += ny;
	}
	
	public void render(Screen screen){
		screen.renderTile(x, y, sprite);
	}
}
