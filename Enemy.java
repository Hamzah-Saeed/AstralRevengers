import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {

    private int enemyHp;
    private int enemyMaxHp;
    private int enemyStr;
    private int enemySpd;
    private int enemyType;
    private Image enem = null;

    private int x;
    private int y;
    private int width = 40;
    private int height = 70;
    private int endX;
    private int startX;
    private boolean movingRight = true;
    Main m;

    public Enemy(int startX, int endX, int y, Main m) {
        this.m = m;
        this.startX = startX;
        this.x = startX;
        int a = (int) (Math.random() * 3) + 1;
        if (a == 1) { // weak
            this.height = 70;
            this.width = 40;
            enemyHp = 50;
            enemyMaxHp = 50;
            enemyStr = 1;
            enemySpd = 3;
            final Toolkit tk = Toolkit.getDefaultToolkit();
            enem = tk.createImage("SmallEnemy.gif");
        } else if (a == 2) { // mid
            final Toolkit tk = Toolkit.getDefaultToolkit();
            enem = tk.createImage("ReaperPlant (2).gif");
            this.width = 60;
            this.height = 105;
            enemyHp = 100;
            enemyMaxHp = 100;
            enemyStr = 2;
            enemySpd = 2;
        } else { // strong
            final Toolkit tk = Toolkit.getDefaultToolkit();
            enem = tk.createImage("enemy.png");
            this.width = 80;
            this.height = 140;
            enemyHp = 300;
            enemyMaxHp = 300;
            enemyStr = 3;
            enemySpd = 1;
        }
        this.endX = endX-this.width;
        this.y = y-this.height;
        if(startX>endX){
            movingRight = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHP() {
        return enemyHp;
    }

    public int getDmg() {
        return enemyStr;
    }

    public void setHp(int a) {
        enemyHp -= a;
    }

    public void setY(int a) {
        this.y = a;
    }

    public void move() {
        if (startX > endX) {
            // Swap startX and endX if startX is greater than endX
            int temp = startX;
            startX = endX;
            endX = temp;
        }

        if (movingRight) {
            x += enemySpd;
            if (x >= endX) {
                movingRight = false;
            }
        } else {
            x -= enemySpd;
            if (x <= startX) {
                movingRight = true;
            }
        }
    }


    public void enemyDrop(Character c) {
        if (enemyStr == 1) {
            c.setCurrency(c.getCurrency() + 10);
        } else if (enemyStr == 2) {
            c.setCurrency(c.getCurrency() + 20);
        } else {
            c.setCurrency(c.getCurrency() + 30);
        }

        if((int) (Math.random() * 20) + 1 == 1){
            m.setHeals(new Heals(50, c.getHp(), 100, "HealPotion.png"));
        }else if((int) (Math.random() * 20) + 1 > 0){
            System.out.println("HAH");
            m.setHeals(new Heals(25, c.getHp(), 100, "GoldenApple.png"));
        }else if((int) (Math.random() * 20) + 1 == 1){
            m.setHeals(new Heals(10, c.getHp(), 100, "AlienSoda.png"));
        }

    }

    public void paint(Graphics g) {
        g.drawImage(enem, x, y, width, height, null);
    }
}