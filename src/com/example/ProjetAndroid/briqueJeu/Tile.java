package com.example.ProjetAndroid.BriqueJeu;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;


/**
 * Created by Guillaume on 30/03/2016.
 */
public class Tile {

    private int m_numeroSprite;
    private TileSet m_tileset;
    private boolean m_getASprite;
    private Bitmap m_bitmap;

    public Tile(int numeroSprite, TileSets listeTileSets) {

        m_numeroSprite = numeroSprite;
        m_tileset = listeTileSets.getTileSet(m_numeroSprite);

        if (numeroSprite !=0){
            m_getASprite = true;

            m_bitmap = m_tileset.getBitMap(m_numeroSprite);
        }
        else
        {

            m_getASprite = false;
        }

    }

    public int getNumeroSprite(){
        return m_numeroSprite;
    }
    public Bitmap getBitmap(){
        return m_bitmap;
    }

    public boolean getASpite() {
        return m_getASprite;
    }
}
