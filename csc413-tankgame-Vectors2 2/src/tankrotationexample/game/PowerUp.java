package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUp {
    protected float x, y;
    protected Rectangle hitBox;

BufferedImage img;

    public PowerUp(float y, float x, BufferedImage img) {
        this.y = y;
        this.x = x;
        this.img = img;
        this.hitBox = new Rectangle((int)x,(int)y,this.img.getWidth(),this.img.getHeight());
    }

    public void drawIt(Graphics2D buffer) {
        buffer.drawImage(img, (int)x, (int)y, null);
    }



}
