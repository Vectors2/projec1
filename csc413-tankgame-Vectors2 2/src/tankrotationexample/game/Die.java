package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Die  {
    protected float x, y;

    protected BufferedImage img;
    protected Rectangle hitBox;

    public Die(float y, float x, BufferedImage img) {

        this.y = y;
        this.x = x;
        this.img = img;
        this.hitBox=new Rectangle((int)x,(int)y,this.img.getWidth(),this.img.getHeight());
    }


    public void drawDie(Graphics2D buffer) {
        buffer.drawImage(img, (int)x, (int)y, null);

    }


    public Rectangle getHitBox() {
        {
            return  this.hitBox.getBounds();
        }
    }
}


