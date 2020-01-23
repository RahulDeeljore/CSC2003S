package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;

public class Bitmask {

    int[] bits;

    Bitmask(Pixmap pixelMap){

        bits = new int[pixelMap.getHeight()];

        // initialize to 0
        // images are 20 x 20 except player
        for (int i=0; i<20; i++) {
            bits[i] = 0;
        }

        // initialize bitmask
        for (int i = 0; i < pixelMap.getHeight(); i++) {
            for(int j = 0; j < pixelMap.getWidth(); j++) {
                int val = pixelMap.getPixel(j, i); // Returns the 32-bit RGBA8888 value of the pixel at x, y.
                bits[i] = bits[i] << 1;
                if(val != 0 && val != -256){ // make entities solid
                    bits[i] = bits[i] | 1;
                }
            }

        }

    }
}
