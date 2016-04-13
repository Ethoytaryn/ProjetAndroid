package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import com.example.ProjetAndroid.BriqueJeu.Assembleur;
import com.example.ProjetAndroid.BriqueJeu.Tile;
import com.example.ProjetAndroid.BriqueJeu.TileMap;
import com.example.ProjetAndroid.BriqueJeu.TileMaps;


import java.util.ArrayList;
import java.util.Objects;


public class GameLoop implements Runnable {


    private boolean running;  // variable arrêt de la boucle
    private long sleepTime = 100;
    private Context m_context;

    //affichage
    private int fps;
    private GameView screen; //écran de jeu


    //données partie

    private Assembleur m_assembleur;
    private ArrayList[][] m_tableauObjet;
    private Bitmap m_img;
    private Paint paint;

    //evenement et position doigt
    private boolean m_motion;
    private MotionEvent lastEvent; // le dernier évenement enregistré sur l'écran
    private float positionX = 0;
    private  float positionY = 0;




    public void initGame(Context context, Assembleur assembleur)  {

        m_motion = true;
        m_assembleur = assembleur;
        m_tableauObjet = assembleur.getTableau();
        m_context = context;
        running = true;
        screen = new GameView(m_context, this);
        paint = new Paint();
        paint.setColor(0xFF000000);

    }

    /** la boucle de game_screen */
    @Override
    public void run() {

        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé

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
        }
    }

    /** Dessiner les composant du game_screen sur le buffer de l'écran*/
    public void render() {

        if (m_motion) {
            screen.canvas.drawPaint(paint);

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 40; j++) {

                    ArrayList listeTuile = m_tableauObjet[i][j];
                    for (Object tile : listeTuile) {

                        Tile temp = (Tile) tile;
                        int resID = m_context.getResources().getIdentifier(temp.getM_tileset().getM_nomTileSet(), "drawable", m_context.getPackageName());
                        m_img = ((BitmapDrawable) m_context.getResources().getDrawable(resID)).getBitmap();
                        int largeur = 50;

                        int a = j * largeur;
                        int b = i * largeur;
                        int c = a + largeur;
                        int d = b + largeur;

                        Rect I = new Rect(a, b, c, d);
                        Rect J = temp.getM_coordSprite();

                        screen.canvas.drawBitmap(m_img, J, I, null);

                    }

                }
            }

            screen.invalidate();
            m_motion = false;
        }
    }

    public void update() {
        if(m_motion){
            screen.canvas.translate(positionX,positionY);
            positionX = 0;
            positionY = 0;
        }
    }

    public void processEvents() {

        if (lastEvent != null) {
            if(lastEvent.getAction()==MotionEvent.ACTION_UP){
                Log.d("Detection","J'ai cliquez une fois");
            }
            if (lastEvent.getAction() == MotionEvent.ACTION_MOVE) {
                float x_init = lastEvent.getHistoricalX(0);
                float x = lastEvent.getX();

                float y = lastEvent.getY();
                Log.d("Detection", "J'ai commence en x = "+x_init);
                    Log.d("Dectection","Je bouge mon doigt en position "+x + " "+y);
                    Log.d("Detection", "Il y a une différence de "+(x-x_init));
                    //positionX = -80;
                    //translate = true;


            }
            else if (lastEvent.getAction() == MotionEvent.ACTION_UP) {
                Log.d("Dectection","J'enleve mon doigt");
                setLastEvent(null);
                translate = false;
                positionX = 0;
            }
        }
    }

    public GameView getScreen() {
        return screen;
    }

    public MotionEvent getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(MotionEvent lastEvent) {
        this.lastEvent = lastEvent;
    }


}




