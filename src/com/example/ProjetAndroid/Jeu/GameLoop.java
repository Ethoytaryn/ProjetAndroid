package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import com.example.ProjetAndroid.BriqueJeu.Assembleur;
import com.example.ProjetAndroid.BriqueJeu.ElementJeu;
import com.example.ProjetAndroid.BriqueJeu.Personnage;
import com.example.ProjetAndroid.BriqueJeu.Personnages;

import java.util.ArrayList;


public class GameLoop implements Runnable {

    private boolean m_running;  // variable arrêt de la boucle
    private long m_sleepTime = 10;
    private Context m_context;

    //affichage
    private int fps;
    private GameView screen; //écran de jeu
    int m_largeurTile;

    //données partie

    private ArrayList[][] m_tableauObjet;
    private Rect[][] m_coordTileCanvas;

    //evenement et position doigt
    private MotionEvent lastEvent; // le dernier évenement enregistré sur l'écran
    private float XEcran = 0;
    private float YEcran = 0;
    float x_frame_précédente = 0;
    float y_frame_précédente = 0;
    private float translateX = 0;
    private float translateY = 0;
    private ArrayList<int[]> m_coordPerso;
    private ArrayList<Personnage> m_listPersonnages;
    private boolean m_AfficherPorteePerso;
    private int m_clic_colonne;
    private int m_clic_ligne;
    private boolean m_mvmtPerso = false;


    public void initGame(Context context, Assembleur assembleur)  {

        m_tableauObjet = assembleur.getTableau();
        m_listPersonnages = assembleur.getPersonnages().getListPerso();
        m_coordPerso = assembleur.getPersonnages().getCoordPerso();
        m_listPersonnages.get(0).setSelected(true);
        m_AfficherPorteePerso = true;

        m_context = context;
        m_running = true;
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

        while (m_running) {

            startTime = System.currentTimeMillis();

            processEvents();

            updatePerso();
            updateDeplacement();
            updateCarte();

            render();



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

           for (int i = 0; i < 30; i++) {
               for (int j = 0; j < 40; j++) {

                   Rect I = m_coordTileCanvas[i][j];

                   ArrayList<ElementJeu> listeObjet = m_tableauObjet[i][j];

                   for (ElementJeu o : listeObjet) {

                       if (o.isDrawable()) {

                           if (I.right > (Math.abs(XEcran) - m_largeurTile) || I.left < Math.abs(XEcran) + screen.getM_Width() || I.top > Math.abs(YEcran) - m_largeurTile || I.bottom < Math.abs(YEcran) + screen.getM_height())
                               o.dessiner(screen.getCanva(), I);
                       }
                   }
               }
           }
            screen.invalidate();

    }

    public void updatePerso(){
        if(m_mvmtPerso){

            int rangPersoActif = Personnages.getPersonnageActif(m_listPersonnages);
            int[] coord = Personnages.getCoordPersonnageActif(rangPersoActif,m_coordPerso);
            ElementJeu tile = findPersoActif(rangPersoActif,m_listPersonnages);
            m_tableauObjet = Assembleur.bougerTile(coord[0],coord[1],m_clic_ligne,m_clic_colonne,tile,m_tableauObjet);
            m_coordPerso = Personnages.setCoordPerso(m_coordPerso,rangPersoActif,m_clic_ligne,m_clic_colonne);
            resetMvtCase();

            m_AfficherPorteePerso = true;
            m_mvmtPerso = false;
        }
    }

    public void updateDeplacement() {

        if (m_AfficherPorteePerso) {
            for (Personnage bob : m_listPersonnages) {
                int[] coord = m_coordPerso.get(m_listPersonnages.indexOf(bob));
                ArrayList<int[]> caseAPorte = bob.influence(coord[0], coord[1]);
                if (bob.isSelected()) {
                    setDrawableDeplacement(caseAPorte, true);
                } else {
                    setDrawableDeplacement(caseAPorte, true);
                }
            }
            m_AfficherPorteePerso = false;
        }
    }

        public void updateCarte(){


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

    public void processEvents() {

        if(lastEvent != null) {

            float x_actuel = lastEvent.getX();
            float y_actuel = lastEvent.getY();

            float x_total = Math.abs(XEcran)+x_actuel;
            float y_total = Math.abs(YEcran)+y_actuel;

            m_clic_colonne =(int) x_total / m_largeurTile;
            m_clic_ligne = (int) y_total/ m_largeurTile;

            if(lastEvent.getAction() == MotionEvent.ACTION_DOWN){

                ArrayList<ElementJeu> caseSelec = m_tableauObjet[m_clic_ligne][m_clic_colonne];
                for(ElementJeu deplacement : caseSelec){
                    if(deplacement.isDeplacement()){
                        if(deplacement.isDrawable()){
                            m_mvmtPerso = true;
                        }
                    }
                }

            }
            else if (lastEvent.getAction() == MotionEvent.ACTION_MOVE) {

                m_mvmtPerso = false;
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

                m_mvmtPerso = false;
                x_frame_précédente = 0;
                y_frame_précédente = 0;
                translateX = 0;
                translateY = 0;
                m_clic_colonne = 0;
                m_clic_ligne = 0;

            }

            lastEvent = null;
        }
    }

    public GameView getScreen() {
        return screen;
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

    public void setLastEvent(MotionEvent lastEvent) {
        this.lastEvent = lastEvent;
    }

    private void resetMvtCase(){
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 40; j++) {
                ArrayList<ElementJeu> test = m_tableauObjet[i][j];
                for(ElementJeu element : test){
                    if(element.isDeplacement()){
                        element.setDrawable(false);
                    }
                }
            }
        }

    }

    private ElementJeu findPersoActif(int rang,ArrayList<Personnage> listPerso ) {
        ElementJeu perso = listPerso.get(rang);
        return perso;
    }

    private void setDrawableDeplacement(ArrayList<int[]> caseSelect,boolean e){
        for(int[] coord : caseSelect) {
            ArrayList<ElementJeu> temp = m_tableauObjet[coord[0]][coord[1]];
            for (ElementJeu element : temp) {
                if (element.isDeplacement()) {
                    element.setDrawable(e);
                }

            }
        }

    }



}




