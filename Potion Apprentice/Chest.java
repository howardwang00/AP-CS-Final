import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Chest extends BlockTile {
	public Chest(int x, int y) {
		this.x = x;
		this.y = y;
		
		try
        {
			image = ImageIO.read(new File("./Images/chest.png"));
			//ground is automatically below Chest because of background
        } catch (IOException e) {}
	}
	@Override
	public boolean getCollision(MainCharacter main, int direction) {
		if(direction == 5) {
			//ignore direction, direction is for obstacles only. Only for detecting whether character is close
			int mainX = main.getX();
			int mainY = main.getY();
			int mainHeight = main.getHeight() - 5;
			int mainWidth = main.getWidth() - 15;
			
			int objectHeight = height - 5;
			int objectWidth = width - 8;
			
			if(mainX + mainWidth > x && mainX < x + objectWidth && mainY + mainHeight > y && mainY < y + objectHeight) {
				return true;
			}
		}
		else {
			return super.getCollision(main, direction);
		}
		return false;
	}
	public String toString() {
		return "Chest";
	}
}