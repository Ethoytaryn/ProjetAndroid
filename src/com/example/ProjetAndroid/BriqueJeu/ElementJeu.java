package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by maël on 22/04/2016.
 */
public interface ElementJeu {
    public void isDrawable();

    //Personnage action
    public Personnage getImgSource(Context context);
    public Personnage setSprite(int[] coord);
    public Bitmap getSprite();



}
