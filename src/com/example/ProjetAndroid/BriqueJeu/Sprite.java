package com.example.ProjetAndroid.BriqueJeu;

/**
 * Created by maël on 06/04/2016.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.example.ProjetAndroid.Jeu.GameView;


public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;

    private GameView gameView;
    private Bitmap image;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    /** numéro de la frame courante */
    private int currentFrame;
    /** dimension du sprite */
    private int width;
    private int height;

    public Sprite(Bitmap bmp, int x, int y, int vx, int vy, GameView gameView) {
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.gameView = gameView;
        this.image = bmp;
        this.x = x;
        this.y = y;
        this.xSpeed = vx;
        this.ySpeed = vy;
    }

    public void update() {
        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
        currentFrame = (currentFrame + 1) % BMP_COLUMNS;
    }

    private int getAnimationRow() {
        // animation: 3 back, 1 left, 0 front, 2 right
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

    public void render(Canvas canvas) {
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(image, src, dst, null);
    }

    /** déterminer la vitesse vers le point cliqué */
    public void moveTo(int x2, int y2) {
        int dx = x2-this.x;
        int dy = y2-this.y;
        double r = Math.sqrt(dx*dx+dy*dy);
        xSpeed = (int) (3*dx/r);
        ySpeed = (int) (3*dy/r);
    }

}