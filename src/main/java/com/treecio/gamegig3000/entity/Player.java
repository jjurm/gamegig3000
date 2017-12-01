package com.treecio.gamegig3000.entity;



import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.input.Keyboard;
import com.treecio.gamegig3000.input.Mouse;

import java.awt.*;

public class Player extends com.treecio.gamegig3000.entity.Entity {

	private Keyboard input;


	public Player(int x, int y, Keyboard input, Sprite sprite){
		super(x, y,0, sprite);
		this.input = input;
	}
	
	public void update(){
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
