package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import org.w3c.dom.Element;

public class TileSet {

    private String m_nomTileSet;
    private int m_tilewidth;
    private int m_tileheight;
    private int m_spacing;
    int m_tilecount;
    private int m_columns;
    private int m_densityDPI;
    private Bitmap m_imgSource;

    TileSet(Element tileSet, int densityDPI, Context context){

        m_nomTileSet = tileSet.getAttribute("name");
        m_tilewidth = Integer.parseInt(tileSet.getAttribute("tilewidth"));
        m_tileheight = Integer.parseInt(tileSet.getAttribute("tileheight"));
        m_spacing = Integer.parseInt(tileSet.getAttribute("spacing"));
        m_tilecount = Integer.parseInt(tileSet.getAttribute("tilecount"));
        m_columns = Integer.parseInt(tileSet.getAttribute("columns"));
        m_densityDPI = densityDPI;
        getImgSource(context);

    }

    private void getImgSource(Context context){
        int resID = context.getResources().getIdentifier(m_nomTileSet, "drawable", context.getPackageName());
        m_imgSource = ((BitmapDrawable) context.getResources().getDrawable(resID)).getBitmap();
    }

    public Bitmap getBitMap(int numeroSprite){
        int[] coord = getCoordSprite(numeroSprite);
        return Bitmap.createBitmap(m_imgSource,coord[0],coord[1],coord[2],coord[3]);
    }

    public int[] getCoordSprite(int numeroTuile){
        int[] coord = new int[4];

        int col = (numeroTuile % m_columns)-1;
        int ligne = numeroTuile / m_columns;
        int coeff = m_densityDPI/160;
        coord[0] = 1+(col)*(m_tilewidth+m_spacing)*coeff; //numeroColonne
        coord[1] = 1+ligne*(m_tileheight+m_spacing)*coeff; //numeroLigne
        coord[2] = (m_tilewidth-m_spacing)*coeff;  //pixelX
        coord[3] = (m_tileheight-m_spacing)*coeff; //pixelY

        return coord;
    }

    public int getM_tilecount(){
        return m_tilecount;
    }

    public String getM_nomTileSet() {
        return m_nomTileSet;
    }

    public int getM_tilewidth() {
        return m_tilewidth;
    }

    public int getM_tileheight() {
        return m_tileheight;
    }

    public int getM_spacing() {
        return m_spacing;
    }

    public int getM_columns() {
        return m_columns;
    }
}
