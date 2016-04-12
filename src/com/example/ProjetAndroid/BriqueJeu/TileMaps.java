package com.example.ProjetAndroid.BriqueJeu;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Guillaume on 11/04/2016.
 */
public class TileMaps {

    private ArrayList<TileMap> listMap = new ArrayList<>();

    public TileMaps(NodeList listLayer, TileSets listeTileSets){

        for(int i=0; i < listLayer.getLength();i++){

            getListMap().add(new TileMap((Element)listLayer.item(i),listeTileSets));
        }
    }

    public ArrayList<TileMap> getListMap() {
        return listMap;
    }
}
