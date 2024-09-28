import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Shop extends JPanel implements MouseListener {
	// Fields
	private Item h = new Item("Potion", "Heal", 3, 25);
	private Item a = new Item("Rock Candy", "Attack", 3, 15);
	private Item s = new Item("Alien Soda", "Speed", 3, 10);
	private Item d = new Item("Golden Apple", "Defense", 3, 20);
	private int userMoney;
	private boolean menuIsDisplayed = false;
	private Item selectedItem = null;
	private String errorMessage = null;
	private boolean needErrorMessage = false;
	private boolean enterShop = true;
	private BufferedImage shopBackground;

	// location and size for popUp
	int pX = 200;
	int pY = 130;
	int pW = 950;
	int pH = 650;

	// location and size for buyButton
	int bX = 300;
	int bY = 580;
	int bW = 300;
	int bH = 150;

	// location and size for xButton
	int xX = 950;
	int xY = 180;
	int xW = 100;
	int xH = 100;

	// location of first item and item image overall size + magic numbers that helped position the items
	int firstItemX = 330;
	int firstItemY = 280;
	int iW = 150;
	int iH = 150;
	int magicNumX = 380;
	int magicNumY = 350;

	//Constructor
	public Shop(int uM) {
		this.userMoney = uM;
		addMouseListener(this);
		try
		{
			shopBackground = ImageIO.read(new File("ShopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Other Methods

	//getItemImage -> returns the image of the selected item
	public BufferedImage getItemImage(Item i)
	{
		BufferedImage img = null;

		if (i.getName().equalsIgnoreCase("Potion"))
		{
			try
			{
				img = ImageIO.read(new File("HealPotion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if (i.getName().equalsIgnoreCase("Rock Candy"))
		{
			try
			{
				img = ImageIO.read(new File("RockCandy.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if (i.getName().equalsIgnoreCase("Alien Soda"))
		{
			try
			{
				img = ImageIO.read(new File("AlienSoda.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else
		{
			try
			{
				img = ImageIO.read(new File("GoldenApple.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return img;
	}

	//buy method
	public void buy(Item i, int money)
	{
		// Get item's type and price
		int price = i.getPrice();

		// decrease quantity of specific item if there is enough and if the user has enough money
		if(1 <= i.getQuantity() && money >= price)
		{
			i.setQuantity(i.getQuantity() - 1);
			userMoney -= price;
		}

	}

	//updateStock -> resets the quantity of each item to 3 (called after the user leaves the store)
	public void updateStock(Item h1, Item a1, Item s1, Item d1)
	{
		h1.setQuantity(3);
		a1.setQuantity(3);
		s1.setQuantity(3);
		d1.setQuantity(3);
	}

	public BufferedImage imgH = getItemImage(h);
	public BufferedImage imgA = getItemImage(a);
	public BufferedImage imgS = getItemImage(s);
	public BufferedImage imgD = getItemImage(d);
	//paint method
	public void paint(Graphics g)
	{
		//display the shop background

		g.drawImage(shopBackground, 0, 0, 1400, 1000, null);

		//saving and displaying the four items

		magicNumX = 380;
		magicNumY = 350;

		g.drawImage(imgH, firstItemX, firstItemY, iW, iH, null);
		g.drawImage(imgA, firstItemX + magicNumX, firstItemY, iW, iH, null);
		g.drawImage(imgS, firstItemX, firstItemY + magicNumY, iW, iH, null);
		g.drawImage(imgD, firstItemX + magicNumX, firstItemY + magicNumY, iW, iH, null);

		//Text displaying the quantity of each item
		g.setColor(Color.cyan);
		Font myFont = new Font ("Courier New", 1, 120);
		g.setFont(myFont);
		magicNumX = 110;
		magicNumY = 160;
		g.drawString("" + h.getQuantity(), firstItemX + magicNumX, firstItemY + magicNumY);
		g.drawString("" + a.getQuantity(), (firstItemX + 380) + magicNumX, firstItemY + magicNumY);
		g.drawString("" + s.getQuantity(), firstItemX + magicNumX, (firstItemY + 350) + magicNumY);
		g.drawString("" + d.getQuantity(), (firstItemX + 380) + magicNumX, (firstItemY + 350) + magicNumY);

		//Display for the user's balance
		//Text showing the user's balance
		myFont = new Font ("Courier New", 1, 45);
		g.setFont(myFont);
		g.drawString("Balance: " + userMoney, 990, 75);

		// Displays the shop menu if an item is selected and if the menu needs to be displayed
		if(menuIsDisplayed && selectedItem != null)
		{
			//displayShopMenu(g, selectedItem);
		}

		// Displays an error message if out of stock or if the user doesn't have enough money
		//Only works if there is an error message to display and if it is needed.
		if(errorMessage != null && needErrorMessage)
		{
			g.drawString(errorMessage, 100, 700);
		}

	}
/*
	//displayShopMenu -> secondary paint method for painting the menu box when an item is clicked
	public void displayShopMenu(Graphics g, Item i)
	{
		//Menu popup
		BufferedImage popUp = null;
		try
		{
			popUp = ImageIO.read(new File("Popup.png"));
		} catch (IOException e) {
		}

		g.drawImage(popUp, pX, pY, pW, pH, null);

		//buy Button
		BufferedImage buyButton = null;
		try
		{
			buyButton = ImageIO.read(new File("BUYButton.png"));
		} catch (IOException e) {
		}

		g.drawImage(buyButton, bX, bY, bW, bH, null);

		//x Button
		BufferedImage xButton = null;
		try
		{
			xButton = ImageIO.read(new File("X-Button.png"));
		} catch (IOException e) {
		}

		g.drawImage(xButton, xX, xY, xW, xH, null);

		//Display for item name, type, quantity, and price
		g.setColor(Color.CYAN);
		Font myFont = new Font ("Courier New", 1, 45);
		g.setFont(myFont);
		System.out.println("Selected Item Type: " + i.getType());
		g.drawString("Name: " + i.getName(), pX + pH - 230, pY + 200);
		g.drawString("Type: " + i.getType(), pX + pH - 200, pY + 300);
		g.drawString("Quantity: " + i.getQuantity(), pX + pH - 230, pY + 400);
		g.drawString("Price: " + i.getPrice(), pX + pH - 200, pY + 500);

//		Image for the corresponding image
		BufferedImage iImg = getItemImage(i);
		g.drawImage(iImg, pX + 50, pY + 100, 300, 300, null);

	}
*/
	//buttonClickCheck -> checks if the mouse x and y are within a button
	private boolean buttonClickCheck(int mouseX, int mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight)
	{
		boolean xRange = mouseX >= buttonX && mouseX <= buttonX + buttonWidth;
		boolean yRange = mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
		return xRange && yRange;
	}

	//imageClickCheck -> checks if the mouse x and y are within an item image
	private boolean imageClickCheck(int mouseX, int mouseY, int imageX, int imageY) {
		boolean xRange = mouseX >= imageX && mouseX <= imageX + iW;
		boolean yRange = mouseY >= imageY && mouseY <= imageY + iH;
		return xRange && yRange;
	}

	public void setC(int a){
		userMoney = a;
	}

	private void ifItemClicked(int mouseX, int mouseY)
	{
		magicNumX = 500;
		magicNumY = 300;

		if(imageClickCheck(mouseX, mouseY, firstItemX, firstItemY))
		{
			//Potion Check
			System.out.println("Chosen Item " + h.getName());
			menuIsDisplayed = true;
			selectedItem = h;
		}

		else if(imageClickCheck(mouseX, mouseY, firstItemX + magicNumX, firstItemY))
		{
			//Rock Candy Check
			System.out.println("Chosen Item " + a.getName());
			menuIsDisplayed = true;
			selectedItem = a;
		}

		else if(imageClickCheck(mouseX, mouseY, firstItemX, firstItemY + magicNumY))
		{
			// Alien Soda Check
			System.out.println("Chosen Item " + s.getName());
			menuIsDisplayed = true;
			selectedItem = s;
		}

		else if(imageClickCheck(mouseX, mouseY, firstItemX + magicNumX, firstItemY + magicNumY))
		{
			// Golden Apple Check
			System.out.println("Chosen Item " + d.getName());
			menuIsDisplayed = true;
			selectedItem = d;
		}
		repaint();
	}

	private void ifMenuClicked(int mouseX, int mouseY, Item sItem)
	{
		// buy button check
		if(buttonClickCheck(mouseX, mouseY, bX, bY, bW, bH))
		{
			System.out.println("User wants to buy " + sItem.getName());
			if(userMoney >= sItem.getPrice() && sItem.getQuantity() >= 1)
			{
				buy(sItem, userMoney);
				System.out.println("User buys " + sItem.getName());
				menuIsDisplayed = false;
			}

			else
			{
				needErrorMessage = true;
				if(userMoney < sItem.getPrice())
				{

					errorMessage = "You don't have enough money!";
				}

				else
				{
					errorMessage = "Out of stock!";
				}
			}
		}

		// X button check
		else if(buttonClickCheck(mouseX, mouseY, xX, xY, xW, xH))
		{
			menuIsDisplayed = false;
			needErrorMessage = false;
		}

		repaint();
	}

	public void mouseClicked(MouseEvent e)
	{
		int mouseX = e.getX();
		int mouseY = e.getY();

		if(menuIsDisplayed)
		{
			ifMenuClicked(mouseX, mouseY, selectedItem);
		}

		else
		{
			ifItemClicked(mouseX, mouseY);
		}

		//Exit button click check
		if(buttonClickCheck(mouseX, mouseY, 20, 10, 400, 130))
		{
			enterShop = false;
			System.out.println(enterShop);
		}

	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
