package tankrotationexample.game;

import tankrotationexample.GameConstants;
import tankrotationexample.ResourcesM;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author anthony-pc
 */
public class Tank {

    private float x;
    private float y;
    private float vx;
    private float vy;
    private float angle;
    int health = 100;
    int lives = 3;

    private float R = 1f;
    private float ROTATIONSPEED = 2f;

    float fireDelay = 120f;
    float coolDown = 0f;
    float rateOfFile = 2f;

    float charge = 1f;
    float chargeRate = 0.05f;


    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shootPressed;
    private Rectangle hitBox;


    Bullet b;
    List<Bullet> ammo = new ArrayList<>();


    Tank(float x, float y, float vx, float vy, float angle, BufferedImage img) {
        super();
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.health =100;
        this.hitBox = new Rectangle((int)x, (int)y, this.img.getWidth(), this.img.getHeight());
    }
    void setPosition(float x, float y){
        this.x = x;
        this.y=y;
        this.hitBox.setLocation((int)x, (int)y);
        //add hitBox move

    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    void setX(float x) {
        this.x = x;
    }

    void setY(float y) {
        this.y = y;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShotPressed() {
        this.shootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShotPressed() {
        this.shootPressed = false;

        if (b != null) {
            b.setScalefactor(charge);
            b.setPosition(setBulletStartX(), setBulletStartY(), angle);
            this.charge = 0;
            this.ammo.add(b);
            b = null;
        }
    }

    void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }

        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }

        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.shootPressed /*&& this.coolDown >=this.fireDelay*/) {
            ResourcesM.getSound("bullet.wav");
            this.coolDown = 0;
            this.charge += this.chargeRate;
            if (b == null) {
                b = new Bullet(this.setBulletStartX(), this.setBulletStartY(), angle, charge, ResourcesM.getImage("bullet"));
                //ammo.add(b);

            } else {
                b.setPosition(setBulletStartX(), setBulletStartY(), angle);
                b.setScalefactor(charge);


            }
        }
        this.coolDown += this.rateOfFile;

//        if(b!=null){
//            b.update();
//

//        }
        this.ammo.forEach(b -> b.update());

    }

    private int setBulletStartX() {
        float cx = 29f * (float) Math.cos(Math.toRadians(angle));
        return (int) x + this.img.getWidth() / 2 + (int) cx - 4;
    }
public int getBulletStartX(){
        return setBulletStartX();
}
    private int setBulletStartY() {
        float cy = 29f * (float) Math.cos(Math.toRadians(angle));
        return (int) y + this.img.getWidth() / 2 + (int) cy - 4;
    }
    public int getBulletStartY(){
        return setBulletStartY();
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    public void moveBackwards() {
        vx = Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        this.hitBox.setLocation((int)x, (int)y);
    }

    private void moveForwards() {
        vx = Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation((int)x, (int)y);
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
        Graphics2D g2d = (Graphics2D) g;
        if (b != null) b.drawImage(g2d);
        this.ammo.forEach(b -> b.drawImage(g2d));
        g2d.drawImage(this.img, rotation, null);
        g2d.setColor(Color.RED);
        //g2d.rotate(Math.toRadians(angle), bounds.x + bounds.width/2, bounds.y + bounds.height/2);
        g2d.drawRect((int) x, (int) y - 30, 100, 20);

        if(this.health >= 70) g2d.setColor(Color.GREEN);
        else if(this.health>=50) g2d.setColor(Color.YELLOW);
        else g2d.setColor(Color.red);
        g2d.fillRect((int) x, (int) y - 30, health, 20);
        for(int i =0; i<this.lives; ++i){
            g2d.drawOval((int)x+(i*20), (int)y+55, 15, 15);
            g2d.fillOval((int)x+(i*20), (int)y+55, 15, 15);
        }
    }
    public Rectangle getHitBox(){
        return  this.hitBox.getBounds();
    }
}
