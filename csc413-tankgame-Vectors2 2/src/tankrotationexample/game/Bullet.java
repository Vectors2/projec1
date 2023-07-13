package tankrotationexample.game;

import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

public class Bullet   {
    private float x;
    private float y;
    private float angle;
   protected Rectangle hitBox;

    protected int life;



    float scalefactor = 1f;
    private float R = 5;
    private BufferedImage img;



    Bullet(float x, float y, float angle, float charge, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.scalefactor = charge;
        this.angle = angle;
    }

    void setPosition(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;

    }


    void update() {
        moveForwards();
        //scalefactor+=0.05;
    }


    private void moveForwards() {
        x += Math.round(R * Math.cos(Math.toRadians(angle)));
        y += Math.round(R * Math.sin(Math.toRadians(angle)));
        // checkBorder();
    }


    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.WORLD_WIDTH - 88) {
            x = GameConstants.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.WORLD_HEIGHT - 80) {
            y = GameConstants.WORLD_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        rotation.scale(scalefactor, scalefactor);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        g2d.setColor(Color.RED);
        //g2d.rotate(Math.toRadians(angle), bounds.x + bounds.width/2, bounds.y + bounds.height/2);
        g2d.drawRect((int) x, (int) y, this.img.getWidth() * (int) this.scalefactor, this.img.getHeight() * (int) this.scalefactor);

    }

    public void setScalefactor(float scalefactor) {

        this.scalefactor = scalefactor;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getHitBox(){
       return new Rectangle((int)x, (int)y, this.img.getWidth(), this.img.getHeight());
    }

    public BufferedImage getImg() {
        return img;
    }

    public float getScalefactor() {
        return scalefactor;
    }






}
