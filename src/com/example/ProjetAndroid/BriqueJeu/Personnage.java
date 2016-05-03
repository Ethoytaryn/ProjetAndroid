package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;

/**
 * Created by Guillaume on 16/04/2016.
 */

public class Personnage implements ElementJeu {


    private String m_nomTileSet = "tileset_persobase";
    private Bitmap m_img_src;
    private Bitmap m_sprite;
    //caractéristique
    private int m_déplacement = 1;
    private boolean m_isDrawable = true;
    private boolean m_isSelected = false;

    public Personnage(){

    }

    @Override
    public boolean isDrawable() {
        return m_isDrawable;
    }

    @Override
    public void setDrawable(boolean e) {
        m_isDrawable = e;
    }

    @Override
    public void dessiner(Canvas canvas,Rect I) {
        canvas.drawBitmap(m_sprite, null, I, null);
    }

    @Override
    public boolean isSelected() {
        return m_isSelected;
    }

    @Override
    public void setSelected(boolean e) {
        m_isSelected = e;
    }

    @Override
    public boolean isPersonnage() {
        return true;
    }

    @Override
    public boolean isTile() {
        return false;
    }

    @Override
    public boolean isDeplacement() {
        return false;
    }

    @Override
    public ArrayList<int[]> influence(int ligne, int colonne) {
        ArrayList<int[]> listCoord = new ArrayList<>();
        int[] init = {ligne,colonne};
        listCoord.add(init);
        for(int i = 1; i <= m_déplacement;i++){
            int[] coordplus = {ligne,colonne-i};
            int[] coordmois = {ligne,colonne+i};
            listCoord.add(coordplus);
            listCoord.add(coordmois);
        }
        return listCoord;
    }



    public Personnage getImgSource(Context context){
        int resID = context.getResources().getIdentifier(m_nomTileSet, "drawable", context.getPackageName());
        m_img_src = ((BitmapDrawable) context.getResources().getDrawable(resID)).getBitmap();
        return this;
    }
    //Modification de la position du sprite
    public Personnage setSprite(int[] coord){
       m_sprite = Bitmap.createBitmap(m_img_src,coord[0],coord[1],coord[2],coord[3]);
        return this;
    }

}
