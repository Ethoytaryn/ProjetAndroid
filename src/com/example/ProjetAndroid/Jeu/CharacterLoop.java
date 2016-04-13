package com.example.ProjetAndroid.Jeu;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.MotionEvent;
import com.example.ProjetAndroid.BriqueJeu.Sprite;

/**
 * Created by maël on 06/04/2016.
 */
public class CharacterLoop extends Thread {

    public boolean running;

    boolean isMoving = false;
    /**personnage*/
    private Sprite character;
    /** le dernier évenement enregistré */
    public MotionEvent lastEvent;
    /** Valeur du FPS (nombre de frames par secondes) */
    private int fps;
    private int xSpeed;
    private int ySpeed;
    public void initGame(Context context,GameView screen,Bitmap img) {

        // créer un sprite en spécifiant la position
        character = new Sprite(img, 0, 0, 2, 3, screen);
        running = true;
    }

    private int getAnimationRow() {
        // animation: 3 down, 1 left, 0 up, 2 right
        if (Math.abs(xSpeed) > Math.abs(ySpeed)) {
            if (xSpeed > 0)
                return 2; //right
            else
                return 1; //left
        } else {
            if(ySpeed>0)
                return 0; //down
            else
                return 3; //up
        }
    }
    public void update() {
        character.update();
    }
    public void processEvents() {
        if (lastEvent != null && lastEvent.getAction() == MotionEvent.ACTION_DOWN) {
            int xTouch = (int)lastEvent.getX();
            int yTouch = (int)lastEvent.getY();
            character.moveTo(xTouch, yTouch);
        }
        lastEvent = null;
    }
    public void render( GameView screen) {
        this.character.render(screen.canvas);
        // appliquer le buffer à l'écran
        screen.invalidate();
    }
    public void run() {
        long startTime = 0;
        while (this.running) {
            startTime = System.currentTimeMillis();

            this.processEvents();
            this.update();
        }
        // calculer le FSP
        fps = (int) (1000/(System.currentTimeMillis() - startTime));
    }

    // gestion des déplacements sur les tileset
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                // Set isMoving so character is moved in the update method
                isMoving = true;

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:

                // Set isMoving so character does not move
                isMoving = false;

                break;
        }
        return true;
    }
}
