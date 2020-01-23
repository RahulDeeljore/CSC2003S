package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import java.util.Random;

public class GameScreen implements Screen,ApplicationListener {
    public static SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont Cfont;
    private BitmapFont sfont;
    private BitmapFont linesfont;
    private BitmapFont scfont;
    private BitmapFont line;
    public boolean showline = false;

    private Texture img;
    private Texture border;
    private Texture Gblock;
    private Texture Rblock;
    private Texture Yblock;
    private Texture Pblock;
    private Texture Bblock;
    private Texture background;
    public static Texture go;
    private Texture ln;
    private Texture L;
    private Texture Z;
    private Texture T;
    private Texture s;


    private int grid[][] = new int[14][24];
    private Block[] b = new Block[4];
    private Block[] bb = new Block[4];
    private Tetromino Tetro;

    private Block myblock1;
    private Block myblock2;
    private Block myblock3;
    private Block myblock4;

    private Block block1;
    private Block block2;
    private Block block3;
    private Block block4;

    private Random r = new Random();


    private long frame = 0;

    private float time = System.nanoTime();
    private int speed = 0;
    public int score = 0;
    public int lines = 0;

    private int pre;
    int ran;


    public GameScreen(Game g) {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Cfont = new BitmapFont();
        Cfont.setColor(Color.WHITE);
        sfont = new BitmapFont();
        sfont.setColor(Color.WHITE);
        linesfont = new BitmapFont();
        linesfont.setColor(Color.WHITE);
        scfont = new BitmapFont();
        scfont.setColor(Color.WHITE);
        line = new BitmapFont();
        line.setColor(Color.WHITE);


        img = new Texture("blacktetris.png");
        ln = new Texture("Ln_piece.png");
        s = new Texture("S_piece.png");
        L = new Texture("L_piece.png");
        Z = new Texture("Z_piece.png");
        T = new Texture("T_piece.png");
        border = new Texture("border.png");
        Gblock = new Texture("green_block.png");
        Rblock = new Texture("red_block.png");
        Yblock = new Texture("yellow_block.png");
        Pblock = new Texture("purple_block.png");
        Bblock = new Texture("blue_block.png");
        background = new Texture("tet.png");
        go = new Texture("game over.png");


        int tetrominonumber = r.nextInt(7);


        if (tetrominonumber == 0)
        {
            myblock1 = new Block(grid, 6, 21);
            myblock2 = new Block(grid, 6, 20);
            myblock3 = new Block(grid, 6, 19);
            myblock4 = new Block(grid, 6, 18);
        } else if (tetrominonumber == 1) // T
        {
            myblock1 = new Block(grid, 5, 21);
            myblock2 = new Block(grid, 6, 21);
            myblock3 = new Block(grid, 6, 20);
            myblock4 = new Block(grid, 7, 21);
        } else if (tetrominonumber == 2) // L
        {
            myblock1 = new Block(grid, 6, 21);
            myblock2 = new Block(grid, 6, 20);
            myblock3 = new Block(grid, 6, 19);
            myblock4 = new Block(grid, 7, 19);
        } else if (tetrominonumber == 3) //z
        {
            myblock1 = new Block(grid, 5, 21);
            myblock2 = new Block(grid, 6, 21);
            myblock3 = new Block(grid, 6, 20);
            myblock4 = new Block(grid, 7, 20);
        } else if (tetrominonumber == 4) //rL
        {
            myblock1 = new Block(grid, 7, 21);
            myblock2 = new Block(grid, 7, 20);
            myblock3 = new Block(grid, 7, 19);
            myblock4 = new Block(grid, 6, 19);
        } else if (tetrominonumber == 5) //S
        {
            myblock1 = new Block(grid, 7, 21);
            myblock2 = new Block(grid, 6, 21);
            myblock3 = new Block(grid, 6, 20);
            myblock4 = new Block(grid, 5, 20);
        } else if (tetrominonumber == 6) // O
        {
            myblock1 = new Block(grid, 6, 21);
            myblock2 = new Block(grid, 7, 21);
            myblock3 = new Block(grid, 6, 20);
            myblock4 = new Block(grid, 7, 20);
        }

        b[0] = myblock1;
        b[1] = myblock2;
        b[2] = myblock3;
        b[3] = myblock4;

        Tetro = new Tetromino(grid, b);

        for (int y = 0; y < 24; y++) {
            for (int x = 0; x < 14; x++) {
                if (x == 1 || x == 12 || y == 1 || y == 22) {
                    grid[x][y] = 1;// border
                } else if (x == 0 || x == 13 || y == 0 || y == 23) {
                    grid[x][y] = 4;// unplayable
                } else {
                    grid[x][y] = 0; // actual grid
                }

            }
        }

        Tetro.Show();

    }

    public boolean gameover() {
        if (grid[4][20] == 3 || grid[5][20] == 3 || grid[6][20] == 3 || grid[6][20] == 3 || grid[3][20] == 3 || grid[2][20] == 3 || grid[7][20] == 3 || grid[8][20] == 3 || grid[8][20] == 3 )
            return true;
        else
            return false;

    }


    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {

        if (Tetro.getStop() == true) {
            if (speed != 1000) {
                speed += 5;
            }

            ran = pre;

            pre = r.nextInt(7);


            if (ran == 0) //line piece
            {
                block1 = new Block(grid, 6, 21);
                block2 = new Block(grid, 6, 20); //pivot
                block3 = new Block(grid, 6, 19);
                block4 = new Block(grid, 6, 18);
            } else if (ran == 1) // T piece
            {
                block1 = new Block(grid, 5, 21);
                block2 = new Block(grid, 6, 21); //pivot
                block3 = new Block(grid, 6, 20);
                block4 = new Block(grid, 7, 21);
            } else if (ran == 2) // L piece
            {
                block1 = new Block(grid, 6, 21);
                block2 = new Block(grid, 6, 20); //pivot
                block3 = new Block(grid, 6, 19);
                block4 = new Block(grid, 7, 19);
            } else if (ran == 3) //Z piece
            {
                block1 = new Block(grid, 5, 21);
                block2 = new Block(grid, 6, 21); //pivot
                block3 = new Block(grid, 6, 20);
                block4 = new Block(grid, 7, 20);
            } else if (ran == 4) //reverse L piece
            {
                block1 = new Block(grid, 7, 21);
                block2 = new Block(grid, 7, 20);//pivot
                block3 = new Block(grid, 7, 19);
                block4 = new Block(grid, 6, 19);
            } else if (ran == 5) //reverse Z piece
            {
                block1 = new Block(grid, 7, 21);
                block2 = new Block(grid, 6, 21); //pivot
                block3 = new Block(grid, 6, 20);
                block4 = new Block(grid, 5, 20);
            } else if (ran == 6) // square piece
            {
                block1 = new Block(grid, 6, 21);
                block2 = new Block(grid, 7, 21); //pivot
                block3 = new Block(grid, 6, 20);
                block4 = new Block(grid, 7, 20);
            }

            bb[0] = block1;
            bb[1] = block2;
            bb[2] = block3;
            bb[3] = block4;

            Tetro = new Tetromino(grid, bb);

            Tetro.Show();


        }

        int c = 23;
        for (int y = 2; y < 22; y++) {
            if (grid[2][y] == 3 && grid[3][y] == 3 && grid[4][y] == 3 && grid[5][y] == 3 && grid[6][y] == 3 &&
                    grid[7][y] == 3 && grid[8][y] == 3 && grid[9][y] == 3 && grid[10][y] == 3 && grid[11][y] == 3) {
                //clears line
                for (int x = 2; x < 12; x++) {
                    grid[x][y] = 0;
                }


                score = score + 100;
                lines = lines + 1;



                for (int m = y; m < c; m++) {
                    for (int n = 2; n < 12; n++) {
                        grid[n][m] = grid[n][m + 1];

                    }
                    c--;
                }

            }

        }


        // function to remove bottom line once player has cleared 5 lines

        int d = 23;
        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && lines >= 5) {

            for (int y = 2; y < 22; y++) {
                if (grid[2][y] == 3 || grid[3][y] == 3 || grid[4][y] == 3 || grid[5][y] == 3 || grid[6][y] == 3 ||
                        grid[7][y] == 3 || grid[8][y] == 3 || grid[9][y] == 3 || grid[10][y] == 3 || grid[11][y] == 3) {
                    //clears line
                    for (int x = 2; x < 12; x++) {
                        grid[x][y] = 0;
                    }

                    for (int m = y; m < d; m++) {
                        for (int n = 2; n < 12; n++) {
                            grid[n][m] = grid[n][m + 1];

                        }
                        d--;
                    }

                }
            }
            lines -= 5;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && Tetro.getStop() == false)
        {
            create();
            Tetro.MoveRight();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && Tetro.getStop() == false)
        {
            create();
            Tetro.MoveLeft();
        }


        frame++;
        if (frame % 5 == 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                Tetro.MoveDown();
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Tetro.Fall();

            if (Tetro.getStop() == true) {

            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && Tetro.getStop() == false) {
            create();
            Tetro.Rotate();

        }

        if ((System.nanoTime() - time) / (1000 - speed) > 500000 && Tetro.getStop() == false) {
            time = System.nanoTime();
            Tetro.AutoMoveDown();


        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            Gdx.app.exit();

        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(background, 0, Gdx.graphics.getHeight());
        batch.end();


        batch.begin();

        font.getData().setScale(2);//enlarge
        sfont.getData().setScale(2);// score font
        linesfont.getData().setScale(2);
        scfont.getData().setScale(2);
        line.getData().setScale(2);



        font.draw(batch,"Points:",530,150);

        if(lines >=5)
        {
            line.draw(batch,"Press L",530,300);


        }
        if (score >= 0) {
            font.draw(batch, String.valueOf(score), 575, 100);// shows score
        }

        font.draw(batch,"Cleared:",500,450);
        if (lines >= 0) {
            sfont.draw(batch, String.valueOf(lines), 575, 400);//shows lines cleared
        }

        batch.end();

        // make borders, make grid and set Tetromino colours
        batch.begin();
        for (int y = 1; y < 23; y++) {
            for (int x = 1; x < 13; x++) {
                if (grid[x][y] == 1) {
                    batch.draw(border, (x * 20) + 200, (y * 20));
                } else if (grid[x][y] == 0) {
                    batch.draw(img, (x * 20) + 200, (y * 20));
                } else {
                    if (Tetro.getColour() == 0) {
                        batch.draw(Gblock, (x * 20) + 200, (y * 20));
                    }

                    if (Tetro.getColour() == 1) {
                        batch.draw(Rblock, (x * 20) + 200, (y * 20));
                    }

                    if (Tetro.getColour() == 2) {
                        batch.draw(Yblock, (x * 20) + 200, (y * 20));
                    }

                    if (Tetro.getColour() == 3) {
                        batch.draw(Pblock, (x * 20) + 200, (y * 20));
                    }

                    if (Tetro.getColour() == 4) {
                        batch.draw(Bblock, (x * 20) + 200, (y * 20));
                    }
                }
            }
        }
        if (gameover() == true) {
            batch.draw(go, 230, 100);
        }
        if (pre == 0) {
            batch.draw(ln, 0, 100);
        } else if (pre == 1) {
            batch.draw(T, 0, 100);
        } else if (pre == 2) {
            batch.draw(L, 0, 100);
        } else if (pre == 3) {
            batch.draw(Rblock, 0, 100);
        } else if (pre == 4) {
            batch.draw(Bblock, 0, 100);
        } else if (pre == 5) {
            batch.draw(Z, 0, 100);
        } else if (pre == 6) {
            batch.draw(s, 0, 100);
        }


        batch.end();


    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }


    @Override
    public void create() {
    }

    @Override
    public void render() {

    }

}

