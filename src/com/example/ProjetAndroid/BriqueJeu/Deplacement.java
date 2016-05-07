package com.example.ProjetAndroid.BriqueJeu;

import android.graphics.*;

import java.util.ArrayList;

/**
 * Created by Guillaume on 22/04/2016.
 */
public class Deplacement implements ElementJeu {

    private Paint m_sprite;
    private boolean m_isDrawable;
    private boolean m_isSelected;
    private boolean m_obstacle;

    public Deplacement(){
        m_isDrawable = false;
        m_isSelected = false;
        m_obstacle = false;
        m_sprite = new Paint();


    }
    @Override
    public boolean isDrawable() {
        return m_isDrawable;
    }
    public void setObstacle(boolean obstacle){
        m_obstacle = obstacle;
    }
    @Override
    public void setDrawable(boolean e){
        m_isDrawable = e;
    }

    @Override
    public void dessiner(Canvas canvas, Rect positionElement) {
        if(m_obstacle){
            m_sprite.setColor(Color.RED);
        }
        else{
            m_sprite.setColor(Color.BLUE);
        }
        m_sprite.setAlpha(70);
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
    public boolean isObstacle() {
        return false;
    }

    @Override
    public ArrayList<int[]> influence(int positionX, int positionY) {
        ArrayList<int[]> temp = new ArrayList<>();
        return temp;
    }
}
