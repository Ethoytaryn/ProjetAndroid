package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import com.example.ProjetAndroid.BriqueJeu.Assembleur;
import com.example.ProjetAndroid.BriqueJeu.Tile;


import java.util.ArrayList;


public class GameLoop implements Runnable {


    private boolean running;  // variable arrêt de la boucle
    private long sleepTime = 10;
    private Context m_context;

    //affichage
    private int fps;
    private GameView screen; //écran de jeu
    int m_largeurTile;


    //données partie

    private ArrayList[][] m_tableauObjet;
    private int m_nbreTile;
    private Rect[][] m_coordTileCanvas;
    private Bitmap m_img;

    //evenement et position doigt
    private MotionEvent lastEvent; // le dernier évenement enregistré sur l'écran
    private float XEcran = 0;
    private float YEcran = 0;
    float x_frame_précédente = 0;
    float y_frame_précédente = 0;
    private float translateX = 0;
    private float translateY = 0;



    public void initGame(Context context, Assembleur assembleur)  {

        m_tableauObjet = assembleur.getTableau();
        m_nbreTile = 1200;
        m_context = context;
        running = true;
        screen = new GameView(m_context, this);
        m_largeurTile = 50;

        m_coordTileCanvas = new Rect[30][40];
        positionDesTuiles();

    }

    /** la boucle de game_screen */
    @Override
    public void run() {

        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé

        render();

        while (running) {

            startTime = System.currentTimeMillis();

            processEvents();
            update();
            render();



            elapsedTime = System.currentTimeMillis() - startTime;
            sleepCorrected = sleepTime - elapsedTime;

            // si jamais sleepCorrected<0 alors faire une pause de 1 ms
            if (sleepCorrected < 0) {
                sleepCorrected = 1;
            }
            try {
                Thread.sleep(sleepCorrected > 0 ? sleepCorrected : 1);
            } catch (InterruptedException e) {

            }
            // calculer le FSP
            fps = (int) (1000/(System.currentTimeMillis() - startTime));
            Log.d("FPS",""+fps);
        }
    }

    public void render() {

           for (int i = 0; i < 30; i++) {
               for (int j = 0; j < 40; j++) {

                   Rect I = m_coordTileCanvas[i][j];

                   ArrayList listeTuile = m_tableauObjet[i][j];
                   for (Object tile : listeTuile) {
                       Tile tuile = (Tile) tile;


                       if (tuile.getASpite()) {

                           if (I.right > (Math.abs(XEcran) - m_largeurTile) || I.left < Math.abs(XEcran) + screen.width || I.top > Math.abs(YEcran) - m_largeurTile || I.bottom < Math.abs(YEcran) + screen.height)
                               screen.canvas.drawBitmap(tuile.getBitmap(), null, I, null);

                       }
                   }
               }
           }
            screen.invalidate();
    }

    public void update() {

            if (XEcran + translateX <= 0 && (XEcran - screen.width + translateX) > (-50 * 40)) {
                XEcran += translateX;
                screen.canvas.translate(translateX, 0);
                translateX = 0;
            }

            if (YEcran + translateY <= 0 && YEcran - screen.height + translateY > (-50 * 30)) {
                YEcran += translateY;
                screen.canvas.translate(0, translateY);
                translateY = 0;
            }

    }

    public void processEvents() {


        if(lastEvent != null) {

            if (lastEvent.getAction() == MotionEvent.ACTION_MOVE) {
                float x_actuel = lastEvent.getX();
                float y_actuel = lastEvent.getY();

                if (x_frame_précédente == 0) {
                    x_frame_précédente = x_actuel;
                }

                if(y_frame_précédente == 0){
                    y_frame_précédente = y_actuel;
                }

                translateX = x_actuel - x_frame_précédente;
                translateY = y_actuel - y_frame_précédente;

                x_frame_précédente = x_actuel ;
                y_frame_précédente = y_actuel;

            }

            if (lastEvent.getAction() == MotionEvent.ACTION_UP) {
                x_frame_précédente = 0;
                y_frame_précédente = 0;
                translateX = 0;
                translateY = 0;
                lastEvent = null;
            }
        }

    }
     private void positionDesTuiles(){

         for (int i = 0; i < 30; i++) {
             for (int j = 0; j < 40; j++) {

                 int a = j * m_largeurTile;
                 int b = i * m_largeurTile;
                 int c = a + m_largeurTile;
                 int d = b + m_largeurTile;

                 Rect I = new Rect(a, b, c, d);

                 m_coordTileCanvas[i][j] = I;
                }
             }
     }

    public GameView getScreen() {
        return screen;
    }

    public void setLastEvent(MotionEvent lastEvent) {
        this.lastEvent = lastEvent;
    }


}




