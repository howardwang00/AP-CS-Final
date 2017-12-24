import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Tree extends BlockTile {
	//private static final BufferedImage treeImage = ImageIO.read(new File("./Images/Tree.png"));
	private static BufferedImage treeImage;
	public Tree(int x, int y) {
		this.x = x;
		this.y = y;
		
		
		try
        {
			treeImage = ImageIO.read(new File("./Images/Tree.png"));
			//ground is automatically below tree because of background
        } catch (IOException e) {}
		
	}
	@Override
	public void drawMe(Graphics g) {
		g.drawImage(treeImage, (int)x, (int)y, width, height, null);
	}
}