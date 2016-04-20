package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by Guillaume on 16/04/2016.
 */
public class Personnage {

    private String m_nomTileSet = "tileset_persobase";
    private Bitmap m_img_src;
    private Bitmap m_sprite;

    public Personnage(){

    }

    public Personnage getImgSource(Context context){
        int resID = context.getResources().getIdentifier(m_nomTileSet, "drawable", context.getPackageName());
        m_img_src = ((BitmapDrawable) context.getResources().getDrawable(resID)).getBitmap();
        return this;
    }

    public Personnage setSprite(int[] coord){
       m_sprite = Bitmap.createBitmap(m_img_src,coord[0],coord[1],coord[2],coord[3]);
        return this;
    }

    public Bitmap getSprite(){
        return m_sprite;
    }

}
