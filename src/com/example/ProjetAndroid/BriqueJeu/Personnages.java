package com.example.ProjetAndroid.BriqueJeu;

import java.util.ArrayList;

/**
 * Created by Guillaume on 20/04/2016.
 */
public class Personnages {
    private ArrayList<Personnage> m_listPersonnage = new ArrayList<>();
    private ArrayList<int[]> m_coordPerso = new ArrayList<>();
    private int[] tableauErreur = new int[2];

    public Personnages(){

    }

    public void addPerso(Personnage perso, int[]posPerso){
        m_listPersonnage.add(perso);
        m_coordPerso.add(posPerso);
    }

    public ArrayList<Personnage> getListPerso(){
        return m_listPersonnage;
    }
    public Personnage getPersonnage(int index){
       return m_listPersonnage.get(index);
    }
    public int[] getCoordPersonage(Personnage perso) {

        for (Personnage m_perso : m_listPersonnage) {
            if (m_perso == perso) {
                int index = m_listPersonnage.indexOf(m_perso);
                return m_coordPerso.get(index);
            }
        }
        return tableauErreur;
    }
}
