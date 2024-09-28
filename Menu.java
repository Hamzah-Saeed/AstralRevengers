import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO; 

public class Menu 
{
	// Fields
	private String title; // Title of the menu (how to play, settings)
	private String[] options; // Array containing options for the user to pick
	private int currentOption; // Option user currently selects
	
	//Try coding the graphics of the main menu body before figuring out the options stuff
	
	/**
	 * Need to implement mouse clicking on a button causing something to happen (if mouse clicks this button this happens)
	 * How does the program know what to do when a button is clicked?
	 * Each button has their own function that happens after clicking the button
	 */
	
	// Constructor
	public Menu(String t, String[] o, int cO)
	{
		title = t;
		options = o;
		currentOption = cO;
	}
	
	// Other Methods
	
	/**
	 * Notes: 
	 * the graphics will include buttons for each option
	 * however many options there are, that number of buttons will display. 
	 * types of menus: 
	 * 		- Pause (title: Pause; options: Resume, Exit, Restart, How to Play?; happens during gameplay if the player 
	 * 				 wishes to pause the game)
	 * 		- Starting game (title:_____, options: How to Play, Settings, Start Game; happens when the player opens the
	 * 				 game and sees the title screen)
	 * 		-  
	 * need to add text into the graphics somehow so you can display the name of the title and options themselves (drawText). 
	 * Button is an imported image
	 */
	
	//displayMenu --> displays menu + options + graphics related to menu (like a paint method) 
	public void displayMenu(Graphics g)
	{
//		Graphics2D g2d = (Graphics2D) g;
//		super.paint(g);
		BufferedImage img = null;
		try
		{
			 img = ImageIO.read(new File("/SpaceGame/src/menu (2).png"));
			} catch (IOException e) {
		}
		//boolean Graphics.drawImage(Image img,int x, int y, ImageObserver observer); 
		int imgX = 100;
		int imgY = 30;
		
		//public abstract void drawString(String str, int x, int y)
		//Find a way to import a spacey font for the text
		g.setColor(Color.cyan);
		Font myFont = new Font ("Courier New", 1, 80);
		g.setFont(myFont);
		g.drawString(title, imgX + 30, imgY + 20);
		
		int textX = imgX + 70;
		int textY = imgY + 110;
		myFont = new Font ("Courier New", 1, 40);
		/**
		 * Need to somehow find the midpoint of the text so that you can position the text right in the middle of the menu box. 
		 * This is to maintain the central alignment of the text with the box.
		 * Save the midpoint into an integer variable
		 */
		g.setFont(myFont);
		for(int i = 0; i < options.length; i++)
		{
			g.drawImage(img, imgX, imgY + (150 * i), 300, 200, null);
			g.drawString(options[i], textX, textY + (150 * i));

		}
		
	}
}
