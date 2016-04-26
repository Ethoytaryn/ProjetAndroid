package com.example.ProjetAndroid.BriqueJeu;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;


public class TileMap {

    private ArrayList<Tile> listTiles = new ArrayList<>();
    private String m_nomCouche;
    private int m_largeur;
    private int m_hauteur;

    TileMap(Element e,TileSets listeTileSets){

        m_nomCouche = e.getAttribute("name");
        m_largeur = Integer.parseInt(e.getAttribute("width"));
        m_hauteur = Integer.parseInt(e.getAttribute("height"));

        NodeList tuiles = e.getElementsByTagName("tile");


        for(int i=0; i < tuiles.getLength();i++){
            Element tuile = (Element) tuiles.item(i);
            int nmrTuile = Integer.parseInt(tuile.getAttribute("gid"));
            listTiles.add(new Tile(nmrTuile,listeTileSets));
        }



    }


    public int getM_largeur() {
        return m_largeur;
    }

    public ArrayList<Tile> getListTiles() {
        return listTiles;
    }

    public String getM_nomCouche() {
        return m_nomCouche;
    }

    public int getM_hauteur() {
        return m_hauteur;
    }
}
