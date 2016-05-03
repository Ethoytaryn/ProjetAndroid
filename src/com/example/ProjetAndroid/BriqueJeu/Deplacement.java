package com.example.ProjetAndroid.BriqueJeu;

import android.graphics.*;

import java.util.ArrayList;

/**
 * Created by Guillaume on 22/04/2016.
 */
public class Deplacement implements ElementJeu {

    private Paint m_sprite;
    private boolean m_isDrawable;
    private boolean m_isSelected = false;


    Deplacement(){
        m_isDrawable = false;
       m_sprite = new Paint();
        m_sprite.setColor(Color.BLUE);
        m_sprite.setAlpha(50);
    }
    @Override
    public boolean isDrawable() {
        return m_isDrawable;
    }

    @Override
    public void setDrawable(boolean e){
        m_isDrawable = e;
    }

    @Override
    public void dessiner(Canvas canvas, Rect positionElement) {
        canvas.drawRect(positionElement,m_sprite);
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
        return false;
    }

    @Override
    public boolean isDeplacement() {
        return true;
    }

    @Override
    public ArrayList<int[]> influence(int positionX, int positionY) {
        ArrayList<int[]> temp = new ArrayList<>();
        return temp;
    }
}
