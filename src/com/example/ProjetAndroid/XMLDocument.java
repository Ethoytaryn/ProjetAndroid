package com.example.ProjetAndroid;

import android.app.Activity;
import android.content.res.AssetManager;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;


public class XMLDocument extends Activity {

    String m_nomFichier;
    XMLDOMParser parser;
    private Document doc;
    InputStream stream;


    XMLDocument(String nomFichier) throws IOException {

        m_nomFichier = nomFichier;
        parser = new XMLDOMParser();
        OpenDocument();
    }

    private void OpenDocument() throws IOException {
            stream =  getAssets().open(m_nomFichier);
            doc = parser.getDocument(stream);
            CloseDocument();
    }

    private void CloseDocument() throws  IOException {
        stream.close();
    }

    public NodeList getNode(String nomNode){
        return doc.getElementsByTagName(nomNode);
    }
}
