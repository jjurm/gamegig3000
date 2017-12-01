package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.App;
import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.Utils;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.graphics.SpriteSheet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Player extends com.treecio.gamegig3000.entity.Entity {

	public static Sprite ship = new Sprite(32, 0, 0, SpriteSheet.entities);

	public static final int SPACE_MARGIN = App.Companion.getWIDTH() / 10;

	public Player(){
		super(new Vector2D(App.Companion.getWIDTH() / 2, (int) (App.Companion.getHEIGHT() * 0.8)),
				0, ship, 4);
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

	}

	protected void move(Vector2D move){
		super.move(move);
		pos = new Vector2D(
				Utils.toRange(pos.getX(), SPACE_MARGIN, App.Companion.getWIDTH() - SPACE_MARGIN),
				Utils.toRange(pos.getY(), App.Companion.getHEIGHT() / 2, App.Companion.getHEIGHT() - SPACE_MARGIN / 2)
		);
	}

	private int getSpeed() {
		return 10;
	}

}
