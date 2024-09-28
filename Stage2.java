package spaceGame2;
// Gavi

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Stage2 extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		BufferedImage img = null;
		try
		{
			 img = ImageIO.read(new File("airplane-png-10.png"));
			} catch (IOException e) {
		}
		
		//boolean Graphics.drawImage(Image img, int x, int y, ImageObserver observer)
		g.drawImage(img, 55, 415, 150, 150, null);
	}
	
	public static void main (String[] args)
	{
		JFrame frame = new JFrame("Stage 2");
		frame.add(new Stage2());
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
