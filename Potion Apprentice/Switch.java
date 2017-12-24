import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Switch extends BlockTile {
	public Switch(int x, int y) {
		this.x = x;
		this.y = y;
		
		try
        {
			image = ImageIO.read(new File("./Images/switch.png"));
			//ground is automatically below Switch because of background
        } catch (IOException e) {}
	}
	@Override
	public boolean getCollision(MainCharacter main, int direction) {
		//ignore direction, direction is for obstacles only. Only for detecting whether character is close
		int mainX = main.getX();
		int mainY = main.getY();
		
		if(mainX > x - 5 && mainX < x + 5 && mainY > y + 5 && mainY < y - 5) {
			System.out.println("Collided with main");
			return true;
		}
		return false;
	}
	public String toString() {
		return "Switch";
	}
}