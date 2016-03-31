package com.example.ProjetAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Guillaume on 16/03/2016.
 */
public class MenuMulti extends Activity implements View.OnClickListener {

    private Button buttonPartie;
    private Button buttonClassement;
    private Button buttonRetour;
    private TextView titre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menumulti);

        buttonPartie = (Button) findViewById(R.id.PartieMulti);
        buttonClassement = (Button) findViewById(R.id.Classement);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);

        buttonPartie.setOnClickListener(this);
        buttonClassement.setOnClickListener(this);
        buttonRetour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonRetour){
            this.finish();
        }

        if(v.getId() == R.id.buttonRetour){
            finish();
        }

    }
}
