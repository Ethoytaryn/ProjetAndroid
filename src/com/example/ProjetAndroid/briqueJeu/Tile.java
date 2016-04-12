package com.example.ProjetAndroid.BriqueJeu;


import android.graphics.Rect;


/**
 * Created by Guillaume on 30/03/2016.
 */
public class Tile {

    private int m_numeroSprite;
    private Rect m_coordSprite;
    private TileSet m_tileset;


    public Tile(int numeroSprite,TileSets listeTileSets) {
        this.m_numeroSprite = numeroSprite;
        m_tileset = listeTileSets.getTileSet(m_numeroSprite);
        m_coordSprite = m_tileset.getCoordSprite(m_numeroSprite);
    }

    public int getM_numeroSprite() {
        return m_numeroSprite;
    }

    public Rect getM_coordSprite() {
        return m_coordSprite;
    }

    public TileSet getM_tileset() {
        return m_tileset;
    }
}
