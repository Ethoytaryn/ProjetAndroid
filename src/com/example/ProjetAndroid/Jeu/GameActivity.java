package com.example.ProjetAndroid.Jeu;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    private GameLoop gameLoop;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameLoop = new GameLoop();
        gameLoop.initGame(this);
        setContentView(gameLoop.screen);


    }

    @Override
    protected void onDestroy() {
        gameLoop.running = false;
        super.onDestroy();
    }
}