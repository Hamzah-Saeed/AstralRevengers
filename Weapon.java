import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Weapon{

    private int bullets;
    private int maxBullets;
    private int reloadSpeed;
    private String weaponType;
    private int damageFallOff;
    private boolean space = false;
    int x;
    int y;
    int height;
    int width;
    private String s;
    private BufferedImage img = null;

    public Weapon(int a, Character c){
        if(a==1){ //light weapon
            this.bullets = 30;
            this.maxBullets = 30;
            this.reloadSpeed = 1;
            this.weaponType = "light";
            s = "LightGun.png";
            this.damageFallOff = 50; //50m
            this. height = 25;
            this. width = 30;
        }else if(a==2){ //med weapon
            this.bullets = 15;
            this.maxBullets = 15;
            this.reloadSpeed = 3;
            this.weaponType = "medium";
            s = "MediumGun.png";
            this.damageFallOff = 100; //100m
            this. height = 15;
            this. width = 25;
        }else if(a==3) { //heavy weapon
            this.bullets = 5;
            this.maxBullets = 5;
            this.reloadSpeed = 5;
            this.weaponType = "heavy";
            s = "HeavyGun.png";
            this.damageFallOff = 200; //200m
            this. height = 20;
            this. width = 30;
        }
        final Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            img = ImageIO.read(new File(s));
            img = rotate(img);
        }catch(IOException E){
        }
    }



    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }

    public int getBullets(){
        return this.bullets;
    }

    public int getMaxBullets(){
        return this.maxBullets;
    }

    public void setBullets(int a){
        this.bullets = a;
    }

    public void move(Character c){
        this.x=c.getX()+c.getWidth()-5;
        this.y=c.getY()+40;
    }

    public boolean shooting(){
        return bullets!=0 && space;
    }

    public boolean shoot(Character c){
        return c.getShots()>0;
    }
    public String getType(){
        return this.weaponType;
    }

    public static BufferedImage rotate(BufferedImage img)
    {
        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.rotate(Math.toRadians(20), img.getWidth()/2, img.getHeight()/2);
        g2.drawImage(img, null, 0, 0);
        return newImage;
    }


    public void reload(){
        setBullets(getMaxBullets());
    }

    public void paint(Graphics g, Character c) {
        if (!c.isMoving()) {
            if (c.getLastLook()) {
                g.drawImage(img, x, y, width, height, null);
            } else {
                g.drawImage(img, x - 40, y, width * -1, height, null);
            }

        }
    }

}
