package com.example.ProjetAndroid;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;
import tmx_loader.*;
/**
 * Created by maël on 21/03/2016.
 */
public class test extends Activity {
    //ImageView mapView;
    //String FILENAME = "test1.tmx";


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        //mapView = (ImageView)findViewById(R.id.MapImage);
/*
        // Start the parser, get back TMX data object
        TileMapData t = TMXLoader.readTMX(FILENAME, this);

        mapView = (ImageView)findViewById(R.id.MapImage);

        // Create a Bitmap from the tilemap data
        Bitmap mapImage = TMXLoader.createBitmap(t, this, 0, t.layers.size());

        // Set the imageview to show the map, if we have one
        if (mapImage != null){
            mapView.setImageBitmap(mapImage);
        }
        // Map loading problem, inform the user.
        else{
            Toast errorMessage = Toast.makeText(getApplicationContext(), "Map could not be loaded", Toast.LENGTH_LONG);
            errorMessage.show();
        }
*/
    }
}