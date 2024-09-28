import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obstacles {

    private int x;
    private int y;
    private int height;
    private int width;
    private BufferedImage platform = null;

    public Obstacles(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            platform = ImageIO.read(new File("platform.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX(){
        return x;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getY(){
        return y;
    }

    public void collide(Character c) {
        Rectangle cr = new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight());
        Rectangle or = new Rectangle(this.x, this.y, this.width, this.height);

        if (cr.intersects(or)) {
            if (c.getY() + c.getHeight() <= or.getY() + 10 && c.getYa() > 0) {
                // Collision from above
                c.setY(or.y - c.getHeight());
                c.setRise(false);
                c.setYa(0);
            } else if (c.getY() >= or.getY() + or.getHeight() - 10 && c.getYa() < 0) {
                // Collision from below
                c.setY((int) (or.y + or.getHeight()));
                c.setYa(0);
            } else if (c.getX() + c.getWidth() <= or.getX() + 10 && c.getXa() > 0) {
                // Collision from the left
                c.setX(or.x - c.getWidth());
                c.setXa(0);
                c.setRight(false);
            } else if (c.getX() >= or.getX() + or.getWidth() - 10 && c.getXa() < 0) {
                // Collision from the right
                c.setX((int) (or.x + or.getWidth()));
                c.setXa(0);
                c.setLeft(false);
            }
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
    }
}
