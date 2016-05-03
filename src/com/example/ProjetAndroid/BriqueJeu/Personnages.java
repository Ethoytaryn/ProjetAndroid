package com.example.ProjetAndroid.BriqueJeu;

import java.util.ArrayList;

/**
 * Created by Guillaume on 20/04/2016.
 */
public class Personnages {
    private ArrayList<Personnage> m_listPersonnage = new ArrayList<>();
    private ArrayList<int[]> m_coordPerso = new ArrayList<>();

    public Personnages(){

    }

    public void addPerso(Personnage perso, int[]posPerso){
        m_listPersonnage.add(perso);
        m_coordPerso.add(posPerso);
    }

    public ArrayList<Personnage> getListPerso(){
        return m_listPersonnage;
    }

    public ArrayList<int[]> getCoordPerso(){
        return m_coordPerso;
    }

    public int[] getCoordPersonage(Personnage perso) {
        int[] tableauErreur = new int[2];
        for (Personnage m_perso : m_listPersonnage) {
            if (m_perso == perso) {
                int index = m_listPersonnage.indexOf(m_perso);
                return m_coordPerso.get(index);
            }
        }
        return tableauErreur;
    }

    static public int[] getCoordPersonnageActif(int rangPerso,ArrayList<int[]> listCoordPerso){
        int[] coord = {0,0};

                coord = listCoordPerso.get(rangPerso);


           return coord;
    }

    public static int getPersonnageActif(ArrayList<Personnage> listPerso) {
        int i = -1;
        for (Personnage bob :listPerso) {
            if(bob.isSelected()){
                i = listPerso.indexOf(bob);
            }
        }
        return i;
    }

    public static ArrayList<int[]> setCoordPerso(ArrayList<int[]> coordPersonnages, int rangPersoActif, int m_clic_colonne, int m_clic_ligne) {
        int[] coord = {m_clic_colonne,m_clic_ligne};
        ArrayList<int[]> temp = coordPersonnages;
        temp.set(rangPersoActif,coord);
        return temp;
    }
}
