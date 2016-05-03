package com.example.ProjetAndroid.Jeu;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.example.ProjetAndroid.BriqueJeu.*;
import com.example.ProjetAndroid.Parser.XMLDocument;
import org.w3c.dom.NodeList;
import java.io.IOException;
/**
 * Created by Guillaume on 11/03/2016.
 */

public class GameActivity extends Activity {

    private GameLoop m_gameLoop; //mon Thread
    private DisplayMetrics m_metrics; //information sur l'écran
    private String m_nomCarte = "carte_camp.xml";
    private XMLDocument m_carte;
    private Assembleur m_assembleur;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //L'activity est mise en plein écran et en orientation paysage
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //On sauvegarde la taille de l'écran
        m_metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(m_metrics);

        try {
            //on ouvre la carte
            m_carte = new XMLDocument(m_nomCarte, getAssets());

            //on extrait les données nécessaire
            NodeList elist1 = m_carte.getNode("tileset");
            NodeList elist2 = m_carte.getNode("layer");

            int[] coord = new int[4];
            coord[0] = 0;
            coord[1] = 2*(64*2);
            coord[2] = 64*2;
            coord[3] = 64*2;

            Personnage premierPerso = new Personnage();
            premierPerso.getImgSource(this).setSprite(coord);

            Personnages listPerso = new Personnages();
            int[] position ={15,10};
            listPerso.addPerso(premierPerso,position);

            //traitement des données
            m_assembleur = new Assembleur(m_metrics);
            m_assembleur.getInfoTileSet(elist1,this).getInfoMap(elist2).getInfoPerso(listPerso);

        } catch (IOException e) {
            e.printStackTrace();
        }

        m_gameLoop = new GameLoop();
        m_gameLoop.initGame(this,m_assembleur);
        setContentView(m_gameLoop.getScreen());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}