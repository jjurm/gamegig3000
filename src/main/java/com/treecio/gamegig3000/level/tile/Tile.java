package com.treecio.gamegig3000.level.tile;

import com.treecio.gamegig3000.graphics.Screen;
import com.treecio.gamegig3000.graphics.Sprite;
import com.treecio.gamegig3000.level.tile.spawn_level.*;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	/*
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);*/
	
	public static final int col_spawn_grass = 0xff00ff00;
	public static final int col_spawn_hedge = 0; //unused
	public static final int col_spawn_water = 0; //unused
	public static final int col_spawn_wall1 = 0xff8c8c8c;
	public static final int col_spawn_wall2 = 0xff3f3f3f;
	public static final int col_spawn_floor = 0xff646432;


	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
	}

	public boolean solid() {
		return false;
	}

}
