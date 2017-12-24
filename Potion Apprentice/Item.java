import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class Item extends Movable {
	public Item(int x, int y) {
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
	}
	public void drawMe(Graphics g, int x, int y) {
		//new x and y overrides old positions
		g.drawImage(image, x, y, width, height, null);
	}
	/*
	public String toString() {
		return null;
	}
	*/
}