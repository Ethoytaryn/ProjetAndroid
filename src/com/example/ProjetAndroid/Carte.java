package com.example.ProjetAndroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Guillaume on 11/03/2016.
 */
public class Carte extends View {

    int rigth;
    int left;
    int top;
    int bottom;
    int dx;
    int dy;
    boolean first = true;

    Drawable horloge;
    public Carte(Context context, AttributeSet attrs) {
        super(context, attrs);
        rigth = 0;
        left = 2600;
        top = 0;
        bottom = 2560;
        dx = 0;
        dy = 0;
    }


    @Override
    public void onDraw(Canvas canvas){

            int x = canvas.getWidth();
            int y = canvas.getHeight();





        //horloge = getResources().getDrawable(R.drawable.*);
        horloge.setBounds(rigth,top,left,bottom);


        canvas.save();
        canvas.translate(dx,0);
        horloge.draw(canvas);
        canvas.restore();


    }


    public void slide(View v) {

        dx += 10;


        this.invalidate();
    }
}
