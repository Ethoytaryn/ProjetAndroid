package com.example.ProjetAndroid;

import android.app.Activity;

import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.IOException;


public class JeuActivity extends Activity {

    private AssetManager m_assetsListe;
    XMLDocument XMLcarte;
    String m_nomCarte = "carte_camp.xml";



    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);
        m_assetsListe = getAssets();
        try {
            XMLcarte = new XMLDocument(m_nomCarte,m_assetsListe);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TileSets tilesetCarte = new TileSets(XMLcarte.getNode("tileset_nature"));






    }




                /*Element e = (Element) nodeList.item(0);

                nameText.setText(parser.getValue(e, NODE_NAME));
                salaryText.setText(parser.getValue(e, NODE_SALARY));
                designationText.setText(parser.getValue(e, NODE_DESIGNATION));*/


}