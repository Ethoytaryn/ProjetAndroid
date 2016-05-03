package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guillaume on 13/04/2016.
 */
public class Assembleur {

    private TileSets m_listTileSet;
    private TileMaps m_listTileMap;
    private DisplayMetrics m_metric;
    private ArrayList[][] m_tableau;
    private Personnages m_listPerso;


    public Assembleur(DisplayMetrics metrics){
        m_metric = metrics;
        créationDuTableau();
    }

    public Assembleur getInfoTileSet(NodeList infoTileSet, Context context){
        m_listTileSet = new TileSets(infoTileSet,m_metric.densityDpi,context);
        return this;
    }

    public Assembleur getInfoPerso(Personnages listPerso){
        m_listPerso = listPerso;

        ArrayList<Personnage> temp = m_listPerso.getListPerso();
        for(Personnage perso : temp){
            int[] coordPerso = m_listPerso.getCoordPersonage(perso);
            m_tableau[coordPerso[0]][coordPerso[1]].add(perso);
        }
        return this;
    }

    public Assembleur getInfoMap(NodeList infoMap){
        m_listTileMap = new TileMaps(infoMap,m_listTileSet);
        remplirTableau();
        ajoutElementDeplacement();
        return this;
    }

    private void créationDuTableau(){
        m_tableau = new ArrayList[30][40];
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 40; j++){
                m_tableau[i][j] = new <ElementJeu> ArrayList();
            }
        }
    }

    private void remplirTableau(){
        ArrayList<TileMap> listMap = m_listTileMap.getListMap();

        for (TileMap map:listMap) {

            ArrayList<Tile> listTiles = map.getListTiles();

            for(int i = 0; i< 30; i++){
                for(int j = 0; j < 40; j++){

                    m_tableau[i][j].add(listTiles.get((i*40)+j));
                }
            }
        }

    }

    private void ajoutElementDeplacement(){
        for(int i = 0; i< 30; i++){
            for(int j = 0; j < 40; j++){

                m_tableau[i][j].add(new Deplacement());
            }
        }
    }

    static public ArrayList[][] bougerTile(int ligneStart, int colStart,int ligneFinish, int colFinish, ElementJeu bob,ArrayList[][] tableau){

        ArrayList<ElementJeu> colonneStart = tableau[ligneStart][colStart];
        ArrayList<ElementJeu> colonneFinish = tableau[ligneFinish][colFinish];
        colonneStart.remove(bob);
        colonneFinish.add(bob);

        return tableau;
    }
    public ArrayList[][] getTableau() {
        return m_tableau;
    }

    public Personnages getPersonnages(){ return m_listPerso;}

}
