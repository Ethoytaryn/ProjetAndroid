package com.example.ProjetAndroid.Jeu;

import android.app.Activity;
import android.os.Bundle;
import com.example.ProjetAndroid.R;


public class JeuActivity extends Activity {

    private Jeu m_jeu;
    private String m_nomCarte;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);

        m_jeu =(Jeu) findViewById(R.id.jeu);
        m_jeu.getNomCarte(m_nomCarte);


    }




                /*Element e = (Element) nodeList.item(0);

                nameText.setText(parser.getValue(e, NODE_NAME));
                salaryText.setText(parser.getValue(e, NODE_SALARY));
                designationText.setText(parser.getValue(e, NODE_DESIGNATION));*/


}