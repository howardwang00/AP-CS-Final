import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Portal extends Tile {
	public Portal(int x, int y) {
		this.x = x;
		this.y = y;
		try
        {
			image = ImageIO.read(new File("./Images/portal.png"));
			//ground is automatically below portal because of background
        } catch (IOException e) {}
	}
	public String toString() {
		return "Portal";
	}
}