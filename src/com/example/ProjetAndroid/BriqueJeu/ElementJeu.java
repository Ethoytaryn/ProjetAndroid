package com.example.ProjetAndroid.BriqueJeu;


import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Guillaume on 22/04/2016.
 */
public interface ElementJeu {

    boolean isDrawable();
    void setDrawable(boolean e);
    void dessiner(Canvas canvas, Rect positionElement);
    boolean isSelected();
    void setSelected(boolean e);
    boolean isPersonnage();
    boolean isTile();
    int getTypeTile();
    boolean isDeplacement();
    boolean isObstacle();
    void setAcces(ArrayList<ElementJeu> caseTile);

    ArrayList<int[]> influence(int colonne, int ligne);



}
