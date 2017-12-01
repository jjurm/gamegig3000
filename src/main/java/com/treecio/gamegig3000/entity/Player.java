package com.treecio.gamegig3000.entity;

import com.treecio.gamegig3000.Input;
import com.treecio.gamegig3000.graphics.Sprite;

public class Player extends com.treecio.gamegig3000.entity.Entity {

	public Player(int x, int y){
		super(x, y,0, Sprite.ship);
	}
	
	public void update(Input input){
		int xa = 0, ya =0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		if(xa != 0 || ya != 0){
			move(xa, ya);
		}

	}

	private void move(int xa, int ya){
		x += xa;
		y += ya;
	}

}
