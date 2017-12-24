import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class ComputerCharacter extends Movable {
	public int talkNumber = 1;
	protected BufferedImage dialogueBox;
	public ComputerCharacter(int x, int y) {
		this.x = x;
		this.y = y;
		width = 100;
		height = 100;
		try
        {
			dialogueBox = ImageIO.read(new File("./Images/dialogue.png"));
        } catch (IOException e) {}
	}
	public boolean getCollision(MainCharacter main) {
		//ignore direction, direction is for obstacles only
		int mainX = main.getX();
		int mainY = main.getY();
		int mainHeight = main.getHeight() - 5;
		int mainWidth = main.getWidth() - 15;
		
		int objectHeight = height - 5;
		int objectWidth = width - 8;
		
		if(mainX + mainWidth + 30 > x && mainX < x + objectWidth + 30 && mainY + mainHeight + 30 > y && mainY < y + objectHeight + 30) {
			return true;
		}
		return false;
	}
	public abstract void talk(Graphics g);
}