package com.example.ProjetAndroid.BriqueJeu;

import android.graphics.Rect;
import org.w3c.dom.Element;

public class TileSet {

    private String m_nomTileSet;
    private int m_tilewidth;
    private int m_tileheight;
    private int m_spacing;
    int m_tilecount;
    private int m_columns;

    TileSet(Element tileSet){

        m_nomTileSet = tileSet.getAttribute("name");
        m_tilewidth = Integer.parseInt(tileSet.getAttribute("tilewidth"));
        m_tileheight = Integer.parseInt(tileSet.getAttribute("tileheight"));
        m_spacing = Integer.parseInt(tileSet.getAttribute("spacing"));
        m_tilecount = Integer.parseInt(tileSet.getAttribute("tilecount"));
        m_columns = Integer.parseInt(tileSet.getAttribute("columns"));
    }

    public Rect getCoordSprite(int numeroTuile){
        Rect coord = new Rect();

        int col = (numeroTuile % m_columns)-1;
        int ligne = numeroTuile / m_columns;

        int left = 1+(col)*2*(m_tilewidth+m_spacing); //numeroColonne
        int top = 1+ligne*(m_tileheight+m_spacing)*2; //numeroLigne
        int right = left + (m_tilewidth-m_spacing)*2;  //pixelX
        int bottom = top + (m_tileheight-m_spacing)*2; //pixelY

        coord.set(left,top,right,bottom);
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
