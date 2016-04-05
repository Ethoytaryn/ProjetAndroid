package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.example.ProjetAndroid.R;

/**
 * Created by Guillaume on 30/03/2016.
 */
public class Tile extends View {

    TileSet tileSet;
    int m_numeroTuile;

    public Tile(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void onDraw(Canvas canvas,int[]coord){
        Drawable tuile = getResources().getDrawable(R.drawable.tileset_nature);
        tuile.setBounds(coord[0],coord[1],coord[2],coord[3]);
        tuile.draw(canvas);

    }
}
