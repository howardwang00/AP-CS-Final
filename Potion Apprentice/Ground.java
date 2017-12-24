import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Ground extends Tile {
	public Ground(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void drawMe(Graphics g) {
		//Do not draw anything because the background already is the ground's look
	}
	
	@Override
	public boolean getCollision(MainCharacter main, int direction) {
		return false;
	}
}