package com.example.ProjetAndroid.Menu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.ProjetAndroid.R;

public class MenuPrincipale extends Activity implements View.OnClickListener {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button buttonSolo = (Button) findViewById(R.id.buttonSolo);
        Button buttonMulti = (Button) findViewById(R.id.buttonMulti);
        Button buttonPerso = (Button) findViewById(R.id.buttonPerso);
        Button buttonQuit = (Button) findViewById(R.id.buttonQuit);

        buttonSolo.setOnClickListener(this);
        buttonMulti.setOnClickListener(this);
        buttonPerso.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonSolo) {
            Intent menu = new Intent(this, MenuSolo.class);
            startActivity(menu);
        }

        if(v.getId()==R.id.buttonMulti) {
            Intent menu = new Intent(this, MenuMulti.class);
            startActivity(menu);
        }
        if(v.getId() == R.id.buttonQuit){
            finish();
            System.exit(0);
        }
    }
}
