package com.treecio.gamegig3000.entity;

import java.util.*;

import com.treecio.gamegig3000.graphics.Screen;

public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random ranrom = new Random();

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void remove() {
		// Remove from level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}
}
