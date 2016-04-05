package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Guillaume on 04/04/2016.
 */
public class ListTiles extends View {

    ArrayList<Tile> listTiles = new ArrayList<>();

    ListTiles(Context context, AttributeSet attrs){
        super(context, attrs);


    }

    Void setListtile(NodeList e){
        for(int i=0; i < e.getLength();i++){
            listTiles.add(new Tile(context,attrs));
        }
    }

}
