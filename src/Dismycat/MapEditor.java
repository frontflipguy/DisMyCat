package Dismycat;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.TextField;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Dismycat.Cat;
import Dismycat.loadImage;

public class MapEditor implements KeyListener {
	
	char activeThing;
	
	String whichMap = "Kitty's Garden.txt";
	protected JLabel label;
	private JFrame frame; // The window itself
	private Canvas playArea;  // The area where the game takes place
	private JPanel options; //the panel where the option buttons will be
	private JPanel title; //words at the top
	Button btns; // a save button
	Button btng; //a grass button
	Button btnb; //a bush button
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	TextField input;
	protected JLabel saveAs;
	
	public int h;
	public int w = 0;
	public int hh = 0;
	public int ww = 0;
	private boolean leftHeld = false;
	private boolean rightHeld = false;
	private boolean upHeld = false;
	private boolean downHeld = false;
	private boolean unclick = false;
	int locx;
    int locy;
	private Cat cat = new Cat(1056/2, 544/2);
	private loadImage images;
	public static int tile = 75; //the pixel width of 1 tile
	public static int wid = 64; //the number of (tiles) wide the whole map is
	public static int hig = 64; //the number of (tiles) high the whole map is
	public static int playWidth = 1056; // The width of the play area (in pixels)
	public static int playHeight = 544; // The height of the play area (in pixels)
	int ar1;
	int ar2;
	static char[][] mapSolids = new char[hig + ((playHeight/2)/tile + 15)][wid + ((playWidth/2)/tile) + 15];
	BufferedImage img = null;
	
	public String mapTitle;
	
	private void configureGUI()
	{
		
		// Create the window object
		frame = new JFrame("Dis my cat - Editor");
		frame.setSize(1320, 644);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// The program should end when the window is closed		
		frame.setLayout(new FlowLayout()); // Set the window's layout manager
		
		title = new JPanel();
		title.setPreferredSize(new Dimension(1290, 20));
		label = new JLabel();
		label.setText("Dis my cat - Editor");
		title.add(label);
		frame.add(title);
		
		input = new TextField();
		saveAs = new JLabel();
		saveAs.setText("What is name this map?");
		input.setFocusable(true);
		btns = new Button("Save");
		btng = new Button("Grass");
		btnb = new Button("Bush");
		btn1 = new Button("Water1");
		btn2 = new Button("Water2");
		btn3 = new Button("Water3");
		btn4 = new Button("Water4");
		btn5 = new Button("Water5");
		btn6 = new Button("Water6");
		btn7 = new Button("Water7");
		btn8 = new Button("Water8");
		btn9 = new Button("Water9");
		input.setPreferredSize(new Dimension(232, 20));
		//saveAs.setPreferredSize(new Dimension(232, 30));
		btn9.setPreferredSize(new Dimension(75, 75));
		btns.setPreferredSize(new Dimension(75, 75));
		btng.setPreferredSize(new Dimension(75, 75));
		btnb.setPreferredSize(new Dimension(75, 75));
		btn1.setPreferredSize(new Dimension(75, 75));
		btn2.setPreferredSize(new Dimension(75, 75));
		btn3.setPreferredSize(new Dimension(75, 75));
		btn4.setPreferredSize(new Dimension(75, 75));
		btn5.setPreferredSize(new Dimension(75, 75));
		btn6.setPreferredSize(new Dimension(75, 75));
		btn7.setPreferredSize(new Dimension(75, 75));
		btn8.setPreferredSize(new Dimension(75, 75));
		btns.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {saveButton();}});
		btng.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='0';}});
		btnb.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='s';System.out.print("bushes!\n");}});
		btn1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='1';}});
		btn2.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='2';}});
		btn3.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='3';}});
		btn4.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='4';}});
		btn5.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='5';}});
		btn6.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='6';}});
		btn7.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='7';}});
		btn8.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='8';}});
		btn9.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {activeThing='9';}});
		
		
		options = new JPanel();
		options.setPreferredSize(new Dimension(240, 544));
		options.setVisible(true);
		btns.setFocusable(false);
		btng.setFocusable(false);
		btnb.setFocusable(false);
		btn1.setFocusable(false);
		btn2.setFocusable(false);
		btn3.setFocusable(false);
		btn4.setFocusable(false);
		btn5.setFocusable(false);
		btn6.setFocusable(false);
		btn7.setFocusable(false);
		btn8.setFocusable(false);
		btn9.setFocusable(false);
		options.add(saveAs);
		options.add(input);
		options.add(btns);
		options.add(btng);
		options.add(btnb);
		options.add(btn1);
		options.add(btn2);
		options.add(btn3);
		options.add(btn4);
		options.add(btn5);
		options.add(btn6);
		options.add(btn7);
		options.add(btn8);
		options.add(btn9);
		frame.add(options);
		
		// Create the play area
		playArea = new Canvas();
		playArea.setSize(1056, 544);
		playArea.setBackground(Color.BLACK);
		playArea.setFocusable(true);
		playArea.setVisible(true);
		frame.add(playArea);
		// Make the frame listen to keystrokes
		playArea.addKeyListener(this);
		MouseListener ears = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				locx = e.getX();
		        locy = e.getY();
		        
		        System.out.printf("You clicked at %d, %d.\n", locx, locy);
		        
		        placeTile();
		        
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					
					unclick = true;}
			}
			
		};
		playArea.addMouseListener(ears);
		
		
	}

	public MapEditor() {
			
		configureGUI();
		loadMap();
		//solidArray();
		images = new loadImage(null);
		// Display the window so play can begin
		frame.setVisible(true);
		EditMap();
	}
	
	private void loadMap() {
		h = 0;
		
		// Loop
		while(h<64)
		{
			mapSolids[w][h] = '0';
			
			System.out.printf("%s ",mapSolids[w][h]);
			w++;
	
				if(w==64){
					h++;
					w = 0;
					System.out.printf("\n\n");
					}
		}
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

	private void EditMap() // The main game loop. This method coordinates everything that happens in the game
	{
		Graphics g = playArea.getGraphics();
		
		
		while(true)
		{
			// Measure the current time in an effort to keep up a consistent frame rate
			long time = System.currentTimeMillis();
		
			handleKeyEntries();
														
			if(leftHeld||rightHeld||upHeld||downHeld||unclick){
				drawBkd(g, cat, images);
				
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
				
				if(mapSolids[ar1][ar2] == '0'){fin = bg1;}
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
	
	public void placeTile(){
		
		int x = cat.getXPosition(); //x is the number of pixels away from 0, 0.
		int y = cat.getYPosition();
		
		double arrx = (Math.floor((double) (locx+x)/tile))%wid;
		double arry = (Math.floor((double) (locy+y)/tile))%hig;
			
		System.out.printf("This tile is %s across and %s down.",(int)arrx,(int)arry);
			
		mapSolids[(int)arrx][(int)arry] = activeThing;
		
	}
	
	public void saveButton(){
		
		mapTitle = input.getText(); //returns the text in the input field
		mapTitle = mapTitle.concat(".txt");
			
		while(hh < hig){
			
			printThingy();
			ww++;
			
			if(ww == wid){
				ww = 0;
				hh++;
			}
			
		}
		System.out.printf("successfully saved file as %s", mapTitle);
		
		
	}
	
	public void printThingy(){
		
		BufferedWriter writer = null;
				
		
		try {
            //create a temporary file
            
            File logFile = new File(mapTitle);

            //This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.write(mapSolids[ww][hh]); 
            writer.write(" "); 
            if(ww == wid-1){writer.write(System.getProperty("line.separator")); System.out.printf("it worked");}
        } 
		catch (Exception e) {
            e.printStackTrace();
        } 
		finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } 
            catch (Exception e) {
            }
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
			
			public void keyTyped(KeyEvent arg0) {}
			
			public void keyPressed(KeyEvent key)
			{	
				System.out.printf("hi");
				if(key.getKeyCode() == KeyEvent.VK_LEFT)
					leftHeld = true;
				if(key.getKeyCode() == KeyEvent.VK_RIGHT)
					rightHeld = true;
				if(key.getKeyCode() == KeyEvent.VK_UP)
					upHeld = true;
				if(key.getKeyCode() == KeyEvent.VK_DOWN)
					downHeld = true;
			}
			
			public void keyReleased(KeyEvent key) 
			{
				System.out.printf("hi");
				if(key.getKeyCode() == KeyEvent.VK_LEFT)
					leftHeld = false;
				if(key.getKeyCode() == KeyEvent.VK_RIGHT)
					rightHeld = false;
				if(key.getKeyCode() == KeyEvent.VK_UP)
					upHeld = false;
				if(key.getKeyCode() == KeyEvent.VK_DOWN)
					downHeld = false;
			}
			
				

	public static void main(String[] args) {
		new MapEditor();

	}

	
	

}
