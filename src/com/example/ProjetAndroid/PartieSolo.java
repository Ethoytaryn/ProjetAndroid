package com.example.ProjetAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
/**
 * Created by maël on 21/03/2016.
 */
public class PartieSolo extends Activity implements View.OnClickListener {
    private float x = 300, y = 300;
    private int direction = 0;
    private boolean moving = false;
    private Animation[] animations = new Animation[8];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partiesolo);
        SpriteSheet spriteSheet = null;
        try {
            spriteSheet = new SpriteSheet("sprites/character.png",64, 64);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
    }
    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 16, y - 8, 32, 16);
        g.drawAnimation(animations[direction + (moving ? 4 : 0)], x-32, y-60);
    }
    @Override
    public void onClick(View v) {

    }
}
