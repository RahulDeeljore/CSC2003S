package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame.Direction;
import com.badlogic.gdx.graphics.Pixmap;


public class Entity {
	public MyGdxGame game;
	public float x;
	public float y;
	public float dx;
	public float dy;
	public int width;
	public int height;  
	public float speed;
	int[] bits;
	public Texture texture;
	protected Bitmask bitmask1;
	
	public Entity(MyGdxGame game, float x, float y, int width, int height, float speed, Texture texture) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.texture = texture;
		this.texture.getTextureData().prepare();
		bitmask1 = new Bitmask(this.texture.getTextureData().consumePixmap());
	}

	public void update(float delta) {

	}
	
	public void move(float newX, float newY) {
		x = newX;
		y = newY;		
	}
	
	public void render() {
		
	}

	// tile collisions are not implemented
	public void tileCollision(int tile, int tileX, int tileY, float newX, float newY, Direction direction) {
		System.out.println("tile collision at: " + tileX + " " + tileY);
		
		if(direction == Direction.U) {
			y = tileY * game.tileSize + game.tileSize;
		}
		else if(direction == Direction.D) {
			y = tileY * game.tileSize - height;
		}
		else if(direction == Direction.L) {
			x = tileX * game.tileSize + game.tileSize;
		}
		else if(direction == Direction.D) {
			x = tileX * game.tileSize - width;
		}		
	}

	public boolean entityCollision(Entity e2, float newX, float newY, Direction direction) {

		int e1start = 0;
		int e2start = 0;
		int e1shift = 0;
		int e2shift = 0;
		int numChecks;
		int xDiff = Math.round(e2.x) - Math.round(newX);
		int yDiff = Math.round(e2.y) - Math.round(newY);


		// test if x-shift possible
		if (xDiff > 0) e2shift = xDiff;
		else e1shift = -xDiff;


		// test if y-shift possible
		if (yDiff > 0) {
			numChecks = this.height - yDiff;
			e2start = yDiff;
		} else {
			numChecks = e2.height + yDiff;
			e1start = - yDiff;
		}

		// goes through bitmask and does bit shifting to check for collisions
		for (int i = 0; i < numChecks; i++){
			if( ((bitmask1.bits[e1start] >> e1shift) & (e2.bitmask1.bits[e2start] >> e2shift)) != 0) //bit shifting
			{
				return true;
			}

			e1start++; // increment index of both maps
			e2start++;
		}

		return false; // no collision
	}
}
