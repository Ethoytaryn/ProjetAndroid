package com.example.ProjetAndroid;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

public class Jeu extends Activity {

    String m_nomCarte = "carte_1";
    XMLDocument XMLcarte;

    Carte m_carte;



    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            XMLcarte = new XMLDocument(m_nomCarte);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TileSets test = new TileSets();

    }




                /*Element e = (Element) nodeList.item(0);

                nameText.setText(parser.getValue(e, NODE_NAME));
                salaryText.setText(parser.getValue(e, NODE_SALARY));
                designationText.setText(parser.getValue(e, NODE_DESIGNATION));*/


}