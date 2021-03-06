package com.treecio.gamegig3000.entity.entities;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Constants;
import com.treecio.gamegig3000.Game;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.Utils;
import com.treecio.gamegig3000.entity.Projectile;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Player extends com.treecio.gamegig3000.entity.Entity {

    private int fireCooldown = 100;
    private long nextShot = 0;

	public static List<Sprite> ship = new ArrayList<Sprite>(){{
		add(new Sprite(32, 0, 0, SpriteSheet.entities));
	}};

	public static final int SPACE_MARGIN = App.Companion.getWIDTH() / 20;

	public Player(){
		super(new Vector2D(App.Companion.getWIDTH() / 2, (int) (App.Companion.getHEIGHT() * 0.8)),
				0, ship, 2);
	}
	
	public void update(Input input){
		int xa = 0, ya =0;
		if(input.up) ya -= 1;
		if(input.down) ya += 1;
		if(input.left) xa -= 1;
		if(input.right) xa += 1;
		if(xa != 0 || ya != 0){
			move(new Vector2D(xa, ya).normalize().scalarMultiply(getSpeed()));
		}

		if (Game.INSTANCE.getEnergyBar().canConsume(Constants.COST_FIRE) && input.space) {
			fire();
		}
	}

	private void fire(){
		long now = System.currentTimeMillis();
		if (now > nextShot) {

            Game.INSTANCE.getEnergyBar().consume(Constants.COST_FIRE);
			Game.INSTANCE.addBullet(this.pos, 0, Projectile.bullet, 2, 32);

			nextShot = now + fireCooldown;
		}
	}

	protected void move(Vector2D move){
		super.move(move);
		pos = new Vector2D(
				Utils.toRange(pos.getX(), SPACE_MARGIN, App.Companion.getWIDTH() - SPACE_MARGIN),
				Utils.toRange(pos.getY(), SPACE_MARGIN, App.Companion.getHEIGHT() - SPACE_MARGIN / 2)
		);
	}

	@Override
	public double getRadius() {
		return 5 * 4;
	}

	private int getSpeed() {
		return 24;
	}

}
