package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HeathPowerUp extends PowerUp{
    protected Rectangle hitBox;

    public HeathPowerUp(float y, float x, BufferedImage img) {
        super(y, x, img);
        this.hitBox = new Rectangle((int)x,(int)y,this.img.getWidth(),this.img.getHeight());
    }

    public Rectangle getHitBox(){
        return  this.hitBox.getBounds();
    }
}
