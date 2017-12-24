import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class Tile extends Movable {
	public Tile() {
		height = 50;
		width = 50;
	}
	public void setPostion(int x, int y) {
		this.x = x;
		this.y = y;
	}
}