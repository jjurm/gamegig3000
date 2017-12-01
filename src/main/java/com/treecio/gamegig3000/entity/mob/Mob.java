package com.treecio.gamegig3000.entity.mob;

import com.treecio.gamegig3000.entity.Entity;
import com.treecio.gamegig3000.entity.prijectile.Projectile;
import com.treecio.gamegig3000.entity.prijectile.WizardProjectile;
import com.treecio.gamegig3000.graphics.Sprite;

import java.util.List;
import java.util.*;


public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	protected List<Projectile> projectiles = new ArrayList<Projectile>();

	public void move(int xa, int ya) {
		if(xa !=0 && ya !=0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {
	}
	
	protected void shoot(int x, int y, double dir){
		//dir *= 180 / Math.PI;
		Projectile p = new WizardProjectile(x, y, dir);
		projectiles.add(p);
		level.add(p);
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for(int c = 0; c<4;c++){
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if(level.getTile(xt, yt).solid()) solid = true;

		}
		return solid;
	}

	public void render() {

	}
}
