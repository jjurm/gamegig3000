package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;

public class Player extends com.treecio.gamegig3000.entity.Entity {

	public Player(int x, int y){
		super(x, y,0, Sprite.ship);
	}
	
	public void update(Input input){
		int xa = 0, ya =0;
		if(input.up) ya -= getSpeed();
		if(input.down) ya += getSpeed();
		if(input.left) xa -= getSpeed();
		if(input.right) xa += getSpeed();
		if(xa != 0 || ya != 0){
			move(xa, ya);
		}

	}

	private void move(int xa, int ya){
		x += xa;
		y += ya;
	}

	private int getSpeed() {
		return 10;
	}

}
