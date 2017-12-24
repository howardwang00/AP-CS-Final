import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Jacques extends ComputerCharacter {
	public Jacques(int x, int y) {
		super(x, y);
		
		try
        {
			image = ImageIO.read(new File("./Images/jacques.png"));
        } catch (IOException e) {}
		
	}
	@Override
	public void drawMe(Graphics g) {
		super.drawMe(g);
		g.drawString("Jacques", (int)x + width/2 - 30, (int)y + height + 10);	//x + width/2 - 15
	}
	public void talk(Graphics g) {
		if(talkNumber < 5 && talkNumber != 0) {
			//g.setColor(Color.blue);
			//g.fillRect(0, 400, 800, 200);
			g.drawImage(dialogueBox, 0, 400, 800, 200, null);
		}
		
		Font arial = new Font("Arial", Font.PLAIN, 15);
		g.setFont(arial);
		g.setColor(Color.white);
		if(talkNumber == 1) {
			g.drawString("Jacques: Hello, I'm Jacques, and I am here to help you on your way. Here are some helpful tips!", 50, 450);
		}
		else if(talkNumber == 2) {
			g.drawString("Jacques: First, use the arrow keys to move around.", 50, 450);
		}
		else if(talkNumber == 3) {
			g.drawString("Jacques: Also, press space when near characters or items to interact.", 50, 450);
			g.drawString("This allows you to pick items up, or talk to people.", 50, 470);
		}
		else if(talkNumber == 4) {
			g.drawString("Jacques: Your Master has some urgent matters to speak with you. Go talk to him.", 50, 450);
			g.drawString("I hope this was helpful. Good luck on your journeys!", 50, 470);
		}
		if(talkNumber < 5) {
			g.setColor(Color.white);
			g.drawString("Press space to continue", 500, 550);
		}
	}
}