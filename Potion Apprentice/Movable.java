import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class Movable {
	public static final double speed = 1;
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	public void drawMe(Graphics g) {
		g.drawImage(image, (int)x, (int)y, width, height, null);
	}
	public boolean getCollision(MainCharacter main, int direction) {
		//ignore direction, direction is for obstacles only
		int mainX = main.getX();
		int mainY = main.getY();
		int mainHeight = main.getHeight() - 5;
		int mainWidth = main.getWidth() - 15;
		
		int objectHeight = height - 5;
		int objectWidth = width - 8;
		
		if(mainX + mainWidth > x && mainX < x + objectWidth && mainY + mainHeight > y && mainY < y + objectHeight) {
			return true;
		}
		return false;
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	public void moveUp() {
		y = y - Movable.speed;
	}
	public void moveDown() {
		y = y + Movable.speed;
	}
	public void moveRight() {
		x = x + Movable.speed;
	}
	public void moveLeft() {
		x = x - Movable.speed;
	}
}