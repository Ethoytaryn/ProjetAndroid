package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by Guillaume on 04/04/2016.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {


    public int width; //largeur de l'écran
    public int height; //hauteur de l'écran
    public Canvas canvas; //outil pour dessiner sur l'écran
    private Bitmap buffer; // pixel buffer
    private SurfaceHolder holder;
    private GameLoop game; //boucle de game_screen
    private Thread monThread;

    public GameView(Context context, GameLoop game) {
        super(context);
        this.holder = getHolder();
        this.holder.addCallback(this);
        this.game = game;
    }

    /** Rafraichir l'écran*/
    @Override
    public void invalidate() {
        if (holder != null) {
            Canvas c = holder.lockCanvas();
            if (c != null) {
                c.drawBitmap(buffer, 0, 0, null);
                holder.unlockCanvasAndPost(c);
            }
        }
    }

    //On envoie à la boucle de jeu le dernier evenement
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        game.setLastEvent(event);
        return true;
    }


    //quand l'activité a démarrer on lance la boucle
    public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {

        this.width = width;
        this.height = height;
        this.buffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(buffer);
        monThread = new Thread(game);
        monThread.start();
    }

    public void surfaceCreated(SurfaceHolder holder) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
