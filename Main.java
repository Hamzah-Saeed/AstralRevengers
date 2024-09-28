import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class Main extends JPanel{

    private BufferedImage cobble = null;
    private Character c = new Character(this);
    private Weapon w = new Weapon(1, c);
    private int zone = 1;
    private int level = 1;
    private int prevLevel = -0;
    private ArrayList<Enemy> eList = new ArrayList<Enemy>();
    private ArrayList<Bullet> b = new ArrayList<Bullet>();
    private ArrayList<Obstacles> o = new ArrayList<Obstacles>();
    private boolean isReloading = false;
    long timeNow;
    long time;
    long timeOfLastProj = 0;
    long timeOfLastDmg = 0;
    private Heals[] heals = new Heals[3];
    private static boolean isPaused = true;
    private Levels l = new Levels(level, zone, this);
    private BufferedImage healSlot1;
    private BufferedImage healSlot2;
    private BufferedImage healSlot3;
    private boolean inShop = false;
    int mouseX;
    int mouseY;
    TitleScreen t = new TitleScreen(this);
    boolean win = false;

    public Main(){
        c.setWeaponType(w.getType());
        eList = l.createEnemy(c);
        System.out.println(level);
        if(level == 1) {
            try{
                cobble = ImageIO.read(new File("JungleBackground.jpg"));
            } catch (IOException e){
            }
        }else if(level == 2){
            try{
                cobble = ImageIO.read(new File("UnderwaterBackground.jpg"));
            } catch (IOException e){
            }
        }else{
            try{
                cobble = ImageIO.read(new File("SpaceBackground.jpg"));
            } catch (IOException e){
            }
        }


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                c.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_SPACE && !isReloading){
                    if(timeNow-timeOfLastProj<0 || timeNow-timeOfLastProj>200) {
                        c.setShots(1);
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                c.keyPressed(e);
                l.keyPressed(e);

            }
        }); //NOTE THE SEMI-COLON!!!!
        //Making sure our JPanel has the focus and therefor it is the
        //instance that will receive the keyboard input
        setFocusable(true);

    }

    public void setIsPaused(boolean a){
        isPaused = a;
    }
    public void setHeals(Heals h){
        for(int i = 0; i<heals.length; i++){
            if(heals[i]==null){
                heals[i] = h;

                i = 1000;
                System.out.println("RAHHH");
            }
        }
    }

    private void checkL(){
        if(c.getX()+c.getWidth()>1400){
            zone+=1;
            b.clear();
            if(zone == 6 && level!=3){
                level++;
                setBackground();
                zone = 1;
                prevLevel = -1;
            }
            c.setX(0);
            l = new Levels(level, zone, this);
            o = new ArrayList<Obstacles>();
        }
    }

    public void setZone(int a){
        zone = a;
    }
    public void setLevel(int a){
        level = a;
    }
    private void move(){
        if(level == 3 && zone == 6){
            win = true;
        }
        checkL();
        c.move(w, o);
        if(heals[2]==null){
            for(int i = 0; i<heals.length; i++){

            }
        }

        for(int i = 0; i<eList.size(); i++){
            eList.get(i).move();
            if(eList.get(i).getHP()<=0){
                eList.get(i).enemyDrop(c);
                eList.remove(i);
            }
        }
        for(int i = 0; i<o.size(); i++){
            o.get(i).collide(c);
        }
        w.move(c);
        for(int i = 0; i<b.size(); i++){
            b.get(i).move();
            for (int j = 0; j<eList.size(); j++) {;
                if (b.get(i).hit(w, eList.get(j))) {
                    b.remove(i);
                    j=eList.size();
                }
            }
        }
        if(prevLevel+2==zone){
            eList = new ArrayList<Enemy>();
            prevLevel+=1;
            eList = l.createEnemy(c);
        }

    }

    public void addObstacle(Obstacles ob){
        o.add(ob);
    }

    private void shoot(){
        checkL();
        timeNow = System.currentTimeMillis();
        time = timeNow - timeOfLastProj;
        if(time < 0 || time > 200) {
            if (c.getShots() > 0 && w.getBullets() > 0) {
                w.setBullets(w.getBullets() - 1);
                timeOfLastProj = timeNow;
                Bullet bul = new Bullet(w, c.getLastLook());
                b.add(bul);
                c.setShots(-1);
            }
        }
        timeNow = System.currentTimeMillis();
        long timer = timeNow - timeOfLastDmg;

        if(timer<0 || timer>1000 && !isReloading) {

            for (int i = 0; i < eList.size(); i++) {
                int cHpTemp = c.getHp();
                c.takeDamage(eList.get(i));
                if(c.getHp()<cHpTemp){
                    timeOfLastDmg = timeNow;
                }
            }
        }


        for(int i = 0; i<o.size(); i++){
            for(int j = 0; j<b.size(); j++){
                if (b.get(j).collide(o.get(i))){
                    b.remove(j);
                }
            }
        }
        if(w.getBullets()<=0){
            isReloading = true;
        }
        if(w.getBullets()<=0 && (time < 0 || time > 10000)){
            w.setBullets(w.getMaxBullets());
            isReloading = false;
        }
    }
    public void reset(){
        t = new TitleScreen(this);
    }
    public void setBackground(){
        if(level == 1) {
            try{
                cobble = ImageIO.read(new File("JungleBackground.jpg"));
            } catch (IOException e){
            }
        }else if(level == 2){
            try{
                cobble = ImageIO.read(new File("UnderwaterBackground.jpg"));
            } catch (IOException e){
            }
        }else{
            try{
                cobble = ImageIO.read(new File("SpaceBackground.jpg"));
            } catch (IOException e){
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (!isPaused) {
            g.drawImage(cobble, 0, 0, 1400, 1000, null);
            g.setColor(Color.gray);
            l.drawZone(g2d);
            c.paint(g2d);
            w.paint(g2d, c);

            for (int i = 0; i < b.size(); i++) {
                b.get(i).paint(g2d);
            }
            for (int i = 0; i < eList.size(); i++) {
                if (eList.get(i) != null) {
                    eList.get(i).paint(g2d);
                }
            }
            for (int i = 0; i < o.size(); i++) {
                o.get(i).paint(g2d);
            }

            for (int i = 0; i < heals.length; i++) {
                if (heals[i] != null) {
                    heals[i].paint(g, 245 + 40 * i, 21, 28, 28);
                }
                g.setColor(Color.lightGray);
                g.fillRect(242 + 40 * i, 18, 34, 34);

            }
        }else {
            t.paint(g2d);
        }
        if (c.getHp()<=0 || c.getY()>1400){
            g.setColor(Color.black);
            g.fillRect(0, 0, 2000, 2000);
            g.setColor(Color.red);
            Font myFont = new Font("Cyrillic", 1, 100);
            g.setFont(myFont);
            g.drawString("YOU DIED", 500, 500);
        }
        if(win){
            final Toolkit tk = Toolkit.getDefaultToolkit();
            g.setColor(Color.white);
            g.fillRect(0, 0, 2000, 2000);
            Image cImg = null;
            cImg = tk.createImage("dance.gif");
            g.drawImage(cImg, 100, 100, 100, 100, null);
            g.setColor(Color.red);
            Font myFont = new Font("Cyrillic", 1, 100);
            g.setFont(myFont);
            g.drawString("YOU WON", 500, 500);
        }
    }


    public void titleWork(){
        t.ifItemClicked(mouseX, mouseY, this);
    }

    public static void main(String args[]) throws InterruptedException {
        JFrame frame = new JFrame("gaem");
        Main p = new Main();
        frame.add(p);
        frame.setSize(1400, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true)
        {
            if(!isPaused) {
                p.move(); //Updates the coordinates
                p.repaint(); //Calls the paint method
                Thread.sleep(18); //Pauses for a moment
                p.shoot();
            }
            p.repaint();
            p.titleWork();
        }


    }

}
