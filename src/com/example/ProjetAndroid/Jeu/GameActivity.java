package com.example.ProjetAndroid.Jeu;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.example.ProjetAndroid.BriqueJeu.*;
import com.example.ProjetAndroid.Parser.XMLDocument;
import org.w3c.dom.NodeList;


import java.io.IOException;
import java.util.ArrayList;


public class GameActivity extends Activity {

    private GameLoop gameLoop;
    private DisplayMetrics metrics;
    private String m_nomCarte = "carte_camp.xml";
    private XMLDocument m_carte;
    private TileSets listTileSet;
    private TileMaps listTileMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //On sauvegarde la taille de l'écran
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        try {
            m_carte = new XMLDocument(m_nomCarte, getAssets());

            NodeList elist1 = m_carte.getNode("tileset");
            NodeList elist2 = m_carte.getNode("layer");

            //traitement des données
            listTileSet = new TileSets(elist1,metrics.densityDpi);
            listTileMap = new TileMaps(elist2,listTileSet);

        } catch (IOException e) {
            e.printStackTrace();
        }



        gameLoop = new GameLoop();

        gameLoop.initGame(this, metrics,listTileMap);

        setContentView(gameLoop.getScreen());




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}