package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import com.example.ProjetAndroid.BriqueJeu.Assembleur;
import com.example.ProjetAndroid.BriqueJeu.Personnage;
import com.example.ProjetAndroid.BriqueJeu.Personnages;
import com.example.ProjetAndroid.BriqueJeu.Tile;
/**
 * Created by Guillaume on 11/03/2016.
 */
public class GameLoop implements Runnable {
    Personnages listPerso;
    Personnage bob;
    private boolean m_running;  // variable arrêt de la boucle
    private long m_sleepTime = 10;
    private Context m_context;

    //Affichage
    private int fps;
    private GameView screen; //écran de jeu
    int m_largeurTile;

    //Données partie

    private ArrayList[][] m_tableauObjet;
    private int m_nbreTile;
    private Rect[][] m_coordTileCanvas;

    //Evenement et position doigt
    private MotionEvent lastEvent; // le dernier évenement enregistré sur l'écran
    private float XEcran = 0;
    private float YEcran = 0;
    float x_frame_précédente;
    float y_frame_précédente;
    private float translateX;
    private float translateY;
    private int m_pos_clic_X;
    private int m_pos_clic_Y;
    private boolean maj;
    Boolean persoClic;

    public void initGame(Context context, Assembleur assembleur)  {
        initAction();
        m_tableauObjet = assembleur.getTableau();
        m_nbreTile = 1200;
        m_context = context;
        m_running = true;
        screen = new GameView(m_context, this);
        m_largeurTile = 50;
        m_coordTileCanvas = new Rect[30][40];
        positionDesTuiles();
        persoClic = false;
        maj = true;
        bob = new Personnage();
        int pos[] = {1,1};
        listPerso = new Personnages();
        listPerso.addPerso(bob,pos);
    }

    /** la boucle de game_screen */
    @Override
    public void run() {

        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé

        render();

        while (m_running) {
            startTime = System.currentTimeMillis();
            processEvents();
            update();
            render();
            // temps d'action
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepCorrected = m_sleepTime - elapsedTime;

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

    public void render() {

        if(maj){
           for (int i = 0; i < 30; i++) {
               for (int j = 0; j < 40; j++) {

                   Rect I = m_coordTileCanvas[i][j];

                   ArrayList listeObjet = m_tableauObjet[i][j];

                   for (Object o : listeObjet) {
                       Tile tile = new Tile();
                       if (o.getClass() == tile. getClass())
                           tile = (Tile) o;
                           if(tile.getASpite()) {

                           if (I.right > (Math.abs(XEcran) - m_largeurTile) || I.left < Math.abs(XEcran) + screen.getM_Width() || I.top > Math.abs(YEcran) - m_largeurTile || I.bottom < Math.abs(YEcran) + screen.getM_height())
                               screen.getCanva().drawBitmap(tile.getBitmap(), null, I, null);

                       }
                       else if(o.getClass() == bob.getClass()){
                               bob = (Personnage) o;

                               screen.getCanva().drawBitmap(bob.getSprite(),null,I,null);
                           }
                   }
               }
           }
            screen.invalidate();
        }
    }

    public void update() {
            if (XEcran + translateX <= 0 && (XEcran - screen.getM_Width() + translateX) > (-50 * 40)) {
                XEcran += translateX;
                screen.getCanva().translate(translateX, 0);
                translateX = 0;
            }

            if (YEcran + translateY <= 0 && YEcran - screen.getM_height() + translateY > (-50 * 30)) {
                YEcran += translateY;
                screen.getCanva().translate(0, translateY);
                translateY = 0;
            }
    }
    //récupération des évènements du joueur
    public void processEvents() {

        if(lastEvent != null) {
            if(lastEvent.getAction() == MotionEvent.ACTION_DOWN){

                float x_actuel = lastEvent.getX();
                float y_actuel = lastEvent.getY();

                float x_total = Math.abs(XEcran)+x_actuel;
                float y_total = Math.abs(YEcran)+y_actuel;

                m_pos_clic_X =(int) x_total / m_largeurTile;
                m_pos_clic_Y = (int) y_total/ m_largeurTile;

                lastEvent = null;
                ArrayList test = m_tableauObjet[m_pos_clic_Y][m_pos_clic_X];
                Tile ref = new Tile();
                persoClic = false;
                //bob = listPerso.getPersonnage(1);
                for(Object tile: test){
                    if(tile.getClass() == bob.getClass()){
                        persoClic = true;
                    }
                }
                if(!persoClic){
                        Log.d("Test","Pas de personnage");
                }
                else {
                    Log.d("Test", "Tu clique sur un perso");
                }
                maj = true;
            }
            else if (lastEvent.getAction() == MotionEvent.ACTION_MOVE) {
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
            else if (lastEvent.getAction() == MotionEvent.ACTION_UP) {
                initAction();
            }
        }
    }
    // initialisation des attributs de mouvement
    public void initAction(){
        x_frame_précédente = 0;
        y_frame_précédente = 0;
        translateX = 0;
        translateY = 0;
        m_pos_clic_X = 0;
        m_pos_clic_Y = 0;
        lastEvent = null;
        maj = false;
    }
    // positionnnement de chaque tuile
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
    //Récupération de l'écran
    public GameView getScreen() {
        return screen;
    }
    // Modification du dernier évènement
    public void setLastEvent(MotionEvent lastEvent) {
        this.lastEvent = lastEvent;
    }
    //Gestion du déplacement du personnage
    public void deplacementPerso(){

        if(persoClic && lastEvent.getAction() == MotionEvent.ACTION_MOVE){

        }
    }


}




