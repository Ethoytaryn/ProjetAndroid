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


    private int m_width; //m_largeurTile de l'écran
    private int m_height; //hauteur de l'écran
    private Canvas m_canvas; //outil pour dessiner sur l'écran
    private Bitmap m_buffer; // pixel buffer
    private SurfaceHolder m_holder;
    private GameLoop m_game; //boucle de game_screen
    private Thread monThread;

    public GameView(Context context, GameLoop game) {
        super(context);
        m_holder = getHolder();
        m_holder.addCallback(this);
        m_game = game;
    }

    /** Rafraichir l'écran**/
    @Override
    public void invalidate() {
        if (m_holder != null) {
            Canvas c = m_holder.lockCanvas();
            if (c != null) {
                c.drawBitmap(m_buffer, 0, 0, null);
                m_holder.unlockCanvasAndPost(c);
            }
        }
    }

    //On envoie à la boucle de jeu le dernier evenement
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_game.setLastEvent(event);
        return true;
    }


    //quand l'activité a démarrer on lance la boucle
    public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {

        m_width = width;
        m_height = height;
        m_buffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        m_canvas = new Canvas(m_buffer);
        monThread = new Thread(m_game);
        monThread.start();
    }

    public void surfaceCreated(SurfaceHolder holder) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public Canvas getCanva(){
        return m_canvas;
    }

    public int getM_Width(){
        return m_width;
    }
    public int getM_height(){
        return m_height;
    }
}
