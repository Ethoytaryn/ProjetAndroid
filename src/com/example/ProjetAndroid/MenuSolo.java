package com.example.ProjetAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Guillaume on 16/03/2016.
 */
public class MenuSolo extends Activity implements View.OnClickListener{

    private Button buttonPartie;
    private Button buttonParam;
    private Button buttonRetour;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menusolo);



        buttonPartie = (Button) findViewById(R.id.PartieSolo);
        buttonParam = (Button) findViewById(R.id.ParamSolo);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);

        buttonPartie.setOnClickListener(this);
        buttonParam.setOnClickListener(this);
        buttonRetour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.PartieSolo){
            Intent menu = new Intent(this, PartieSolo.class);
            startActivity(menu);
        }
        
        if(v.getId() == R.id.buttonRetour){
            finish();
        }

    }
}
