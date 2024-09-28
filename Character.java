import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Character {

    private String weaponType;
    private int bal;
    private int spd;
    private int hp = 100;
    private int maxHp;
    private int x = 0;
    private int y = 800;
    private int xa = 0;
    private int ya = 0;
    private int width;
    private int height;
    private boolean right = false, left = false;
    private boolean up = false, down = false;
    public boolean space = false;
    private Main m;
    private boolean rise = false;
    private int shotCounter;
    private Image cImg = null;
    private boolean canMoveX = true;
    private boolean canMoveY = true;
    private boolean isTouchingFloor = true;
    private boolean lastLookR = true;
    private int currency;
    private String s = "a";
    private boolean one;
    private boolean two;
    private boolean three;


    public Character(Main m) {
        this.m = m;
        this.height = 100;
        this.width = 60;

        try {
            cImg = ImageIO.read(new File("standStill.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        final Toolkit tk = Toolkit.getDefaultToolkit();
        if (e.getKeyCode() == KeyEvent. ) {
            left = true;
            if (!s.equals("Running Animation" + weaponType + "Flipped.gif")) {
                s = "Running Animation" + weaponType + "Flipped.gif";
                this.cImg = tk.createImage(s);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
            if (!s.equals("Running Animation" + weaponType + "Flipped.gif")) {
                s = "Running Animation" + weaponType + "Flipped.gif";
                this.cImg = tk.createImage(s);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
            xa = 0;
            try {
                cImg = ImageIO.read(new File("standStill.png"));
            } catch (IOException ev) {
                ev.printStackTrace();
            }
            s = "b";
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
            xa = 0;
            try {
                cImg = ImageIO.read(new File("standStill.png"));
            } catch (IOException ev) {
                ev.printStackTrace();
            }
            s = "b";
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
    }


    public void setWeaponType(String s) {
        weaponType = s;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCurrency(int a) {
        currency = a;
    }

    public int getCurrency() {
        return currency;
    }

    public int getWidth() {
        return this.width;
    }

    public void setImg(String s) {
        final Toolkit tk = Toolkit.getDefaultToolkit();
        this.cImg = tk.createImage(s);
    }

    public void setY(int a){
        y = a;
    }

    public void setYa(int a){
        ya = a;
    }

    public int getXa(){
        return xa;
    }

    public boolean getRight() {
        return this.right;
    }

    public void setRight(boolean a) {
        this.right = a;
    }

    public void setLeft(boolean a) {
        this.left = a;
    }

    public int getHeight() {
        return this.height;
    }

    public int getShots() {
        return this.shotCounter;
    }

    public void setShots(int a) {
        this.shotCounter += a;
    }

    public int getYa() {
        return this.ya;
    }

    public int getHp() {
        return this.hp;
    }

    public void setRise(boolean a) {
        this.rise = a;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setXa(int x) {
        this.xa = x;
    }

    public void canMoveX(boolean a) {
        this.canMoveX = a;
    }

    public void setIsTouchingFloor(boolean a) {
        this.isTouchingFloor = a;
    }

    public boolean getLastLook() {
        return lastLookR;
    }

    public void move(Weapon w, ArrayList<Obstacles> oL) {
        if (right && canMoveX) {
            lastLookR = true;
            xa = 4;
            if (x > m.getWidth()) {
                xa = 0;
            }
        }
        if (left && canMoveX) {
            lastLookR = false;
            xa = -4;
            if (x - 2 < 0) {
                xa = 0;
            }
        }

        if (up && isStanding(oL)) {
            rise = true;
            ya = -20;
        }

        try {
            if (!isStanding(oL) || rise) {
                ya += 1;
            } else {
                ya = 0;
                rise = false;
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        x += xa;
        y += ya;

        Iterator<Obstacles> iterator = oL.iterator();
        try {
            for (int i = 0; i<oL.size(); i++) {
                Rectangle cr = new Rectangle(this.x, this.y, this.width, this.height);
                Rectangle or = new Rectangle(oL.get(i).getX(), oL.get(i).getY(), oL.get(i).getWidth(), oL.get(i).getHeight());
                if (cr.intersects(or) && ya > 0) {
                    y = oL.get(i).getY() - this.height;
                    ya = 0;
                    rise = false;
                }
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

    }




    public void takeDamage(Enemy e) {
        Rectangle er = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        Rectangle cr = new Rectangle(this.x, this.y, this.width, this.height);
        if (er.intersects(cr)) {
            hp -= 10 * e.getDmg();
        }
        if (hp <= 0) {
            hp = 0;
        }
    }

    public boolean isMoving() {
        return right || left;
    }

    public boolean isStanding(ArrayList<Obstacles> oL) {
        Rectangle cr = new Rectangle(this.x, this.y + this.height, this.width, 1);
        try {
            for (int i = 0; i<oL.size(); i++) {
                Rectangle or = new Rectangle(oL.get(i).getX(), oL.get(i).getY(), oL.get(i).getWidth(), oL.get(i).getHeight());
                if (cr.intersects(or)) {
                    return true;
                }
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
        return false;
    }


    public void paint(Graphics g) {
        if (lastLookR) {
            g.drawImage(cImg, x, y, width, height, null);
            g.setColor(Color.blue);
        } else {
            g.drawImage(cImg, x + width, y, -width, height, null);
            g.setColor(Color.blue);
        }
        g.setColor(Color.DARK_GRAY);
        g.fillRect(18, 18, 204, 34);
        g.setColor(Color.RED);
        g.fillRect(20, 20, hp * 2, 30);
        g.setColor(Color.lightGray);
        g.fillRect(1200, 18, 180, 34);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("$ " + currency, 1200, 48);
    }
}
