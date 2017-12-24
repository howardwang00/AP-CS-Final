import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MainCharacter {
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage mainCharacter;
	
	public MainCharacter() {
		try
        {
			mainCharacter = ImageIO.read(new File("./Images/mainCharacter.png"));
        } catch (IOException e) {}
		
		
		width = 50;
		height = 50;
		x = 400 - width/2;
		y = 300 - height/2;
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(mainCharacter, x, y, width, height, null);
		g.setColor(Color.yellow);
		g.drawString("You", x + width/2 - 15, y + height + 10);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
}