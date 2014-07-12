package Dismycat;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
//import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;





public class Display implements KeyListener{
	
		protected JLabel label;
	
		public int xx = 75;
		public int yy = 75;
		public int tile = 32;
		public int wid = 32; //(tiles)
		public int hig = 17; //(tiles)
		public int l;
		public int w;
		private boolean leftHeld = false;
		private boolean rightHeld = false;
		private boolean upHeld = false;
		private boolean downHeld = false;
		private Cat cat;
		
		
		
		// GUI Data
		private JFrame frame; // The window itself
		private Canvas playArea;  // The area where the game takes place
		
		private final int playWidth = 1000; // The width of the play area (in pixels)
		private final int playHeight = 450; // The height of the play area (in pixels)
		
		BufferedImage img = null;
		BufferedImage img2 = null;
		BufferedImage img3 = null;

		//private int x;

		//private int y;
		
		// Set up the game and play!
		public Display()
		{
			// Get everything set up
			configureGUI();
			cat = new Cat(playWidth/2, playHeight/2);
			
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
		
		// The main game loop. This method coordinates everything that happens in
		// the game
		private void playGame()
		{
			Graphics g = playArea.getGraphics();
			
			while(true)
			{
				// Measure the current time in an effort to keep up a consistent
				// frame rate
				long time = System.currentTimeMillis();
			
				handleKeyEntries();
				
																				
				if(leftHeld||rightHeld||upHeld||downHeld){
					drawBkd(g);
					drawCat(g, cat);
				}
												
				// Sleep until it's time to draw the next frame 
				// (i.e. 32 ms after this frame started processing)
				try
				{
					long delay = Math.max(0, 32-(System.currentTimeMillis()-time));
					
					Thread.sleep(delay);
				}
				catch(InterruptedException e)
				{
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
			if(downHeld){
				cat.moveDown();
				}
				
			}
		
		public void getImages (Graphics g) //might want to try making this a class with a method for obtaining each image, and return type of image if possible.
		{
		
		try
			{
				img2 = ImageIO.read(new File("cattrans.png"));
			}	
				catch (IOException e){}
		try
			{
				img = ImageIO.read(new File("bgyellow.png"));
			}	
				catch (IOException e1){}
		
		try
			{
				img3 = ImageIO.read(new File("bgblue.png"));
			}	
				catch (IOException e1){}
									
			}
		
	
		
		private void drawCat(Graphics g, Cat s){

			// Figure out where the ship should be drawn
			int x = s.getXPosition();
			int y = s.getYPosition();
			
			getImages(g);
			
			// Draw the cat body
			g.drawImage(img2,x,y,null); 
			
			}
	
		private void drawBkd(Graphics g){
			
			getImages(g);
			
			l = 0;
			while(l<hig) //c=1; c <= 9990; c++
			{
				
				w = 0;
				while(w<wid)
				{
					
					int placex = tile*w;
					int placey = tile*l;
					
					if(l != 8||w == 12||w == 13)
					{
						g.drawImage(img,placex,placey,null);
					}
					else {
						g.drawImage(img3,placex,placey,null);

					}
									
					//System.out.printf("%d, %d, %d, %d \n", placex, placey, l, w);
					w++;
								
				}
	
				l++;
			}
				
			//try {Thread.sleep(0); } 
			//catch(InterruptedException ex) {Thread.currentThread().interrupt(); }
			
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
	}
	
	public static void main(String[] args) 
	{
		new Display();
	}

}


		
	


