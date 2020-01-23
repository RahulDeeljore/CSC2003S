package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player;
import com.mygdx.game.Entity;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	int screenWidth;
	int screenHeight;

	// 1 = block
	// 0 = empty
	// the x and y coordinate system is not what it seems
	// visually x goes down and y across
	// this will make more sense when you compare it to what is drawn
	int[][] map = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	};
	int mapWidth = 15;
	int mapHeight = 15;
	int tileSize = 20;
	Texture tileTexture;

	Quadtree quadtree;

	Player player;
	ArrayList<Entity> entities = new ArrayList<Entity>();

	enum Axis { X, Y };
	enum Direction { U, D, L, R };

	@Override
	public void create () {
		batch = new SpriteBatch();
		tileTexture = new Texture("block.png");
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		quadtree = new Quadtree(1, mapWidth + 1, mapHeight + 1 , (mapWidth - 2)*tileSize, (mapHeight - 2)*tileSize);// create quadtree


		entities.add(new Entity(this, 240, 200, 20, 20, 120.0f, new Texture("dracula.png")));
		entities.add(new Entity(this, 40, 200, 20, 20, 120.0f, new Texture("dracula.png")));
		entities.add(new Entity(this, 180, 50, 20, 20, 120.0f, new Texture("dracula.png")));
		entities.add(new Entity(this, 150, 200, 20, 20, 120.0f, new Texture("dracula.png")));
		entities.add(new Entity(this, 20, 20, 20, 20, 120.0f, new Texture("dracula.png")));
		quadtree.insert(entities);

		player = new Player(this, 200, 150, 120.0f, new Texture("player.png"));
		entities.add(player);
	}

	public void moveEntity(Entity e, float newX, float newY) {
		// for this test only the player is moving
		if(!e.equals(player)) return;

		// just check x collisions keep y the same
		moveEntityInAxis((Player) e, Axis.X, newX, e.y);
		// just check y collisions keep x the same
		moveEntityInAxis((Player) e, Axis.Y, e.x, newY);
	}

	public void moveEntityInAxis(Player e, Axis axis, float newX, float newY) {

		Direction direction;

		// determine axis direction
		if(axis == Axis.Y) {
			if(newY - e.y < 0) direction = Direction.U;
			else direction = Direction.D;
		}
		else {
			if(newX - e.x < 0) direction = Direction.L;
			else direction = Direction.R;
		}

		if(!tileCollision(e, direction, newX, newY) && !entityCollision(e, direction, newX, newY)) {
			// full move with no collision
			e.move(newX, newY);
		}
		// else collision with wither tile or entity occurred, so no move
	}

	public boolean tileCollision(Player e, Direction direction, float newX, float newY) {

		// determine affected tiles
		int x1 = (int) Math.floor(Math.min(e.x, newX) / tileSize);
		int y1 = (int) Math.floor(Math.min(e.y, newY) / tileSize);
		int x2 = (int) Math.floor((Math.max(e.x, newX) + e.width - 0.1f) / tileSize);
		int y2 = (int) Math.floor((Math.max(e.y, newY) + e.height - 0.1f) / tileSize);

		// tile checks
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				if(map[x][y] == 1) {
					if (e.wallCollision(x, y, newX, newY)) return true;
				}
			}
		}

		return false;
	}

	public boolean entityCollision(Entity e1, Direction direction, float newX, float newY) {

		ArrayList<Entity> localEntities = new ArrayList(); //arraylist for local entities
		quadtree.retrieve(localEntities, e1); // add local entities on arraylist

		System.out.println("In quadtree node" +localEntities.size());

		// check local entities for collision
		for(int i = 0; i < localEntities.size(); i++) {
			Entity e2 = localEntities.get(i);

			// bounding box collision check
			if(newX < e2.x + e2.width && e2.x < newX + e1.width &&
					newY < e2.y + e2.height && e2.y < newY + e1.height) {

				System.out.println("Player in bounding box around"+"("+newX + "," + newY+")");

				if(e1.entityCollision(e2, newX, newY, direction))

				    return true;
			}
		}

		return false;
	}

	@Override
	public void render () {

		// update
		// ---


		float delta = Gdx.graphics.getDeltaTime();

		// update all entities
		for(int i = entities.size() - 1; i >= 0; i--) {
			Entity e = entities.get(i);
			// update entity based on input/ai/physics etc
			// this is where we determine the change in position
			e.update(delta);
			// now we try move the entity on the map and check for collisions
			moveEntity(e, e.x + e.dx, e.y + e.dy);
		}


		// draw
		// ---


		// to offset where your map and entities are drawn change the viewport
		// see libgdx documentation

		Gdx.gl.glClearColor((float)0.1, (float)0.7, (float)0.1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		// draw tile map
		// go over each row bottom to top
		for(int y = 0; y < mapHeight; y++) {
			// go over each column left to right
			for(int x = 0; x < mapWidth; x++) {
				// tile
				if(map[x][y] == 1) {
					batch.draw(tileTexture, x * tileSize, y * tileSize);
				}
			}
		}

		// draw entities
		for(int i = entities.size() - 1; i >= 0; i--) {
			Entity e = entities.get(i);
			batch.draw(e.texture, e.x, e.y);
		}

		batch.end();
	}
}
