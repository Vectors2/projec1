/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.game;


import tankrotationexample.GameConstants;
import tankrotationexample.Launcher;
import tankrotationexample.ResourcesM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author anthony-pc
 */
public class GameWorld extends JPanel implements Runnable {

    float charge = 1f;
    float chargeRate = 0.05f;
    List<Wall> walls = new ArrayList<>();
    List<PowerUp> powerUp = new ArrayList<>();
    List<WinorDie> WinOrDie = new ArrayList<>();
    List<tankrotationexample.game.Die> Die = new ArrayList<tankrotationexample.game.Die>();
    List<floor> floor = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();

    private BufferedImage world;
    private float angle;
    private Tank t1;
    private Tank t2;
    //private Bullet b1;
    private Breakable WallB;
    private Launcher lf;
    private long tick = 0;


    /**
     * @param lf
     */
    public GameWorld(Launcher lf) {
        this.lf = lf;
    }

    @Override
    public void run() {
        try {
            this.resetGame();
            while (true) {
                this.tick++;
                this.t1.update(); // update tank
                this.t2.update(); //update tank


                for (int i = 0; i < walls.size(); ++i) {
                    Wall w = this.walls.get(i);
                    if (w.getHitBox().intersects(this.t2.getHitBox())) {
                        t2.moveBackwards();

//                        if(other instanceof Bullet){
//
//                        }else{
//
//                        }
                    }
                    if (w.getHitBox().intersects(this.t1.getHitBox())) {
                        t1.moveBackwards();
                    }

                }
//                int j = 0;
//                while( j < walls.size()) {
//                    Wall w = this.walls.get(j);
//                    if (w.getHitBox().intersects(this.t1.getHitBox())) {
//                        System.out.println("t1 has hit a wall");
//                    }
//                    ++j;
//                }

                for (int i = 0; i < this.powerUp.size(); ++i) {

                    HeathPowerUp w = (HeathPowerUp) this.powerUp.get(i);
                    if (w.getHitBox().intersects(this.t1.getHitBox())) {
                        if (t1.health < 100)
                            t1.health = 100;
                        powerUp.remove(i);
                        System.out.println(t1.health);

                    }

                }
                for (int i = 0; i < this.powerUp.size(); ++i) {
                    HeathPowerUp w = (HeathPowerUp) this.powerUp.get(i);
                    if (w.getHitBox().intersects(this.t2.getHitBox())) {
                        if (t2.health < 100)
                            t2.health = 100;
                        powerUp.remove(i);
                    }

                }
                for (int i = 0; i < this.Die.size(); ++i) {
                    Die w = this.Die.get(i);
                    if (w.getHitBox().intersects(this.t2.getHitBox())) {

                        t2.health--;
                        t2.lives--;
                        Die.remove(i);
                        if (t2.lives == 0) {
                            t2.health = 0;
                            full2();
                        }
                    }
                }
                for (int i = 0; i < this.Die.size(); ++i) {
                    Die w = this.Die.get(i);
                    if (w.getHitBox().intersects(this.t1.getHitBox())) {

                        t1.health--;
                        t1.lives--;
                        Die.remove(i);
                        if (t1.lives == 0) {
                            t1.health = 0;
                            full1();
                        }

                    }
                }

                for (int i = 0; i < walls.size(); ++i) {
                    Wall w = this.walls.get(i);
                    Bullet b = new Bullet(this.setBulletStartX(), this.setBulletStartY(), angle, charge, ResourcesM.getImage("bullet"));

                    if (w.getHitBox().intersects(b.getHitBox().getBounds())) {
                        System.out.println("walls is hit by bullet");
                        walls.remove(i);


                    }


                }


                this.repaint();   // redraw game

                /*
                 * Sleep for 1000/144 ms (~6.9ms). This is done to have our
                 * loop run at a fixed rate per/sec.
                 */
                Thread.sleep(1000 / 144);

                /*
                 * simulate an end game event
                 * we will do this with by ending the game when ~8 seconds has passed.
                 * This will need to be changed since the will always close after 8 seconds
                 */
//                if (this.tick >= 144 * 8) {
//                    this.lf.setFrame("end");
//                    return;
//                }

            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    private float setBulletStartY() {
        return t1.getBulletStartY();
    }

    private float setBulletStartX() {
        return t1.getBulletStartX();
    }


    /**
     * Reset game to its initial state.
     */
    public void resetGame() {
        this.tick = 0;
        this.t1.setX(450);
        this.t1.setY(620);
    }

    public void t1Position() {
        this.t1.setX(450);
        this.t1.setY(620);
    }

    public void t2Position() {
        this.t2.setX(670);
        this.t2.setY(650);

    }

    public void full1() {
        t1.health = 100;
        t1.lives = 3;
        t1Position();
    }

    public void full2() {
        t2.health = 100;
        t2.lives = 3;
        t2Position();
    }


    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void InitializeGame() {
        ResourcesM.InitImages();
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH,
                GameConstants.WORLD_HEIGHT,
                BufferedImage.TYPE_INT_RGB);


//    try {
//            /*
//             * note class loaders read files from the out folder (build folder in Netbeans) and not the
//             * current working directory. When running a jar, class loaders will read from withing the jar.
//             */
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }

        t1 = new Tank(0, 0, 0, 50, (short) 270, ResourcesM.getImage("tank1"));
        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        t2 = new Tank(670, 650, 50, 50, (short) 270, ResourcesM.getImage("tank2"));
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        this.lf.getJf().addKeyListener(tc1);
        this.lf.getJf().addKeyListener(tc2);

        try (BufferedReader mapReader = new BufferedReader(new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("map.csv")))) {
            //  String[] size = mapReader.readLine().split(",");
//            int numberOfRows = Integer.parseInt(size[0]);
//            int numberOfColumns = Integer.parseInt(size[1]);
            for (int i = 0; mapReader.ready(); i++) {

                String[] items = mapReader.readLine().split(",");

                for (int j = 0; j < items.length; j++) {
                    switch (items[j]) {
                        case "0" -> {
                            floor f = new floor(i * 30, j * 30, ResourcesM.getImage("floor"));
                            floor.add(f);
                        }
                        case "9", "1" -> {
                            Wall w = new Wall(i * 30, j * 30, ResourcesM.getImage("unbreakable"));
                            walls.add(w);
                        }
                        case "2" -> {
                            Breakable bw = new Breakable(i * 30, j * 30, ResourcesM.getImage("breakable"));
                            walls.add(bw);
                        }
                        case "3" -> {
                            HeathPowerUp hp = new HeathPowerUp(i * 30, j * 30, ResourcesM.getImage("Health"));
                            this.powerUp.add(hp);
                        }
                        case "4" -> {
                            WinorDie c = new WinorDie(i * 30, j * 30, ResourcesM.getImage("Win"));
                            WinOrDie.add(c);
                        }
                        case "5" -> {
                            Die d = new Die(i * 30, j * 30, ResourcesM.getImage("Die"));
                            Die.add(d);
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-2);
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);

        buffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        this.floor.forEach(f -> f.drawfloor(buffer));
        this.walls.forEach(w -> w.drawImage(buffer));
        this.powerUp.forEach(p -> p.drawIt(buffer));
        this.WinOrDie.forEach(c -> c.drawWin(buffer));
        this.Die.forEach(c -> c.drawDie(buffer));
        this.t1.drawImage(buffer);

        this.t2.drawImage(buffer);

        g2.drawImage(world, 0, 0, null);

        BufferedImage mm = world.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT); //SPLIT SCREEN
        g2.scale(.2, .2);
        g2.drawImage(mm, 150, 2800, null);
    }


}



