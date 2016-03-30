package com.example.ProjetAndroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by maël on 23/03/2016.
 */
public class Partie extends Activity{

    private ImageView gridLayout;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    Bitmap image;
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partiesolo);
        gridLayout = (ImageView)findViewById(R.id.gridLayout);

        image = BitmapFactory.decodeFile("map.png");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 90, bytes);

        //saimage.reconfigure(15,20,image.getConfig());


    }


}
