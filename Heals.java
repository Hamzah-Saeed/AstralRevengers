import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Sumaiya S.
public class Heals {
	public int healAmount; // amount of HP healed
	public int playerHP; // amount of HP player currently has
	public int playerMaxHP; // player's max HP
	public int healNum;
	public BufferedImage slot= null;
	public Heals (int healAmount, int playerHP,int playerMaxHP, String imgName) // constructor
	{
		this.healAmount = healAmount;
		this.playerHP = playerHP;
		this.playerMaxHP = playerMaxHP;
		try{
			slot = ImageIO.read(new File(imgName));
		}catch (IOException e){

		}
	}
	public int getHeals() // get the amount you can heal
	{
		return healAmount;
	}
	public boolean canHeal() // check if player can heal
	{
		if (playerHP < playerMaxHP) // only return true if player is not at max HP
			return true;
		else
			return false;
	}
	public int heal() // increase player's HP
	{
		if (this.canHeal())
		{
			if (playerHP + healAmount > playerMaxHP) //heal to maxHP if the item heals more than needed
				return playerMaxHP;
			else
				return playerHP + healAmount; //otherwise, increase the player's HP by the heal amount
		}
		else
			return playerMaxHP; // if the player cannot heal, it means they have full HP already, so return maxHP
	}

	public void paint(Graphics g, int x, int y, int w, int h){
		g.drawImage(slot, x, y, w, h, null);
	}
}
