package com.treecio.gamegig3000.input;

import com.treecio.gamegig3000.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	private Input input = new Input();

	public void update() {
		input.up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		input.down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		input.left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		input.right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}

	public void keyPressed(KeyEvent e) {
		keys[(e.getKeyCode())] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[(e.getKeyCode())] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public Input getNewInput() {
		update();
		return input;
	}

}
