package com.example.ProjetAndroid;

import android.content.res.AssetManager;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.io.InputStream;


public class XMLDocument {

    private String m_nomFichier;
    private XMLDOMParser m_parser;
    private Document m_doc;
    private AssetManager m_listAssets;

    XMLDocument(String nomFichier, AssetManager listAssets) throws IOException {

        m_nomFichier = nomFichier;
        m_parser = new XMLDOMParser();
        m_listAssets = listAssets;


        EnregistrerDocument();

    }

    private void EnregistrerDocument() throws IOException {
        InputStream stream = m_listAssets.open(m_nomFichier);
        m_doc = m_parser.getDocument(stream);
        stream.close();

    }


    public NodeList getNode(String nomNode){
        return m_doc.getElementsByTagName(nomNode);
    }
}
