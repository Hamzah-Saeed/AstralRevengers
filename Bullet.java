

import org.w3c.dom.css.Rect;

import java.awt.*;

public class Bullet {

    int x;
    int y;
    int height;
    int width;
    boolean dRight;
    int initialLaunch;

    public Bullet(Weapon w, boolean b) {
        this.initialLaunch = w.getX();
        this.dRight = b;
        this.x = w.getX();
        this.y = w.getY()+2;
        this.width = 30;
        this.height = w.getHeight()/3-4;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    public int getWidth(){
        return width;
    }
    public boolean hit(Weapon w, Enemy e){
        Rectangle en = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        Rectangle b = new Rectangle(this.x, this.y, this.width, this.height);

        if(en.contains(b)){
            if(w.getType().equals("light")){
                e.setHp(50);
            }else if(w.getType().equals("medium")){
                e.setHp(100);
            }else{
                System.out.println("BOOM");
                e.setHp(300);
            }
            return true;
        }
        return false;
    }

    public void move(){
        if(dRight){
            this.x+=5;
        }else{
            this.x-=5;
        }
    }

    public void reachEnd(){
//        if((this.initialLaunch>initialLaunch-50))
    }

    public boolean collide(Obstacles ob){
        Rectangle b = new Rectangle(x, y, width, height);
        Rectangle o = new Rectangle(ob.getX(), ob.getY(), ob.getWidth(), ob.getHeight());
        if(o.intersects(b)){
            return true;
        }
        return false;
    }

    public void paint(Graphics g){
        g.setColor(Color.CYAN);
        if(this.dRight) {
            g.fillRect(x, y, width, height);
        }else{g.fillRect(x-30, y, width, height);}
    }

}
