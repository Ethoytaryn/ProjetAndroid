package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.example.ProjetAndroid.BriqueJeu.ListTiles;
import com.example.ProjetAndroid.BriqueJeu.Tile;
import com.example.ProjetAndroid.Parser.*;

import java.io.IOException;

/**
 * Created by Guillaume on 04/04/2016.
 */
public class Jeu extends View{

    private AssetManager m_assetsListe;
    private XMLDocument XMLcarte;
    private String m_nomCarte = "carte_camp.xml";
    private ListTiles listTiles;
    GameView g;

    public Jeu(Context context, AttributeSet attrs) {
        super(context, attrs);


        try {
            m_assetsListe = context.getResources().getAssets();
            this.XMLcarte = new XMLDocument(m_nomCarte, m_assetsListe);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void getNomCarte(String nomCarte){
        this.m_nomCarte = nomCarte;
    }

    @Override
    public void onDraw(Canvas canvas){

        for(Tile tuile:listTiles)
            tuile.draw(canvas);
    }
}
