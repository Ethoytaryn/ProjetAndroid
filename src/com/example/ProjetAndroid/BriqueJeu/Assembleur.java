package com.example.ProjetAndroid.BriqueJeu;

import android.content.Context;
import android.util.DisplayMetrics;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Guillaume on 13/04/2016.
 */
public class Assembleur {
    TileSets m_listTileSet;
    TileMaps m_listTileMap;
    DisplayMetrics m_metric;

    private ArrayList[][] m_tableau;
    private int m_widthMap;
    private int m_heightMap;

    public Assembleur(DisplayMetrics metrics){
        m_metric = metrics;
        créationDuTableau();
    }

    public Assembleur getInfoTileSet(NodeList infoTileSet, Context context){
        m_listTileSet = new TileSets(infoTileSet,m_metric.densityDpi,context);
        return this;
    }

    public Assembleur getInfoMap(NodeList infoMap){
        m_listTileMap = new TileMaps(infoMap,m_listTileSet);
        remplirTableau();
        return this;
    }
    private void créationDuTableau(){
        m_tableau = new ArrayList[30][40];
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 40; j++){
                m_tableau[i][j] = new ArrayList();
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

    public ArrayList[][] getTableau() {
        return m_tableau;
    }

    public int getM_widthMap() {
        return m_widthMap;
    }

    public int getM_heightMap() {
        return m_heightMap;
    }
}
