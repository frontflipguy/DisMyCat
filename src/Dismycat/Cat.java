//this class is called cat, but it's purpose actually evolved and now it is the class that takes care
//of giving the illusion that the cat is moving, by moving the background instead.
//it also (is supposed to) to handle collision detection with solid objects and lakes.

package Dismycat;

public class Cat extends java.lang.Object{

	int x = 0;
	int y = 0;
	int sp = 10; //speed of cat
	int tile = Display.tile;
	int wid = Display.wid;
	int hig = Display.hig;
	char[][] mapSolids = Display.mapSolids;
	int xTileUL;
	int yTileUL;
	int xTileLU;
	int yTileLU;
	int xTileUR;
	int yTileUR;
	int xTileRU;
	int yTileRU;
	int xTileLD;
	int yTileLD;
	int xTileDL;
	int yTileDL;
	int xTileRD;
	int yTileRD;
	int xTileDR;
	int yTileDR;
	
	int edgeUL;
	int edgeLU;
	int edgeUR;
	int edgeRU;
	int edgeLD;
	int edgeDL;
	int edgeRD;
	int edgeDR;
	
	
		public Cat(int x, int y){
			
		}
	
		
		public void moveLeft() {
			
			wallLU();
			wallLD();
			
			if(edgeLU > 0 && edgeLD > 0){x=x-sp;} //if the distance to the edge of a block is greater than 0, ALLOW MOVEMENT!!!!
								
				System.out.printf("%s, %s\n",edgeLU, edgeLD);
				
								
			}
		
		public void moveRight() {
			
			wallRU();
			wallRD();
			
			if(edgeRU > 0 && edgeRD > 0){x=x+sp;}
						
				System.out.printf("%s, %s\n",edgeRU, edgeRD);
				
			}
		
		public void moveUp() {
			
			wallUL();
			wallUR();
			
			if(edgeUL > 0 && edgeUR > 0){y=y-sp;}		
			
				System.out.printf("%s, %s\n",edgeUL,edgeUR);
				
			}
		
		public void moveDown() {
			
			wallDL();
			wallDR();
			
			if(edgeDL > 0 && edgeDR > 0){y=y+sp;}
				
				System.out.printf("%s, %s\n", edgeDL,edgeDR);
				
			}


		public int getXPosition() {
			if(x < 0){x = x + tile*wid;} //sets x back to its max value if it goes below 0 so that the map can be repeating infinitely! 
			if(x > tile*wid){x = x - tile*wid;} //if x gets too high (the width of the entire map) it gets reset to zero or something close to it, for infinitely repeating shiz!
			
			return x+3;
		}


		public int getYPosition() {
			if(y < 0){y = y + tile*hig;}
			if(y > tile*wid){y = y - tile*wid;}
			
			return y;
		}
	
		//////////////////////////////////////////////////////
		//The following 8 methods detect the eight squares (as exemplified below) around the cat character and return their respective distances
		//
		//			 OO
		//			O  O
		//			O  O
		//			 OO
		//
		//U, D, L, and R stand for Up, Down, Left, and Right, respectively
		//////////////////////////////////////////////////////
		
		
		private int wallUL(){ 
			
			xTileUL =  ((int) ((x+544)/tile))%wid; 	//converts pixel value of x and y to the array coordinates of the 
			yTileUL = ((int) ((y+256)/tile))%hig;		//tile to the upper left of the 4 central-most tiles at any given time. the modulus is taken so the map can be repeating
			
			if(mapSolids[xTileUL][yTileUL] == 's'){edgeUL = y%tile - 14;} //edgeUL = the remainder of the value of y divided by tile width, minus 14 gives the distance to the nearby solid object
			else{edgeUL = 1110;}
			
			
				return edgeUL;
		}
		
		private int wallUR(){ 
			
			xTileUR = ((int)((x + 544)/tile))%wid;
			yTileUR = ((int)((y + 256)/tile))%hig;
			
			if(mapSolids[xTileUR][yTileUR] == 's'){edgeUR = y%tile - 14;}
			else{edgeUR = 1110;}
			
				return edgeUR;
		}
		
		private int wallLU(){ 
			
			xTileLU = ((int) ((x + 512)/tile))%wid;
			yTileLU = ((int) ((y + 288)/tile))%hig;
			
			if(mapSolids[xTileLU][yTileLU] == 's'){edgeLU = x%tile - 14;}
			else{edgeLU = 1110;}
			
				return edgeLU;
		}
		
		private int wallLD(){ 
			
			xTileLD = ((int) ((x + 512)/tile))%wid;
			yTileLD = ((int) ((y + 288)/tile))%hig;
			
			if(mapSolids[xTileLD][yTileLD] == 's'){edgeLD = x%tile - 14;}
			else{edgeLD = 1110;}
			
			
				return edgeLD;
		}

		private int wallRU(){ 
			
			xTileRU = ((int)((x+568)/tile))%wid;
			yTileRU = ((int)((y+288)/tile))%hig;
			
			if(mapSolids[xTileRU][yTileRU] == 's'){edgeRU = 22 - x%tile;}
			else{edgeRU = 1110;}
			
				return edgeRU;
		}
		
		private int wallRD(){ 
			
			xTileRD = ((int) ((x+568)/tile))%wid;
			yTileRD = ((int) ((y+288)/tile))%hig;
			
			if(mapSolids[xTileRD][yTileRD] == 's'){edgeRD = 22 - x%tile;}
			else{edgeRD = 1110;}
			
			
				return edgeRD;
		}
		
		private int wallDL(){ 
			
			xTileDL = ((int) ((x + 544)/tile))%wid;
			yTileDL = ((int) ((y + 320)/tile))%hig;
			
			if(mapSolids[xTileDL][yTileDL] == 's'){edgeDL = 8 - y%tile;}
			else{edgeDL = 1110;}
			
				return edgeDL;
		}
		
		private int wallDR(){ 
			
			xTileDR = ((int) ((x+544)/tile))%wid;
			yTileDR = ((int) ((y + 320)/tile))%hig;
			
			if(mapSolids[xTileDR][yTileDR] == 's'){edgeDR = 8 - y%tile;}
			else{edgeDR = 1110;}
			
				return edgeDR;
		}
		
}
