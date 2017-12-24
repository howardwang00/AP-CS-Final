import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Hames extends ComputerCharacter {
	public boolean firstTalk = false;
	private boolean gotPotion = false;
	public Hames(int x, int y) {
		super(x, y);
		
		try
        {
			image = ImageIO.read(new File("./Images/hames.png"));
        } catch (IOException e) {}
		
	}
	@Override
	public void drawMe(Graphics g) {
		super.drawMe(g);
		g.drawString("Hames the Hefty", (int)x, (int)y + height + 10);
	}
	public void talk(Graphics g) {
		
		if(gotPotion == false) {
			if(talkNumber < 7 && talkNumber > 0) {
				//g.setColor(Color.blue);
				//g.fillRect(0, 400, 800, 200);
				g.drawImage(dialogueBox, 0, 400, 800, 200, null);
			}
			
			Font arial = new Font("Arial", Font.PLAIN, 15);
			g.setFont(arial);
			g.setColor(Color.white);
			
			if(talkNumber == 1) {
				g.drawString("Hames: Hoy apprentice, I heard ye can make good potions.", 50, 450);
				g.drawString("I need some, er, help with me slight problem.", 50, 470);
			}
			else if(talkNumber == 2) {
				g.drawString("Apprentice: Sure, I can help. What may be the problem you mentioned?", 50, 450);
			}
			else if(talkNumber == 3) {
				g.drawString("Hames: Well, er, um, eh, *burp*. Look fer yeself.", 50, 450);
				g.setColor(Color.green);
				g.drawString("Hames pats his fat belly.", 50, 470);
			}
			else if(talkNumber == 4) {
				g.drawString("Apprentice: (Ugh). Ok: enough is enough please. I can help you.", 50, 450);
				g.setColor(Color.green);
				g.drawString("Apprentice tries to get away as far as possible.", 50, 470);
			}
			else if(talkNumber == 5) {
				g.drawString("Hames: Wait! Don't leave so soon ye kid. I ain't told ye how to make the potion yet.", 50, 450);
				g.drawString("Go get some pumpkin, mushroom, and special berry that grows near this forest.", 50, 470);
				g.drawString("Afterwards, interact with the cauldron to create the potion.", 50, 490);
			}
			else if(talkNumber == 6) {
				g.drawString("Hames: Thank you very macho. I have not been able to walk out of this forest fer a long time.", 50, 450);
				g.drawString("This potion will slowly help me get better.", 50, 470);
			}
			if(talkNumber < 7) {
				g.setColor(Color.white);
				g.drawString("Press space to continue", 500, 550);
			}
		}
		else {
			if(talkNumber < 4 && talkNumber > 0) {
				//g.setColor(Color.blue);
				//g.fillRect(0, 400, 800, 200);
				g.drawImage(dialogueBox, 0, 400, 800, 200, null);
			}
			
			Font arial = new Font("Arial", Font.PLAIN, 15);
			g.setFont(arial);
			g.setColor(Color.white);
			
			if(talkNumber == 1) {	//got potion
				g.drawString("Hames: Thanks you very macho! I can now cut down on the extra weight.", 50, 450);
			}
			else if(talkNumber == 2) {
				g.drawString("Hames: To leave and go on your journeys, you must find the Portal.", 50, 450);
			}
			else if(talkNumber == 3) {
				g.drawString("Hames: Watch out, this forest is also a maze. The Portal is hidden inside the forest somewhere. Good luck!", 50, 450);
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