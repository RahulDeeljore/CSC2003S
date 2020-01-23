package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity;

public class Player extends Entity {

	public Player(MyGdxGame game, float x, float y, float speed, Texture texture) {
		super(game, x, y,20,20, speed, texture);
	}

	@Override
	public void update(float delta) {

		dx = 0;
		dy = 0;

		// move
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			dy = 1;//speed * delta;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			dy = -1;//-speed * delta;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			dx = -1;//-speed * delta;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			dx = 1;//speed * delta;
		}
	}

	public boolean wallCollision(int x, int y, float newX, float newY){

		int wall = 300000;
		int start = 0;
		int numChecks;
		int xDiff = (x * 20) - Math.round(newX);
		int yDiff = (y * 20) - Math.round(newY);

		// x-shift
		if (xDiff > 0){
			wall = wall >> xDiff;
		} else {
			wall = wall << (-xDiff);
		}

		//y-shift
		if (yDiff > 0) {
			numChecks = this.height - yDiff;
		} else {
			numChecks = 24 + yDiff;
			start = - yDiff;
		}


		for (int i = 0; i < numChecks; i++){
			if( ((this.bitmask1.bits[start]) & wall) != 0) {
				return true;
			}

			// increments the index of the bitmask.
			start++;
		}


		return false;// no collisions
	}
}

