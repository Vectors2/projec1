package tankrotationexample;

import tankrotationexample.game.GameWorld;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import static javax.imageio.ImageIO.read;

public class ResourcesM {
    private static Map<String, BufferedImage> Images = new HashMap<>();
    private static Map<String, Clip> sounds = new HashMap<>();
    private static Map<String, List<BufferedImage>> animations = new HashMap();

    public static BufferedImage getImage(String key) {
        return ResourcesM.Images.get(key);
    }

    public static Clip getSound(String key) {
        return ResourcesM.sounds.get(key);
    }

    public static List<BufferedImage> getAnimation(String key) {
        return ResourcesM.animations.get(key);
    }

    public static void InitImages() {
        try {
            ResourcesM.Images.put("tank1", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("tank1.png"),
                    "Could not find tank1.png")));
            ResourcesM.Images.put("tank2", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("tank2.png"),
                    "Could not find tank2.png")));
            ResourcesM.Images.put("floor", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Background.bmp"),
                    "Could not find Background.bmp")));
            ResourcesM.Images.put("breakable", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Wall1.gif"),
                    "Could not find Wall1.gif")));
            ResourcesM.Images.put("unbreakable", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Wall2.gif"),
                    "Could not find Wall2.gif")));
            ResourcesM.Images.put("bullet", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("bulletLeft.png"),
                    "Could not find bulletLeft.png")));
            ResourcesM.Images.put("Rocket", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Pickup.gif"),
                    "Could not find Pickup.gif")));
            ResourcesM.Images.put("Health", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("health.png"),
                    "Could not find Health.png")));
            ResourcesM.Images.put("Win", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Pickup.gif"),
                    "Could not find Pickup.gif")));
            ResourcesM.Images.put("Die", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("die.png"),
                    "Could not find Die.png")));
            //bush = ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Wall1.gif"),
            //"Could not find Wall1.gif"));
            ResourcesM.Images.put("menuBackground", ImageIO.read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("title.png"),
                    "Could not find title.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void InitAnimations() {
        //String baseName = "bullet_08_%04d.png";
//    List<BufferedImage> temp = new ArrayList<>();
//        String base = " /animations/bullet";
//        File dirs = new File(base);
//        Arrays.asList(dirs.list()).forEach(d ->{
//            Arrays.asList(new File(base+"/"+d)).forEach(anim ->{
//                System.out.println(anim);
//            });
//        });
//
//        ResourcesM.animations.put("bullet", temp);

//   try{
//          String baseName = "bullet_08_%04d.png";
//          List<BufferedImage> temp = new ArrayList<>();
//        for(int i=0; i<32; ++i){
//            String fName = String.format(baseName,i);
//            String fullPath = "animations/bullet/" + fName;
//            System.out.println(fullPath);
//            temp.add(read(GameWorld.class.getClassLoader().getResource(fullPath)));
//
//        }
//        ResourcesM.animations.put("bullet", temp);
//        baseName = "bullet_08_%04d.png";
//        temp = new ArrayList<>();
//        for(int i =0; i<24; i++) {
//            String fName = String.format(baseName,i);
//            String fullPath = "/animations/nuke/" + fName;
//            System.out.println(fullPath);
//            temp.add(read(GameWorld.class.getClassLoader().getResource(fullPath)));
//        }
//        ResourcesM.animations.put("nuke", temp);
//   } catch(IOException | NullPointerException e){
//        System.out.println(e);
//        System.out.println(-2);
//   }

    }

    public static void InitSounds(){
        try{
            AudioInputStream as;
            Clip clip;

            as = AudioSystem.getAudioInputStream(ResourcesM.class.getClassLoader().getResource("bullet.wav"));
            clip = AudioSystem.getClip();
            clip.open(as);
            ResourcesM.sounds.put("bullet",clip);

//            as = AudioSystem.getAudioInputStream(ResourcesM.class.getClassLoader().getResource("bullet.wav"));
//            clip = AudioSystem.getClip();
//            clip.open(as);
//            ResourcesM.sounds.put("Health",clip);


        }catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){
            System.err.println(e);
            e.printStackTrace();
            System.exit(-2);
        }


    }

    public static void main(String[] args) {
        //ResourcesM.InitImages();
        ResourcesM.InitSounds();
    }


}
