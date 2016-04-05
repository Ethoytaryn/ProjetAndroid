package com.example.ProjetAndroid.Jeu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import com.example.ProjetAndroid.R;

public class GameLoop implements Runnable {

    // variable arrêt
    public boolean running;

    private long sleepTime = 100;

    /** Ecran de game_screen */
    public GameView screen;

    /** le dernier évenement enregistré sur l'écran*/
    public MotionEvent lastEvent;

    /** Position de l'image que nous dessions sur l'écran */
    private int x, y;
    /** vitesse de l'image : nombre de pixel parcouru à chaque boucle de game_screen */
    private int vx;
    /** image que nous allons dessiner */
    private Bitmap m_img;
    /** contexte de l'application */
    private Context m_context;
    /** activer ou désactiver l'animation*/
    private boolean animate;

    public void initGame(Context context) {
        m_context = context;
        m_img = ((BitmapDrawable) context.getResources().getDrawable(
                R.drawable.ic_launcher)).getBitmap();
        x = 0;
        y = 10;
        vx = 2;
        animate = true;
        running = true;
        this.screen = new GameView(m_context, this);
    }

    /** la boucle de game_screen */
    @Override
    public void run() {
        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé
        while (this.running) {
            startTime = System.currentTimeMillis();
            this.processEvents();
            this.update();
            this.render();
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepCorrected = sleepTime - elapsedTime;

            // si jamais sleepCorrected<0 alors faire une pause de 1 ms
            if (sleepCorrected < 0) {
                sleepCorrected = 1;
            }
            try {
                Thread.sleep(sleepCorrected > 0 ? sleepCorrected : 1);
            } catch (InterruptedException e) {
            }
            // calculer le FSP
            int fps = (int) (1000/(System.currentTimeMillis() - startTime));
        }
    }

    /** Dessiner les composant du game_screen sur le buffer de l'écran*/
    public void render() {
       if(animate) {
           Paint paint = new Paint();
           paint.setColor(0xFF000000);

           this.screen.canvas.drawPaint(paint);
           this.screen.canvas.drawBitmap(m_img, x, y, null);
           this.screen.invalidate();
       }
    }

    public void update() {
        if(this.animate==false) return;
        int oldX = x;
        x = x + vx;
        if (x < 0 || x > screen.width - m_img.getWidth()) {
            x = oldX;
            vx = -vx;
        }
    }

    public void processEvents() {
        if (lastEvent != null && lastEvent.getAction() == MotionEvent.ACTION_DOWN) {
            this.animate = ! this.animate;
        }
        lastEvent = null;
    }
}

                /*Element e = (Element) nodeList.item(0);

                nameText.setText(parser.getValue(e, NODE_NAME));
                salaryText.setText(parser.getValue(e, NODE_SALARY));
                designationText.setText(parser.getValue(e, NODE_DESIGNATION));*/


