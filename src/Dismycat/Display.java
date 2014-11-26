//This program is for practice and amusement. It's about a cat that walks around and explores its environment. Very zen.

package Dismycat;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Display implements KeyListener{
	
		protected JLabel label;
	
		public static int tile = 75; //the pixel width of 1 tile
		public static int wid = 64; //the number of (tiles) wide the whole map is
		public static int hig = 64; //the number of (tiles) high the whole map is
		public int h;
		public int w = 0;
		int ar1;
		int ar2;
		int lootLocx;
		int lootLocy;
		String whichMap = "river.txt";
				
		private boolean leftHeld = false;
		private boolean rightHeld = false;
		private boolean upHeld = false;
		private boolean downHeld = false;
		private boolean lootFound = false;
		private boolean pickUp;
		private boolean loot1off = true;
		private boolean loot2off = true;
		private boolean loot3off = true;
		private boolean loot4off = true;
		private boolean loot5off = true;
		private Cat cat = new Cat(playWidth/2, playHeight/2);
		private loadImage images;
		
		// GUI Data
		private JFrame frame; // The window itself
		private Canvas playArea;  // The area where the game takes place
		
		public static int playWidth = 1056; // The width of the play area (in pixels)
		public static int playHeight = 544; // The height of the play area (in pixels)
		
		BufferedImage img = null;
		static char[][] mapSolids = new char[hig + ((playHeight/2)/tile + 15)][wid + ((playWidth/2)/tile) + 15]; //the +15 is for some wiggle room
		
		
		public Display() // Sets up the game and plays!
		{			
			// Get everything set up
			configureGUI();
			solidArray(); 
			spawnLoot();
			//spawnLoot();
			//spawnLoot();
			//spawnLoot();
			//spawnLoot();
			
			images = new loadImage(null);
			
			// Display the window so play can begin
			frame.setVisible(true);
			
			// Start the gameplay
			playGame();
		}
		
		private void configureGUI()
		{
			// Create the window object
			frame = new JFrame("Dis my cat");
			frame.setSize(playWidth+20, playHeight+100);
			frame.setResizable(false);
			
			// The program should end when the window is closed
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Set the window's layout manager
			frame.setLayout(new FlowLayout());
		
			label = new JLabel();
			label.setText("<html>Dis my cat</html>");
			frame.add(label);
			
			// Create the play area
			playArea = new Canvas();
			playArea.setSize(playWidth, playHeight);
			playArea.setBackground(Color.BLACK);
			
			playArea.setFocusable(false);
			playArea.setVisible(true);
			frame.add(playArea);
			// Make the frame listen to keystrokes
			frame.addKeyListener(this);
			
			
		}
			
		private void playGame() // The main game loop. This method coordinates everything that happens in the game
		{
			Graphics g = playArea.getGraphics();
			
			while(true)
			{
				// Measure the current time in an effort to keep up a consistent frame rate
				long time = System.currentTimeMillis();
			
				handleKeyEntries();
				
																				
				if(leftHeld||rightHeld||upHeld||downHeld||lootFound){
					drawBkd(g, cat, images);
					drawCat(g, cat, images);
					
				}
				
				if(lootFound){
					spawnLoot();
					lootFound = false;
				}
												
				// Sleep until it's time to draw the next frame 
				// (i.e. 32 ms after this frame started processing)
				try
				{
					long delay = Math.max(0, 32 -(System.currentTimeMillis()-time));
					
					Thread.sleep(delay);
				}
				catch(InterruptedException e)
				{
				}
				
			}
		}
		
		private void spawnLoot() {
			
			lootLocx = (int) (Math.random()*wid);
			lootLocy = (int) (Math.random()*hig);
			
			System.out.printf("the loot is at %d, %d\n", lootLocx, lootLocy);
				if(mapSolids[lootLocx][lootLocy] == 'a'||
					mapSolids[lootLocx][lootLocy] == 'b'||
					mapSolids[lootLocx][lootLocy] == 'c'||
					mapSolids[lootLocx][lootLocy] == 'd'||
					mapSolids[lootLocx][lootLocy] == 'e'){
					
					if(loot1off){Loot loot1 = new Loot(lootLocx, lootLocy); loot1off=false;}
					//if(loot2off){Loot loot2 = new Loot(lootLocx, lootLocy); loot2off=false;}
					//if(loot3off){Loot loot3 = new Loot(lootLocx, lootLocy); loot3off=false;}
					//if(loot4off){Loot loot4 = new Loot(lootLocx, lootLocy); loot4off=false;}
					//if(loot5off){Loot loot5 = new Loot(lootLocx, lootLocy); loot5off=false;}
					
					mapSolids[lootLocx][lootLocy] = 'y';
				}
				else{spawnLoot();}
			
		}
		
		private void findLoot(){
			
			catsLoc();
			int loot1x = (lootLocx - 7)%wid;
			int loot1y = (lootLocy - 3)%hig;
			int catActualx = ((ar1 - 36 + wid)%wid)+1;
			int catActualy = ((ar2 - 20 + hig)%hig)+1;
			
			System.out.print(catActualx + " = " + loot1x + " + 1 && " + catActualy + " = " + loot1y);
			
			if(
			(catActualx==loot1x+1&&catActualy==loot1y)||
			(catActualx==loot1x&&catActualy==loot1y+1)||
			(catActualx==loot1x-1&&catActualy==loot1y)||
			(catActualx==loot1x&&catActualy==loot1y-1)
			){
				mapSolids[lootLocx][lootLocy] = 'a';
				loot1off = true;
				lootFound = true;
			}
		
		}

		// Check which keys have been pressed and respond accordingly
		private void handleKeyEntries()
		{
			// Cat movement keys
			if(leftHeld)
				cat.moveLeft();
			if(rightHeld)
				cat.moveRight();
			if(upHeld)
				cat.moveUp();
			if(downHeld)
				cat.moveDown();	
		}
		
		private void drawCat(Graphics g, Cat s, loadImage l){			
				
			BufferedImage img1 = l.getCatUp(g);
			BufferedImage img2 = l.getCatDown(g);
			BufferedImage img3 = l.getCatLeft(g);
			BufferedImage img4 = l.getCatRight(g);
			
			if(leftHeld)
				img = img3;
			if(rightHeld)
				img = img4;
			if(upHeld)
				img = img1;
			if(downHeld)
				img = img2;
			
			// Draw the cat body
			g.drawImage(img,playWidth/2, playHeight/2,null); 
			
			}
		
		private void solidArray() //reads in a txt file and fills out the array with 0s or 1s, 1s signifying solid blocks
		{ 
			
			try
			{
				@SuppressWarnings("resource")
				Scanner fin = new Scanner(new File(whichMap));
				
				h = 0;
				
				// Loop through each line of the file to read the code for what kind of tile to place.
				while(fin.hasNext())
				{
					char mapSolid = fin.next().charAt(0);
					
					if(mapSolids[w][h]!='0'){ //These two if statements interpret what to load into the array from the map file.
					mapSolids[w][h] = mapSolid;}
					
					if(mapSolids[w][h]=='0'){ //(this one makes the grass random)
						double random = Math.random();
						if(random < 0.3){mapSolids[w][h] = 'a';}
						else if(random > 0.2 && random < 0.4){mapSolids[w][h] = 'b';}
						else if(random > 0.4 && random < 0.6){mapSolids[w][h] = 'c';}
						else if(random > 0.6 && random < 0.8){mapSolids[w][h] = 'd';}
						else if(random > 0.8){mapSolids[w][h] = 'e';}
						}
					
					System.out.printf("%s ",mapSolids[w][h]);
					w++;
			
						if(w==64){
							h++;
							w = 0;
							System.out.printf("\n\n");
							}
				
					}
				
			}
			catch(Exception e)
			{
				
			}
		}
		
		private void drawBkd(Graphics g, Cat s, loadImage l){ //takes the tiles loaded into the array "map" and iteratively draws them
			
			BufferedImage bg1 = l.getBg1(g);
			BufferedImage bg2 = l.getBg2(g);
			BufferedImage bg3 = l.getBg3(g);
			BufferedImage bg4 = l.getBg4(g);
			BufferedImage bg5 = l.getBg5(g);
			
			BufferedImage lk1 = l.getlk1(g);
			BufferedImage lk2 = l.getlk2(g);
			BufferedImage lk3 = l.getlk3(g);
			BufferedImage lk4 = l.getlk4(g);
			BufferedImage lk5 = l.getlk5(g);
			BufferedImage lk6 = l.getlk6(g);
			BufferedImage lk7 = l.getlk7(g);
			BufferedImage lk8 = l.getlk8(g);
			BufferedImage lk9 = l.getlk9(g);
			
			BufferedImage yCapsule = l.getYcap(g);
			
			BufferedImage solid = l.getSolid(g);
			BufferedImage fin = null;
			
			int x = cat.getXPosition();
			int y = cat.getYPosition();
			
			h = 0;
			while(h<2*playHeight/hig+3) //the units of these variables are tiles, not pixels 
			{
				
				w = 0;
				while(w<2*playWidth/wid+3)
				{
					
					int placex = tile*w - x%tile; //voodoo magic. I don't remember what I did to make this work.
					int placey = tile*h - y%tile;
					
					catsLoc();
					
					if(mapSolids[ar1][ar2] == 'a'){fin = bg1;} 
					if(mapSolids[ar1][ar2] == 'b'){fin = bg2;} //These determine the bg tile
					if(mapSolids[ar1][ar2] == 'c'){fin = bg3;} //based on the values loaded into the "map" array.
					if(mapSolids[ar1][ar2] == 'd'){fin = bg4;}
					if(mapSolids[ar1][ar2] == 'e'){fin = bg5;}
					if(mapSolids[ar1][ar2] == '1'){fin = lk1;}
					if(mapSolids[ar1][ar2] == '2'){fin = lk2;}
					if(mapSolids[ar1][ar2] == '3'){fin = lk3;}
					if(mapSolids[ar1][ar2] == '4'){fin = lk4;}
					if(mapSolids[ar1][ar2] == '5'){fin = lk5;}
					if(mapSolids[ar1][ar2] == '6'){fin = lk6;}
					if(mapSolids[ar1][ar2] == '7'){fin = lk7;}
					if(mapSolids[ar1][ar2] == '8'){fin = lk8;}
					if(mapSolids[ar1][ar2] == '9'){fin = lk9;}
					if(mapSolids[ar1][ar2] == 's'){fin = solid;}
					if(mapSolids[ar1][ar2] == 'y'){fin = yCapsule;}
										
					g.drawImage(fin,placex,placey,null); //draws the specified tile at placex placey.

					w++;		
				}
	
				h++;
			}
						
		}
		
		private void catsLoc(){ //this will return the cat's location in the array as a pair of coordinates
			
			int x = cat.getXPosition(); //x is the number of pixels away from 0, 0.
			int y = cat.getYPosition();
			
			ar1 = (w+x/tile)%wid; //this gives the array location of a given tile
			ar2 = (h+y/tile)%hig;
		}
		
	public void keyTyped(KeyEvent arg0) {}

	public void keyPressed(KeyEvent key)
	{	
		
		if(key.getKeyCode() == KeyEvent.VK_LEFT)
			leftHeld = true;
		if(key.getKeyCode() == KeyEvent.VK_RIGHT)
			rightHeld = true;
		if(key.getKeyCode() == KeyEvent.VK_UP)
			upHeld = true;
		if(key.getKeyCode() == KeyEvent.VK_DOWN)
			downHeld = true;
		if(key.getKeyCode() == KeyEvent.VK_SPACE)
			pickUp = true;
	}
	
	public void keyReleased(KeyEvent key) 
	{
		
		if(key.getKeyCode() == KeyEvent.VK_LEFT)
			leftHeld = false;
		if(key.getKeyCode() == KeyEvent.VK_RIGHT)
			rightHeld = false;
		if(key.getKeyCode() == KeyEvent.VK_UP)
			upHeld = false;
		if(key.getKeyCode() == KeyEvent.VK_DOWN)
			downHeld = false;
		if(key.getKeyCode() == KeyEvent.VK_SPACE)
			findLoot();
	}
	
	public static void main(String[] args) 
	{
		new Display();
	}

}


		
	


