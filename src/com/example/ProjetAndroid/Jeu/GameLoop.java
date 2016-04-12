package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import com.example.ProjetAndroid.BriqueJeu.Tile;
import com.example.ProjetAndroid.BriqueJeu.TileMap;
import com.example.ProjetAndroid.BriqueJeu.TileMaps;


import java.util.ArrayList;


public class GameLoop implements Runnable {

    private boolean running;  // variable arrêt de la boucle
    private long sleepTime = 100;
    private GameView screen; //écran de jeu
    private MotionEvent lastEvent; // le dernier évenement enregistré sur l'écran
    private Bitmap m_img;
    private Context m_context; /** contexte de l'application */
    private DisplayMetrics m_metrics;
    private TileMaps m_listeMap;



    public void initGame(Context context, DisplayMetrics metrics, TileMaps listeMap)  {

        m_metrics=metrics;
        m_listeMap = listeMap;
        m_context = context;
        running = true;
        screen = new GameView(m_context, this);

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
            int fps = (int) (1000/(System.currentTimeMillis() - startTime));
        }
    }

    /** Dessiner les composant du game_screen sur le buffer de l'écran*/
    public void render() {

        Paint paint = new Paint();
        paint.setColor(0xFFFFFFFF);
        screen.canvas.drawPaint(paint);

        int nombretuileparligne = 8;

        ArrayList<TileMap> listeMap = m_listeMap.getListMap();
        //TileMap couche = listeMap.get(0);
        for (TileMap couche : listeMap) {
            ArrayList<Tile> listeTuile = couche.getListTiles();


            int ligne = 0;
            int nbretuiledessiné = 0;


            for (Tile aListTuile : listeTuile) {


                if (nbretuiledessiné > couche.getM_largeur()-1 ) {
                    nbretuiledessiné = 0;
                    ligne++;
                }
                Tile tuile = aListTuile;
                int resID = m_context.getResources().getIdentifier(tuile.getM_tileset().getM_nomTileSet(), "drawable", m_context.getPackageName());
                m_img = ((BitmapDrawable) m_context.getResources().getDrawable(resID)).getBitmap();

                int largeur = 50;

                int a = nbretuiledessiné * largeur ;
                int b = ligne * largeur;
                int c = a + largeur;
                int d = b + largeur;

                Log.d("coordonnée sprite" , " "+a+" "+b+""+c+" "+d);
                Rect I = new Rect(a, b, c, d);
                Rect J = tuile.getM_coordSprite();

                screen.canvas.drawBitmap(m_img, J, I, null);

                nbretuiledessiné++;
            }

        }
        screen.invalidate();
    }

    public void update() {

    }

    public void processEvents() {
        if (getLastEvent() != null && getLastEvent().getAction() == MotionEvent.ACTION_DOWN) {

        }
        setLastEvent(null);
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




