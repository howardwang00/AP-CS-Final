import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class BlockTile extends Tile {
	public BlockTile() {
		height = 50;
		width = 50;
	}
	@Override
	public boolean getCollision(MainCharacter main, int direction) {
		//direction in which main is "moving": 0 - left, 1 - right, 2 - up, 3 - down
		int mainX = main.getX();
		int mainY = main.getY();
		int mainHeight = main.getHeight() - 5;
		int mainWidth = main.getWidth() - 15;
		
		int treeHeight = height - 15;
		int treeWidth = width - 8;
		
		if(direction == 0) {	//coming from the right
			if(mainX > x + 25 && mainX < x + treeWidth && mainY + mainHeight - 3 > y && mainY < y + treeHeight - 3) {	// - 3 is to offset it from the wall so you can move along walls
				return true;
			}
			return false;
		}
		else if(direction == 1) {	//coming from the left
			if(mainX + mainWidth > x && mainX + mainWidth < x + 25 && mainY + mainHeight - 3 > y && mainY < y + treeHeight - 3) {
				return true;
			}
			return false;
		}
		else if(direction == 2) {	//coming from below
			if(mainX + mainWidth - 3 > x && mainX < x + treeWidth - 3 && mainY > y + 25 && mainY < y + treeHeight) {
				return true;
			}
			return false;
		}
		else if(direction == 3) {	//coming from above
			if(mainX + mainWidth - 3 > x && mainX < x + treeWidth - 3 && mainY + mainHeight < y + 25 && mainY + mainHeight > y) {
				return true;
			}
			return false;
		}
		return false;
	}
	
}