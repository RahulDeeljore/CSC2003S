package com.mygdx.game;


import com.badlogic.gdx.Game;

public class Tetris extends Game {

	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render ()
	{
		super.render();
	}
}
