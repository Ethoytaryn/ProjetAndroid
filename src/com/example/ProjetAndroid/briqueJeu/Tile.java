package com.example.ProjetAndroid.BriqueJeu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;


/**
 * Created by Guillaume on 30/03/2016.
 */
public class Tile implements ElementJeu {

    private int m_numeroSprite;
    private TileSet m_tileset;
    private boolean m_isDrawable;
    private Bitmap m_bitmap;
    private boolean m_isSelected = false;
    private boolean m_obstacle;

    public Tile(){

    }

    public Tile(int numeroSprite, TileSets listeTileSets, boolean obstacle) {

        m_numeroSprite = numeroSprite;
        m_tileset = listeTileSets.getTileSet(m_numeroSprite);

        if (numeroSprite !=0){
            m_isDrawable = true;
            m_obstacle = obstacle;
            m_bitmap = m_tileset.getBitMap(m_numeroSprite);
        }
        else
        {
            m_obstacle = false;
            m_isDrawable = false;
        }
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
    public void dessiner(Canvas canvas, Rect positionElement) {
        canvas.drawBitmap(m_bitmap, null, positionElement, null);
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
        return false;
    }

    @Override
    public boolean isTile() {
        return true;
    }

    @Override
    public boolean isDeplacement() {
        return false;
    }

    @Override
    public boolean isObstacle() {
        return m_obstacle;
    }

    @Override
    public void setObstacle(boolean e) {

    }

    @Override
    public ArrayList<int[]> influence(int positionX, int positionY) {
        ArrayList<int[]> temp = new ArrayList<>();
        return temp;
    }

}
