import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Potion2 extends Item {
	public Potion2(int x, int y) {
		super(x, y);
		
		try
        {
			image = ImageIO.read(new File("./Images/fullPotion2.png"));
        } catch (IOException e) {}
		
	}
	public String toString() {
		return "Anti-Bronze Potion";
	}
}