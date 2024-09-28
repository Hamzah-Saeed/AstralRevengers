import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class Levels{

    int zone;
    int level;
    int enemies;
    int xEn;
    int x;
    int y;
    int height;
    int width;
    boolean space;
    Main m;
    boolean drawShop = false;

    public Levels(int a, int b, Main m){
        this.m = m;
        zone = b;
        level = a;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = !space;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

        }
    }

    public int getEnemies(){
        return enemies;
    }

    public ArrayList<Enemy> createEnemy(Character c) {
        ArrayList<Enemy> eL = new ArrayList<Enemy>();
        if(level == 1) {
            if (zone == 1) {
                Enemy e = new Enemy(800, 200, 900, m);
                eL.add(e);
            } else if (zone == 2) {
                eL.add(new Enemy(610, 800, 900, m));
                eL.add(new Enemy(800, 1100, 900, m));
            } else if (zone == 3) {

            } else if (zone == 4) {
                eL.add(new Enemy(657, 0, 900, m));
                eL.add(new Enemy(967, 0, 900, m));
                eL.add(new Enemy(456, 0, 900, m));
                eL.add(new Enemy(800, 0, 900, m));
                eL.add(new Enemy(256, 0, 900, m));
                eL.add(new Enemy(654, 0, 900, m));
                eL.add(new Enemy(432, 0, 900, m));
                eL.add(new Enemy(800, 0, 900, m));
            } else if (zone == 5) {

            }
        } else if(level == 2) {
            if (zone == 1) {
                System.out.println("HERE");
                Enemy e = new Enemy(300, 600, 800, m);
                eL.add(new Enemy(600, 1400, 900, m));
                eL.add(e);
            } else if (zone == 2) {
                eL.add(new Enemy(570, 870, 750, m));
                eL.add(new Enemy(1000, 1400, 900, m));
            } else if (zone == 3) {
                eL.add(new Enemy(550, 650, 300, m));
                eL.add(new Enemy(450, 500, 600, m));
            } else if (zone == 4) {
                eL.add(new Enemy(550, 750, 600, m));
                eL.add(new Enemy(250, 450, 780, m));
            } else if (zone == 5) {

            }
        } else if(level == 3) {
            if (zone == 1) {
                eL.add(new Enemy(600, 600, 900, m));
                eL.add(new Enemy(1000, 700, 900, m));
            } else if (zone == 2) {
                eL.add(new Enemy(100, 450, 800, m));
                eL.add(new Enemy(650, 900, 700, m));
            } else if (zone == 3) {
                eL.add(new Enemy(400, 600, 850, m));
                eL.add(new Enemy(850, 1100, 650, m));
            } else if (zone == 4) {
            } else if (zone == 5) {

            }
        }

        return eL;
    }

    public void drawZone(Graphics g){
        g.setColor(Color.darkGray);
        if(level == 1) {
            if (zone == 1) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
                m.addObstacle(new Obstacles(500, 750, 100, 50));
            } else if (zone == 2) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
                m.addObstacle(new Obstacles(600, 500, 10, 600));
                m.addObstacle(new Obstacles(100, 750, 50, 10));
                m.addObstacle(new Obstacles(250, 600, 50, 10));
                m.addObstacle(new Obstacles(400, 600, 50, 10));
            } else if (zone == 3) {
                m.addObstacle(new Obstacles(0, 900, 500, 100));
                m.addObstacle(new Obstacles(700, 900, 500, 100));
            } else if (zone == 4) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
                m.addObstacle(new Obstacles(100, 750, 50, 10));
            } else if (zone == 5) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
            }
        }

        if(level == 2){
            if (zone == 1) {
                m.addObstacle(new Obstacles(0, 900, 300, 100));
                m.addObstacle(new Obstacles(300, 800, 300, 20));
                m.addObstacle(new Obstacles(600, 900, 800, 100));
            } else if (zone == 2) {
                m.addObstacle(new Obstacles(0, 900, 300, 100));
                m.addObstacle(new Obstacles(320, 800, 100, 20));
                m.addObstacle(new Obstacles(570, 750, 300, 20));
                m.addObstacle(new Obstacles(1000, 900, 1000, 100));
            } else if (zone == 3) {
                m.addObstacle(new Obstacles(0, 900, 350, 100));
                m.addObstacle(new Obstacles(550, 300, 100, 700));
                m.addObstacle(new Obstacles(350, 750, 50, 5));
                m.addObstacle(new Obstacles(450, 600, 50, 5));
                m.addObstacle(new Obstacles(350, 450, 50, 5));
                m.addObstacle(new Obstacles(450, 300, 50, 5));
                m.addObstacle(new Obstacles(700, 900, 500, 100));
            } else if (zone == 4) {
                m.addObstacle(new Obstacles(0, 900, 100, 100));
                m.addObstacle(new Obstacles(250, 780, 200, 20));
                m.addObstacle(new Obstacles(550, 600, 200, 20));
                m.addObstacle(new Obstacles(850, 520, 200, 20));
                m.addObstacle(new Obstacles(1050, 800, 400, 400));
            } else if (zone == 5) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
            }
        }

        if(level == 3){
            if (zone == 1) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
            } else if (zone == 2) {
                m.addObstacle(new Obstacles(0, 800, 500, 100));
                m.addObstacle(new Obstacles(600, 700, 800, 100));
            } else if (zone == 3) {
                m.addObstacle(new Obstacles(690, 750, 20, 10));
                m.addObstacle(new Obstacles(0, 850, 700, 100));
                m.addObstacle(new Obstacles(800, 650, 600, 100));
            } else if (zone == 4) {
                m.addObstacle(new Obstacles(0, 850, 300, 50));
                m.addObstacle(new Obstacles(350, 700, 50, 200));
                m.addObstacle(new Obstacles(450, 600, 200, 50));
                m.addObstacle(new Obstacles(700, 500, 50, 150));
                m.addObstacle(new Obstacles(800, 400, 300, 50));
                m.addObstacle(new Obstacles(1150, 600, 50, 300));
                m.addObstacle(new Obstacles(600, 300, 200, 50));
            } else if (zone == 5) {
                m.addObstacle(new Obstacles(0, 900, 1400, 100));
            }
        }
    }
}
