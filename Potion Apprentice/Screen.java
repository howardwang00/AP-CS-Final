import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements KeyListener {
	
	private MainCharacter main;
	private boolean playerMoveUp = false;
	private boolean playerMoveDown = false;
	private boolean playerMoveRight = false;
	private boolean playerMoveLeft = false;
	
	private boolean startMenu = true;
	private BufferedImage background;
	private double backgroundX = -100;
	private double backgroundY = -100;
	
	private int level = 1;
	private boolean gameOver = false;
	private ArrayList<Item> inventory;
	private ArrayList<ArrayList<Item>> items;
	
	//Level 1 characters
	private Master master;
	private ComputerCharacter jacques;
	//Level 2 character
	private Hames hames;
	//Level 3 character
	private SushiBoy sushiBoy;
	//Level 4 character
	private PoisonedMaster poisonedMaster;
	
	private FileReader reader;
	private File[] levelDocuments;
	private char[][] levels;
	private ArrayList<ArrayList<Tile>> levelTiles;
	
    public Screen() {
		try
        {
			background = ImageIO.read(new File("./Images/background.png"));
        } catch (IOException e) {}
		
		/*** ===========================	LEVELS		============================ ***/
		
		levelDocuments = new File[4];
		levelDocuments[0] = new File("./Levels/level1.txt");
		levelDocuments[1] = new File("./Levels/level2.txt");
		levelDocuments[2] = new File("./Levels/level3.txt");
		levelDocuments[3] = new File("./Levels/level4.txt");
		levels = new char[4][10000];
		
		try 
		{
			for(int i = 0; i < 4; i++) {
				reader = new FileReader(levelDocuments[i]);
				reader.read(levels[i]);
			}
			
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		for(char each : levels[0]) {
			if(each == '\n') {
				System.out.println();
			}
			else {
				System.out.print(each);
			}
		}
		*/
		
		//LOADING LEVELS
		//Note: size of borders must be at least 8, because each tile is 50 tall and wide
		levelTiles = new ArrayList<ArrayList<Tile>>();
		levelTiles.add(new ArrayList<Tile>());	//add four levels to the arraylist of levels
		levelTiles.add(new ArrayList<Tile>());
		levelTiles.add(new ArrayList<Tile>());
		levelTiles.add(new ArrayList<Tile>());
		int x;
		int y;
		
		//int iteration = 0;
		for(int i = 0; i < levels.length; i++) {
			System.out.println("Loading Level " + (i + 1));
			x = -100;
			y = -100;
			for(int j = 0; j < levels[i].length; j++) {
				if(levels[i][j] != '\0') {	//at the end of the file, \0 is null for char
					if(levels[i][j] == 't') {
						levelTiles.get(i).add(new Tree(x, y));
						//System.out.print("tree");
						//System.out.println(++iteration);
					}
					else if(levels[i][j] == 'g') {
						levelTiles.get(i).add(new Ground(x, y));
					}
					else if(levels[i][j] == 'p') {
						levelTiles.get(i).add(new Portal(x, y));
					}
					else if(levels[i][j] == 'c') {
						levelTiles.get(i).add(new Cauldron(x, y));
					}
					else if(levels[i][j] == 'C') {
						levelTiles.get(i).add(new Chest(x, y));
					}
					else if(levels[i][j] == '\n') {
						levelTiles.get(i).add(null);	//add a placeholder for the new line
						y = y + 50;
						x = -100;
						x = x - 50;	//to offset the x + 50 below
					}
					x = x + 50;
					//System.out.println(++iteration);
				}
				else {
					break;
				}
			}
		}
		
		
		/*** ===========================	ITEMS		============================ ***/
		
		main = new MainCharacter();
		inventory = new ArrayList<Item>();
		items = new ArrayList<ArrayList<Item>>();
		items.add(new ArrayList<Item>());
		items.add(new ArrayList<Item>());
		items.add(new ArrayList<Item>());
		items.add(new ArrayList<Item>());
		items.get(0).add(new Doge(925, 325));
		items.get(0).add(new Flask(800, -50));
		
		master = new Master(100, 50);
		jacques = new Jacques(300, 200);
		
		setFocusable(true);
		addKeyListener(this);
		startBackgroundMusic();
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
        //draw background
        g.setColor(Color.white);
		//piece the backgrounds together
        g.drawImage(background, (int)backgroundX - 590, (int)backgroundY - 445, 1200, 900, null);
        g.drawImage(background, (int)backgroundX - 590, (int)backgroundY + 445, 1200, 900, null);
        g.drawImage(background, (int)backgroundX + 590, (int)backgroundY - 445, 1200, 900, null);
        g.drawImage(background, (int)backgroundX + 590, (int)backgroundY + 445, 1200, 900, null);
		
		//For wider levels
		g.drawImage(background, (int)backgroundX - 1790, (int)backgroundY - 445, 1200, 900, null);
        g.drawImage(background, (int)backgroundX - 1790, (int)backgroundY + 445, 1200, 900, null);
		g.drawImage(background, (int)backgroundX + 1785, (int)backgroundY - 445, 1200, 900, null);
        g.drawImage(background, (int)backgroundX + 1785, (int)backgroundY + 445, 1200, 900, null);
		
		for(Tile each : levelTiles.get(level - 1)) {
			if(each != null) {
				each.drawMe(g);
			}
		}
		for(Item each : items.get(level - 1)) {
			each.drawMe(g);
		}
		if(level == 1) {
			master.drawMe(g);
			jacques.drawMe(g);
		}
		else if(level == 2) {
			hames.drawMe(g);
		}
		else if(level == 3) {
			sushiBoy.drawMe(g);
		}
		else if(level == 4) {
			poisonedMaster.drawMe(g);
		}
		
		
		main.drawMe(g);
		
		//Talking
		if(level == 1) {
			if(master.getCollision(main) == true) {
				master.talk(g);
			}
			else {
				master.talkNumber = 0;
			}
			if(jacques.getCollision(main) == true) {
				jacques.talk(g);
			}
			else {
				jacques.talkNumber = 0;
			}
		}
		else if(level == 2) {
			if(hames.getCollision(main) == true) {
				hames.talk(g);
			}
			else {
				hames.talkNumber = 0;
			}
		}
		else if(level == 3) {
			if(sushiBoy.getCollision(main) == true) {
				sushiBoy.talk(g);
			}
			else {
				sushiBoy.talkNumber = 0;
			}
		}
		else if(level == 4) {
			if(poisonedMaster.getCollision(main) == true) {
				poisonedMaster.talk(g);
			}
			else {
				poisonedMaster.talkNumber = 0;
			}
		}
		
		Font arial = new Font("Arial", Font.PLAIN, 30);
		g.setFont(arial);
		g.setColor(Color.white);
		g.drawString("Level: " + level, 650, 50);
		displayInventory(g);
		if(startMenu) {
			g.drawImage(background, 0, 0, 800, 600, null);
			Font arial1 = new Font("Arial", Font.PLAIN, 50);
			g.setFont(arial1);
			g.drawString("Potion Apprentice", 200, 300);
			Font arial2 = new Font("Arial", Font.PLAIN, 20);
			g.setFont(arial2);
			g.drawString("Press any key", 325, 350);
			g.drawString("Arrow keys to move", 300, 450);
			g.drawString("Space to interact", 315, 500);
		}
		if(gameOver) {
			g.drawImage(background, 0, 0, 800, 600, null);
			Font arial1 = new Font("Arial", Font.PLAIN, 75);
			g.setFont(arial1);
			g.drawString("You Won!", 225, 300);
			Font arial2 = new Font("Arial", Font.PLAIN, 20);
			g.setFont(arial2);
			g.drawString("Thank you for playing!", 300, 350);
		}
    }
	public void animate()
    {
        //wait for .1 second
        while(true)
        {
            try {
                //Thread.sleep(10);
            	Thread.sleep(5);	//reduce repaint lag
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
			
			//Collision detection
			for(Tile each : levelTiles.get(level - 1)) {
				if(each != null && each.toString() != "Portal" && each.getCollision(main, 2) == true) {
					playerMoveUp = false;
				}
			}
			for(Tile each : levelTiles.get(level - 1)) {
				if(each != null && each.toString() != "Portal" && each.getCollision(main, 3) == true) {
					playerMoveDown = false;
				}
			}
			for(Tile each : levelTiles.get(level - 1)) {
				if(each != null && each.toString() != "Portal" && each.getCollision(main, 0) == true) {
					playerMoveLeft = false;
				}
			}
			for(Tile each : levelTiles.get(level - 1)) {
				if(each != null && each.toString() != "Portal" && each.getCollision(main, 1) == true) {
					playerMoveRight = false;
				}
			}
			
			//Movement
			if(playerMoveUp == true) {
				for(Tile each : levelTiles.get(level - 1)) {
					if(each != null) {
						each.moveDown();
					}
				}
				for(Item each : items.get(level - 1)) {
					each.moveDown();
				}
				if(level == 1) {
					master.moveDown();
					jacques.moveDown();
				}
				else if(level == 2) {
					hames.moveDown();
				}
				else if(level == 3) {
					sushiBoy.moveDown();
				}
				else if(level == 4) {
					poisonedMaster.moveDown();
				}
				backgroundY+=Movable.speed;
			}
			if(playerMoveDown == true) {
				for(Tile each : levelTiles.get(level - 1)) {
					if(each != null) {
						each.moveUp();
					}
				}
				for(Item each : items.get(level - 1)) {
					each.moveUp();
				}
				if(level == 1) {
					master.moveUp();
					jacques.moveUp();
				}
				else if(level == 2) {
					hames.moveUp();
				}
				else if(level == 3) {
					sushiBoy.moveUp();
				}
				else if(level == 4) {
					poisonedMaster.moveUp();
				}
				backgroundY-=Movable.speed;
			}
			if(playerMoveRight == true) {
				for(Tile each : levelTiles.get(level - 1)) {
					if(each != null) {
						each.moveLeft();
					}
				}
				for(Item each : items.get(level - 1)) {
					each.moveLeft();
				}
				if(level == 1) {
					master.moveLeft();
					jacques.moveLeft();
				}
				else if(level == 2) {
					hames.moveLeft();
				}
				else if(level == 3) {
					sushiBoy.moveLeft();
				}
				else if(level == 4) {
					poisonedMaster.moveLeft();
				}
				backgroundX-=Movable.speed;
			}
			if(playerMoveLeft == true) {
				for(Tile each : levelTiles.get(level - 1)) {
					if(each != null) {
						each.moveRight();
					}
				}
				for(Item each : items.get(level - 1)) {
					each.moveRight();
				}
				if(level == 1) {
					master.moveRight();
					jacques.moveRight();
				}
				else if(level == 2) {
					hames.moveRight();
				}
				else if(level == 3) {
					sushiBoy.moveRight();
				}
				else if(level == 4) {
					poisonedMaster.moveRight();
				}
				backgroundX+=Movable.speed;
			}
			
			/*
			backgroundMusicTimer++;
			if(backgroundMusicTimer > 20800) {
				System.out.println("Playing Music");
				backgroundMusic();	//plays every once 208 seconds (how long the music lasts)
				backgroundMusicTimer = 0;
			}
			*/
            //repaint the graphics drawn
            repaint();
        }
    }
	
	public void keyPressed(KeyEvent e) {
        //key code
        //http://www.cambiaresearch.com/articles/15/javascript-char-codes-key-codes
		startMenu = false;
		if(e.getKeyCode() == 80) {	//Level Cheat Key
			if(level == 1) {
				inventory.add(new Flask(0, 0));
				startLevel2();
			}
			else if(level == 2) {
				startLevel3();
			}
			else if(level == 3) {
				startLevel4();
			}
        }
        if(e.getKeyCode() == 38) {	//Up Arrow
			playerMoveUp = true;
        }
        if(e.getKeyCode() == 40) {	//Down Arrow
			playerMoveDown = true;
        }
		if(e.getKeyCode() == 37) {	//Left Arrow
			playerMoveLeft = true;
        }
		if(e.getKeyCode() == 39) {	//Right Arrow
			playerMoveRight = true;
        }
		if(e.getKeyCode() == 32) {	//Space Key, Interact: talk, pick up
			for(int i = 0; i < items.get(level - 1).size(); i++) {
				if(items.get(level - 1).get(i).getCollision(main, 0) == true) {
					inventory.add(items.get(level - 1).remove(i));
					gotItem();
					i--;
				}
			}
			
			if(level == 1) {
				if(find(inventory, "Apprentice Flask") != -1 && master.hasTwinkie() == true) {
					//System.out.println("Apprentice Flask: " + find(inventory, "Apprentice Flask"));
					for(Tile each : levelTiles.get(0)) {
						if(each != null && each.toString() == "Portal" && each.getCollision(main, 2) == true) {
							startLevel2();
							break;
						}
					}
				}
				
				if(master.getCollision(main) == true) {
					if(master.firstTalk == true && master.talkNumber == 0) {
						if(find(inventory, "Doge Twinkie") != -1) {
							master.gotTwinkie();
							inventory.remove(find(inventory, "Doge Twinkie"));
						}
					}
					else if(master.talkNumber == 3) {
						master.firstTalk = true;
					}
					master.talkNumber++;
				}
				if(jacques.getCollision(main) == true) {
					jacques.talkNumber++;
				}
			}
			else if(level == 2) {
				if(hames.hasPotion() == true) {
					for(Tile each : levelTiles.get(1)) {
						if(each != null && each.toString() == "Portal" && each.getCollision(main, 2) == true) {
							startLevel3();
							break;
						}
					}
				}
				
				if(hames.getCollision(main) == true) {
					if(hames.firstTalk == true) {
						if(find(inventory, "Anti-Fat Potion") != -1 && hames.talkNumber == 0) {
							hames.gotPotion();
							inventory.remove(find(inventory, "Anti-Fat Potion"));
						}
					}
					else if(hames.talkNumber == 6) {
						hames.firstTalk = true;
					}
					hames.talkNumber++;
				}
				for(Tile each : levelTiles.get(1)) {
					if(each != null && each.toString() == "Cauldron" && each.getCollision(main, 5) == true) {
						//When main is interacting with the cauldron, pass in 5 to see whether it is close instead of whether main is being blocked.
						//System.out.println("Cauldron");
						//System.out.println("Mushroom: " + find(inventory, "Magical Mushroom"));
						//System.out.println("Pumpkin: " + find(inventory, "Pumpkin"));
						//System.out.println("Berries: " + find(inventory, "Rare Berries"));
						if(find(inventory, "Magical Mushroom") != -1 && find(inventory, "Pumpkin") != -1 && find(inventory, "Rare Berries") != -1) {
							inventory.remove(find(inventory, "Apprentice Flask"));
							inventory.remove(find(inventory, "Magical Mushroom"));
							inventory.remove(find(inventory, "Pumpkin"));
							inventory.remove(find(inventory, "Rare Berries"));
							inventory.add(new Potion(0, 0));
							gotItem();
							//System.out.println("Potion made");
						}
						break;
					}
				}
			}
			else if(level == 3) {
				if(find(inventory, "Key") != -1) {
					for(Tile each : levelTiles.get(2)) {
						if(each != null && each.toString() == "Portal" && each.getCollision(main, 2) == true) {
							startLevel4();
							break;
						}
					}
				}
				if(sushiBoy.getCollision(main) == true) {
					if(sushiBoy.firstTalk == true) {
						if(find(inventory, "Anti-Bronze Potion") != -1 && sushiBoy.talkNumber == 0) {
							sushiBoy.gotPotion();
							inventory.remove(find(inventory, "Anti-Bronze Potion"));
						}
						if(sushiBoy.talkNumber == 2 && sushiBoy.hasPotion() == true) {
							if(find(inventory, "Key") == -1) {
								inventory.add(new Key(0, 0));
								gotItem();
							}
						}
					}
					else if(sushiBoy.talkNumber == 5) {
						sushiBoy.firstTalk = true;
					}
					sushiBoy.talkNumber++;
				}
				for(Tile each : levelTiles.get(2)) {
					if(each != null && each.toString() == "Cauldron" && each.getCollision(main, 5) == true) {
						//When main is interacting with the cauldron, pass in 5 to see whether it is close instead of whether main is being blocked.
						//System.out.println("Cauldron");
						//System.out.println("Sightstone: " + find(inventory, "Sightstone"));
						//System.out.println("Tear: " + find(inventory, "Tear"));
						//System.out.println("Diamond: " + find(inventory, "Diamond"));
						if(find(inventory, "Apprentice Flask") != -1 && find(inventory, "Sightstone") != -1 && find(inventory, "Tear") != -1 && find(inventory, "Diamond") != -1) {
							if(find(inventory, "Sightstone") < find(inventory, "Tear") && find(inventory, "Tear") < find(inventory, "Diamond")) {
								inventory.remove(find(inventory, "Apprentice Flask"));
								inventory.remove(find(inventory, "Sightstone"));
								inventory.remove(find(inventory, "Tear"));
								inventory.remove(find(inventory, "Diamond"));
								inventory.add(new Potion2(0, 0));
								gotItem();
								//System.out.println("Potion made");
							}
							else {
								startLevel3();
							}
						}
						break;
					}
				}
			}
			
			else if(level == 4) {
				if(poisonedMaster.getCollision(main) == true) {
					if(poisonedMaster.firstTalk == true) {
						if(find(inventory, "Potion of Life") != -1 && poisonedMaster.talkNumber == 0) {
							poisonedMaster.cured();
							inventory.remove(find(inventory, "Potion of Life"));
						}
					}
					else if(poisonedMaster.talkNumber == 5) {
						poisonedMaster.firstTalk = true;
						inventory.add(new Flask(0, 0));
						gotItem();
					}
					if(poisonedMaster.isCured() == true && poisonedMaster.talkNumber == 2) {
						gameOver = true;
						nextLevel();
					}
					poisonedMaster.talkNumber++;
				}
				for(Tile each : levelTiles.get(3)) {
					if(each != null && each.toString() == "Chest" && each.getCollision(main, 5) == true) {
						if(find(inventory, "Key") != -1) {
							inventory.remove(find(inventory, "Key"));
							inventory.add(new SecretIngredient(0, 0));
							gotItem();
						}
					}
					if(each != null && each.toString() == "Cauldron" && each.getCollision(main, 5) == true) {
						//When main is interacting with the cauldron, pass in 5 to see whether it is close instead of whether main is being blocked.
						//System.out.println("Cauldron");
						//System.out.println("Sightstone: " + find(inventory, "Sightstone"));
						//System.out.println("Tear: " + find(inventory, "Tear"));
						//System.out.println("Diamond: " + find(inventory, "Diamond"));
						int count = 0;
						for(int i = 0; i < inventory.size(); i++) {
							if(inventory.get(i).toString() == "Ember") {
								count++;
							}
						}
						if(find(inventory, "Apprentice Flask") != -1 && find(inventory, "Secret Ingredient") != -1 && count == 3) {
							inventory.remove(find(inventory, "Apprentice Flask"));
							inventory.remove(find(inventory, "Secret Ingredient"));
							inventory.remove(find(inventory, "Ember"));
							inventory.remove(find(inventory, "Ember"));
							inventory.remove(find(inventory, "Ember"));
							inventory.add(new Potion3(0, 0));
							gotItem();
							//System.out.println("Potion made");
						}
						break;
					}
				}
			}
			
		}
		//System.out.println(e.getKeyCode());
        repaint();
    }
	
    public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38) {	//Up Arrow
			playerMoveUp = false;
        }
        if(e.getKeyCode() == 40) {	//Down Arrow
			playerMoveDown = false; 
        }
		if(e.getKeyCode() == 37) {	//Left Arrow
			playerMoveLeft = false;
        }
		if(e.getKeyCode() == 39) {	//Right Arrow
			playerMoveRight = false;
        }
	}
    public void keyTyped(KeyEvent e) {}
	
	
	public void displayInventory(Graphics g) {
		int x = 50;
		int y = 80;
		
		g.setColor(Color.white);
		g.drawString("Inventory", 50, 50);
		Font arial = new Font("Arial", Font.PLAIN, 15);
		g.setFont(arial);
		for(int i = 0; i < inventory.size(); i++) {
			for(int j = i; j < inventory.size(); j++) {
				if(i != j && inventory.get(i).toString() == inventory.get(j).toString()) {
					inventory.add(i, inventory.remove(j));
				}
			}
			inventory.get(i).drawMe(g, x - 50, y - 25);
			String drawn = inventory.get(i).toString();
			if(i + 1 < inventory.size() && inventory.get(i).toString() == inventory.get(i + 1).toString()) {
				if(i + 2 < inventory.size() && inventory.get(i).toString() == inventory.get(i + 2).toString()) {
					drawn = drawn + " x3";
					i+=2;
				}
				else {
					drawn = drawn + " x2";
					i++;
				}
			}
			g.drawString(drawn, x, y);
			y = y + 50;
		}
	}
	public void clearInventory() {
		for(int i = 0; i < inventory.size(); i++) {
			inventory.remove(i);
			i--;
			//System.out.println("Removed: " + i);
		}
	}
	public void startLevel2() {
		nextLevel();
		clearInventory();
		inventory.add(new Flask(0, 0));
		hames = new Hames(0, 350);
		items.get(1).add(new Pumpkin(150, 150));
		items.get(1).add(new Berries(1275, 100));
		items.get(1).add(new Mushroom(500, 200));
		level = 2;
		for(int i = 0; i < levelTiles.get(0).size(); i++) {
			levelTiles.get(0).remove(i);
			i--;
		}
	}
	public void startLevel3() {
		nextLevel();
		clearInventory();
		boolean temp = false;
		if(sushiBoy != null) {
			temp = sushiBoy.firstTalk;
		}
		sushiBoy = new SushiBoy(0, 200);
		sushiBoy.firstTalk = temp;
		
		items.get(2).add(new Flask(400, 400));
		//items.get(2).add(new Sightstone(150, 200));
		//items.get(2).add(new Diamond(205, 200));
		//items.get(2).add(new Tear(400, 200));
		
		items.get(2).add(new Sightstone(950, 400));
		items.get(2).add(new Diamond(950, 200));
		items.get(2).add(new Tear(950, 0));
		
		int x = -100;
		int y = -100;
		//int newlines = 0;
		for(int j = 0; j < levelTiles.get(2).size(); j++) {
			if(levelTiles.get(2).get(j) == null) {
				y = y + 50;
				x = -100;
				x = x - 50;	//to offset the x + 50 below
			}
			else {
				levelTiles.get(2).get(j).setPostion(x, y);
			}
			x = x + 50;
		}
		
		//System.out.println("Level 3 Start");
		level = 3;
		for(int i = 0; i < levelTiles.get(1).size(); i++) {
			levelTiles.get(1).remove(i);
			i--;
		}
	}
	public void startLevel4() {
		nextLevel();
		clearInventory();
		inventory.add(new Key(0, 0));
		poisonedMaster = new PoisonedMaster(350, 200);
		items.get(3).add(new Ember(500, 300));
		items.get(3).add(new Ember(850, 450));
		items.get(3).add(new Ember(0, 0));
		level = 4;
		for(int i = 0; i < levelTiles.get(2).size(); i++) {
			levelTiles.get(2).remove(i);
			i--;
		}
	}
	
	
	public int find(ArrayList<Item> items, String item) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).toString() == item) {
				return i;
			}
		}
		return -1;
	}
	
	
	public void startBackgroundMusic() {
		try
        {
           URL url = this.getClass().getClassLoader().getResource("./Sounds/all's fairy in love and war runescape.wav");
           Clip clip = AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(url));
           //clip.start();
           clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
	}
	public void nextLevel() {
		try
        {
           URL url = this.getClass().getClassLoader().getResource("./Sounds/achievement.wav");
           Clip clip = AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(url));
           clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
	}
	public void gotItem() {
		try
        {
           URL url = this.getClass().getClassLoader().getResource("./Sounds/gotItem.wav");
           Clip clip = AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(url));
           clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
	}
}