package com.example.ProjetAndroid;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.Object;

public class MyActivity extends Activity implements View.OnClickListener {

    private Button buttonSolo;
    private Button buttonPerso;
    private Button buttonMulti;
    private Button buttonQuit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buttonSolo = (Button) findViewById(R.id.buttonSolo);
        buttonMulti = (Button) findViewById(R.id.buttonMulti);
        buttonPerso = (Button) findViewById(R.id.buttonPerso);
        buttonQuit = (Button) findViewById(R.id.buttonQuit);

        buttonSolo.setOnClickListener(this);
        buttonMulti.setOnClickListener(this);
        buttonPerso.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonQuit){
            finish();
            System.exit(0);
        }
    }
}
