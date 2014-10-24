//The purpose of this class is to make it easier to import images. 
//There are a lot of images so it makes sense to handle them here and not clutter up the code
//in the main class.

package Dismycat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImage {

	BufferedImage bg1 = null;
	BufferedImage bg2 = null;
	BufferedImage bg3 = null;
	BufferedImage bg4 = null;
	BufferedImage bg5 = null;
	BufferedImage lk1 = null;
	BufferedImage lk2 = null;
	BufferedImage lk3 = null;
	BufferedImage lk4 = null;
	BufferedImage lk5 = null;
	BufferedImage lk6 = null;
	BufferedImage lk7 = null;
	BufferedImage lk8 = null;
	BufferedImage lk9 = null;
	BufferedImage img1 = null;
	BufferedImage img2 = null;
	BufferedImage img3 = null;
	BufferedImage img4 = null;
	BufferedImage imgSolid = null;
	
	
	public loadImage(Graphics g) {
		
	}

	public BufferedImage getBg1(Graphics g){
		try
		{
			bg1 = ImageIO.read(new File("bg1.png"));
		}	
			catch (IOException e){}
		return bg1;
	}
	
	
	public BufferedImage getBg2(Graphics g){
		
		try
		{
			bg2 = ImageIO.read(new File("bg2.png"));
		}	
			catch (IOException e){}
		
		return bg2;
	}
	
	public BufferedImage getBg3(Graphics g){
		
		try
		{
			bg3 = ImageIO.read(new File("bg3.png"));
		}	
			catch (IOException e){}
		
		return bg3;
	}

	public BufferedImage getBg4(Graphics g){
	
	try
	{
		bg4 = ImageIO.read(new File("bg4.png"));
	}	
		catch (IOException e){}
	
	return bg4;
	}
	
	public BufferedImage getBg5(Graphics g){
		
		try
		{
			bg5 = ImageIO.read(new File("bg5.png"));
		}	
			catch (IOException e){}
		
		return bg5;
	}
	
	
	public BufferedImage getCatUp(Graphics g){
		
		try
		{
			img1 = ImageIO.read(new File("catus20.png"));
		}	
			catch (IOException e){}
		
		return img1;
	}
	
	public BufferedImage getCatDown(Graphics g){
		
		try
		{
			img2 = ImageIO.read(new File("catds20.png"));
		}	
			catch (IOException e){}
		
		return img2;
	}

	public BufferedImage getCatLeft(Graphics g){
	
	try
	{
		img3 = ImageIO.read(new File("catls20.png"));
	}	
		catch (IOException e){}
	
	return img3;
	}
	
	public BufferedImage getCatRight(Graphics g){
		
		try
		{
			img4 = ImageIO.read(new File("catrs20.png"));
		}	
			catch (IOException e){}
		
		return img4;
	}
	
	public BufferedImage getSolid(Graphics g){
		
		try
		{
			imgSolid = ImageIO.read(new File("bush1.png"));
		}	
			catch (IOException e){}
		
		return imgSolid;
	}
		
	public BufferedImage getlk1(Graphics g){
		
		try
		{
			lk1 = ImageIO.read(new File("bglake1.png"));
		}	
			catch (IOException e){}
		
		return lk1;
	}
	
	public BufferedImage getlk2(Graphics g){
		
		try
		{
			lk2 = ImageIO.read(new File("bglake2.png"));
		}	
			catch (IOException e){}
		
		return lk2;
	}
	
	public BufferedImage getlk3(Graphics g){
		
		try
		{
			lk3 = ImageIO.read(new File("bglake3.png"));
		}	
			catch (IOException e){}
		
		return lk3;
	}
	
	public BufferedImage getlk4(Graphics g){
		
		try
		{
			lk4 = ImageIO.read(new File("bglake4.png"));
		}	
			catch (IOException e){}
		
		return lk4;
	}
	
	public BufferedImage getlk5(Graphics g){
		
		try
		{
			lk5 = ImageIO.read(new File("bglake5.png"));
		}	
			catch (IOException e){}
		
		return lk5;
	}
	
	public BufferedImage getlk6(Graphics g){
		
		try
		{
			lk6 = ImageIO.read(new File("bglake6.png"));
		}	
			catch (IOException e){}
		
		return lk6;
	}
	
	public BufferedImage getlk7(Graphics g){
		
		try
		{
			lk7 = ImageIO.read(new File("bglake7.png"));
		}	
			catch (IOException e){}
		
		return lk7;
	}
	
	public BufferedImage getlk8(Graphics g){
		
		try
		{
			lk8 = ImageIO.read(new File("bglake8.png"));
		}	
			catch (IOException e){}
		
		return lk8;
	}
	
	public BufferedImage getlk9(Graphics g){
		
		try
		{
			lk9 = ImageIO.read(new File("bglake9.png"));
		}	
			catch (IOException e){}
		
		return lk9;
	}
	
	
	
}
