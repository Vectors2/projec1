package tankrotationexample.game;

import tankrotationexample.ResourcesM;

import java.awt.*;
import java.awt.image.BufferedImage;

public class floor {
    protected float y,x;
    protected BufferedImage img;


    public floor(float y, float x, BufferedImage img) {
        this.y = y;
        this.x = x;
        this.img = img;

    }
    public void drawfloor(Graphics2D buffer) {
        buffer.drawImage(img, (int)x, (int)y, null);
    }
}
