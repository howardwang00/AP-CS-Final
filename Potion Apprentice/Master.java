import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Master extends ComputerCharacter {
	public boolean firstTalk = false;
	private boolean gotTwinkie = false;
	public Master(int x, int y) {
		super(x, y);
		
		try
        {
			image = ImageIO.read(new File("./Images/Master.png"));
        } catch (IOException e) {}
		
	}
	@Override
	public void drawMe(Graphics g) {
		super.drawMe(g);
		g.drawString("Master", (int)x + width/2 - 15, (int)y + height + 10);
	}
	public void talk(Graphics g) {
		if(talkNumber < 4 && talkNumber > 0) {
			//g.setColor(Color.blue);
			//g.fillRect(0, 400, 800, 200);
			g.drawImage(dialogueBox, 0, 400, 800, 200, null);
		}
		
		Font arial = new Font("Arial", Font.PLAIN, 15);
		g.setFont(arial);
		g.setColor(Color.white);
		
		if(gotTwinkie == false) {
			if(talkNumber == 1) {
				g.drawString("Master: Hey apprentice, I can teach you how to make potions.", 50, 450);
			}
			else if(talkNumber == 2) {
				g.drawString("Master: First, I need you to find a doge twinkie for me. It's very important for my new potion.", 50, 450);
				g.drawString("I believe it may be somewhere around here.", 50, 470);
			}
			else if(talkNumber == 3) {
				g.drawString("Apprentice: I will not fail you Master!", 50, 450);
			}
		}
		else {
			if(talkNumber == 1) {	//got doge twinkie
				g.drawString("Master: Good job finding the doge twinkie. (It was actually my snack that I dropped earlier)", 50, 450);
			}
			else if(talkNumber == 2) {
				g.drawString("Master: Every level, there will be a Portal to let you move on to the next.", 50, 450);
				g.drawString("Once you are done with the level, interact with the Portal to continue.", 50, 470);
			}
			else if(talkNumber == 3) {
				g.drawString("Master: Now, go find the magical flask to finish the level. You will need it to hold potions.", 50, 450);
			}
		}
		if(talkNumber < 4) {
			g.setColor(Color.white);
			g.drawString("Press space to continue", 500, 550);
		}
	}
	public void gotTwinkie() {
		gotTwinkie = true;
	}
	public boolean hasTwinkie() {
		return gotTwinkie;
	}
}