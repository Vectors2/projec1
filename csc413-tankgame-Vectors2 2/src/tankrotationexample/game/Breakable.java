package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Breakable extends Wall  {
    protected int life = 2;

    public Breakable(float y, float x, BufferedImage img) {
        super(y, x, img);
    }
    public Rectangle getHitBox(){
        return  this.hitBox.getBounds();
    }

}

