package com.example.ProjetAndroid.BriqueJeu;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

/**
 * Created by ,Guillaume on 31/03/2016.
 */
public class TileSets {

    private ArrayList<TileSet> listTileSets = new ArrayList<>();

    public TileSets(NodeList e, int densityDPI){

        for(int i=0; i < e.getLength();i++){
            listTileSets.add(new TileSet((Element)e.item(i),densityDPI));
        }
    }

    public TileSet getTileSet(int numeroTuile){

        int nombreTuile =0;
        TileSet select = null;

        for (TileSet a : listTileSets) {
            nombreTuile += a.getM_tilecount();
            if (numeroTuile < nombreTuile) {
                select = a;
                break;
            }
        }
        return select;

    }
}
