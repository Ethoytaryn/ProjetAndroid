package com.example.ProjetAndroid.BriqueJeu;

import android.graphics.*;
import android.util.Log;

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

    @Override
    public void setAcces(ArrayList<ElementJeu> case_selected){
        for(ElementJeu element : case_selected){
            if(element.getTypeTile()==2){
                m_obstacle = false;
                break;
            }
            else if(element.getTypeTile()==1){
                m_obstacle = true;
            }
        }

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
    public int getTypeTile() {
        return -1;
    }

    @Override
    public boolean isDeplacement() {
        return true;
    }

    @Override
    public boolean isObstacle() {
        return m_obstacle;
    }


    @Override
    public ArrayList<int[]> influence(int positionX, int positionY) {
        ArrayList<int[]> temp = new ArrayList<>();
        return temp;
    }
}
