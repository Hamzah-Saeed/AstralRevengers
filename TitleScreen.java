import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TitleScreen extends JPanel {
    // Fields
    BufferedImage titleBG = null;
    BufferedImage play = null;
    BufferedImage logo = null;
    boolean displayControls = false;
    BufferedImage controls = null;
    BufferedImage exit = null;
    BufferedImage textBox = null;
    BufferedImage controlMenu = null;
    private boolean needTextBox = false;
    BufferedImage xButton = null;

    // Constructor
    public TitleScreen(Main m) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                ifItemClicked(mouseX, mouseY, m);
            }
        });

        try {
            titleBG = ImageIO.read(new File("TitleScreenBackgroundPixelated.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            logo = ImageIO.read(new File("Logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            play = ImageIO.read(new File("PlayButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            controls = ImageIO.read(new File("ControlsButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            exit = ImageIO.read(new File("ExitButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textBox = ImageIO.read(new File("TextboxSquare.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            xButton = ImageIO.read(new File("/SpaceGame/src/X-Button.png"));
        } catch (IOException e) {
        }
    }

    // Paint method
    public void paint(Graphics g) {
        g.drawImage(titleBG, 0, 0, 1400, 1000, null);
        g.drawImage(logo, 300, 100, 800, 288, null);
        g.drawImage(play, 600, 450, 200, 97, null);
        g.drawImage(controls, 600, 600, 200, 97, null);

        // Displays text box if needed
        if (needTextBox) {
            displayControls(g);
        }
    }

    public void displayControls(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(textBox, 300, 200, 800, 460, null);
        g.setColor(Color.CYAN);
        Font myFont = new Font ("Courier New", 1, 60);
        g2d.setFont(myFont);
        g2d.drawString("Use the arrow keys", 380, 320);
        g2d.drawString("to move and jump.", 380, 400);
        g2d.drawString("Press the spacebar", 380, 480);
        g2d.drawString("to shoot.", 380, 560);
        try
        {
            xButton = ImageIO.read(new File("ExitButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(xButton, 1000, 150, 100, 100, null);
    }

    // Checks if the mouse x and y are within a button
    private boolean buttonClickCheck(int mouseX, int mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        boolean xRange = mouseX >= buttonX && mouseX <= buttonX + buttonWidth;
        boolean yRange = mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
        return xRange && yRange;
    }

    // Does this when an item is clicked
    public void ifItemClicked(int mouseX, int mouseY, Main m) {
        if (buttonClickCheck(mouseX, mouseY, 600, 600, 200, 97)) {
            // Control button Check
            needTextBox = true;
        } else if (buttonClickCheck(mouseX, mouseY, 600, 450, 200, 97)) {
            // Play button Check
            m.setIsPaused(false);
        }
        //text box x button click check
        if(buttonClickCheck(mouseX, mouseY, 1000, 150, 100, 100))
        {
            needTextBox = false;
        }
        repaint();
    }

    // Mouse event methods
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

    }



}
