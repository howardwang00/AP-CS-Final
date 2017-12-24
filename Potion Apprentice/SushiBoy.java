import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SushiBoy extends ComputerCharacter {
	public boolean firstTalk = false;
	private boolean gotPotion = false;
	private BufferedImage bronze;
	public SushiBoy(int x, int y) {
		super(x, y);
		
		try
        {
			image = ImageIO.read(new File("./Images/sushiBoy.png"));
			bronze = ImageIO.read(new File("./Images/bronze.png"));
        } catch (IOException e) {}
		
	}
	@Override
	public void drawMe(Graphics g) {
		g.drawImage(image, (int)x + 20, (int)y, width - 20, height, null);
		if(gotPotion == false) {
			g.drawImage(bronze, (int)x - 35, (int)y, 200, 150, null);
		}
		g.drawString("SushiBoy", (int)x + width/2 - 15, (int)y + height + 10);
	}
	public void talk(Graphics g) {
		Font arial = new Font("Arial", Font.PLAIN, 15);
		g.setFont(arial);
		
		if(gotPotion == false) {
			if(talkNumber < 6 && talkNumber >= 0) {
				//g.setColor(Color.blue);
				//g.fillRect(0, 400, 800, 200);
				g.drawImage(dialogueBox, 0, 400, 800, 200, null);
			}
			g.setColor(Color.white);
			if(talkNumber == 0) {
				g.drawString("SushiBoy: HELP!!! I'M STUCK IN BRONZE!!! HELP!!!", 50, 450);
			}
			else if(talkNumber == 1) {
				g.drawString("Apprentice: Hello? Is someone inside there?", 50, 450);
			}
			else if(talkNumber == 2) {
				g.drawString("SushiBoy: SOMEONE CAME! I THOUGHT I WOULD BE FOREVER ALONE IN BRONZE!", 50, 450);
				g.drawString("DO YOU KNOW HOW BAD IT IS IN HERE? SET ME FREE!!!!", 50, 470);
			}
			else if(talkNumber == 3) {
				g.drawString("Apprentice: Alright I will help you (since I have nothing better to do). I will make a potion to melt the bronze.", 50, 450);
			}
			else if(talkNumber == 4) {
				g.drawString("SushiBoy: Thank you so much! I will finally be out of elo hell!", 50, 450);
				g.drawString("Apprentice: (what is he talking about)", 50, 470);
			}
			else if(talkNumber == 5) {
				g.drawString("Apprentice: I must put the ingredients in the cauldron in correct order based off of order in inventory.", 50, 450);
				g.drawString("If I do not make the right potion, I will have to start over.", 50, 470);
			}
			if(talkNumber < 6) {
				g.setColor(Color.white);
				g.drawString("Press space to continue", 500, 550);
			}
		}
		else {
			if(talkNumber < 4 && talkNumber >= 0) {
				//g.setColor(Color.blue);
				//g.fillRect(0, 400, 800, 200);
				g.drawImage(dialogueBox, 0, 400, 800, 200, null);
			}
			g.setColor(Color.white);
			if(talkNumber == 1) {	//got Anti-Bronze potion
				g.drawString("SushiBoy: Thank you so much for freeing me!!", 50, 450);
			}
			else if(talkNumber == 2) {
				g.drawString("SushiBoy: In return, I give you a key. This is the key to a secret ingredient.", 50, 450);
				g.drawString("The ingredient can cure any poison. But you must find the chest is hidden somewhere.", 50, 470);
			}
			else if(talkNumber == 3) {
				g.drawString("Apprentice: Thank you very much!", 50, 450);
			}
			if(talkNumber < 4) {
				g.setColor(Color.white);
				g.drawString("Press space to continue", 500, 550);
			}
		}
	}
	public void gotPotion() {
		gotPotion = true;
	}
	public boolean hasPotion() {
		return gotPotion;
	}
}