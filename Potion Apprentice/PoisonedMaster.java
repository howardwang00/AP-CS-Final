import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PoisonedMaster extends ComputerCharacter {
	public boolean firstTalk = false;
	private boolean cured = false;
	public PoisonedMaster(int x, int y) {
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
		
		Font arial = new Font("Arial", Font.PLAIN, 15);
		g.setFont(arial);
		g.setColor(Color.white);
		
		if(cured == false) {
			if(talkNumber <= 5 && talkNumber > 0) {
				//g.setColor(Color.blue);
				//g.fillRect(0, 400, 800, 200);
				g.drawImage(dialogueBox, 0, 400, 800, 200, null);
			}
			if(talkNumber == 1) {
				g.drawString("Master: Apprentice! Thank god that you are here.", 50, 450);
				g.drawString("I have been poisoned by an assassin's dart from an ambush.", 50, 470);
			}
			else if(talkNumber == 2) {
				g.drawString("Apprentice: What do I do? Is there a special potion that can cure you?", 50, 450);
			}
			else if(talkNumber == 3) {
				g.drawString("Master: The only way to cure this poison is to find a special ingredient.", 50, 450);
				g.drawString("Unfortunately, it is hidden in a chest in this forest, and needs a key to be opened.", 50, 470);
				g.drawString("I came here to search for it, but I am too weak to go any longer.", 50, 490);
			}
			else if(talkNumber == 4) {
				g.drawString("Apprentice: Wait! I have a key that might correspond to the chest!", 50, 450);
				g.setColor(Color.green);
				g.drawString("Apprentice pulls out the key.", 50, 470);
			}
			else if(talkNumber == 5) {
				g.drawString("Master: That is the exact key we need! Go make the potion quick!", 50, 450);
				g.drawString("Here is a flask. You will also need 3 embers. Good luck!", 50, 470);
			}
			if(talkNumber < 6) {
				g.setColor(Color.white);
				g.drawString("Press space to continue", 500, 550);
			}
		}
		else {
			if(talkNumber <= 2 && talkNumber > 0) {
				//g.setColor(Color.blue);
				//g.fillRect(0, 400, 800, 200);
				g.drawImage(dialogueBox, 0, 400, 800, 200, null);
			}
			if(talkNumber == 1) {	//cured
				g.drawString("Master: You really made it! Now I am free and healthy again!", 50, 450);
			}
			else if(talkNumber == 2) {
				g.drawString("Master: You have now truly proved yourself as an apprentice.", 50, 450);
				g.drawString("Your journeys as a potion apprentice are over. You have finally earned the title of Potion Master!", 50, 470);
			}
			if(talkNumber < 3) {
				g.setColor(Color.white);
				g.drawString("Press space to continue", 500, 550);
			}
		}
	}
	public void cured() {
		cured = true;
	}
	public boolean isCured() {
		return cured;
	}
}