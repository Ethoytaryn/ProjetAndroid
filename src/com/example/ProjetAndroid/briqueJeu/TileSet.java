package com.example.ProjetAndroid.BriqueJeu;

import org.w3c.dom.Element;

public class TileSet {

    String m_nomTileSet;
    int m_tilewidth;
    int m_tileheight;
    int m_spacing;
    int m_tilecount;
    int m_columns;

    TileSet(Element tileSet){

        m_nomTileSet = tileSet.getAttribute("name");
        m_tilewidth = Integer.parseInt(tileSet.getAttribute("tilewidth"));
        m_tileheight = Integer.parseInt(tileSet.getAttribute("tileheight"));
        m_spacing = Integer.parseInt(tileSet.getAttribute("spacing"));
        m_tilecount = Integer.parseInt(tileSet.getAttribute("tilecount"));
        m_columns = Integer.parseInt(tileSet.getAttribute("columns"));
    }

    public int[] getCoordSprite(int numeroTuile){
        int[] coord = new int[4];
        coord[0] = numeroTuile % m_columns; //numeroColonne
        coord[1] = (numeroTuile-coord[0])/m_columns; //numeroLigne
        coord[2] = coord[0]*(m_tilewidth+m_spacing);  //pixelX
        coord[3] = coord[1]*(m_tileheight+m_spacing); //pixelY
        if(m_nomTileSet == "tileset_nature") {

            return coord;
        }
        return null;
    }

    public int getM_tilecount(){
        return m_tilecount;
    }
}
