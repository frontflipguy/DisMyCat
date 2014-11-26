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
	
	int upEdge = 256;
	int downEdge = 360;
	int leftEdge = 550;
	int rightEdge = 640;
	int upWall = 575; //for running along a wall smoothly
	int downWall = 575;
	int leftWall = 300;
	int rightWall = 300;
	
	
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
			
			xTileUL = ((int) ((x + upWall)/tile))%wid; 	//converts pixel value of x and y to the array coordinates of the 
			yTileUL = ((int) ((y + upEdge)/tile))%hig;		//tile to the upper left of the 4 central-most tiles at any given time. the modulus is taken so the map can be repeating
			
			if(mapSolids[xTileUL][yTileUL] == 's'
			|mapSolids[xTileUL][yTileUL] == '1'
			|mapSolids[xTileUL][yTileUL] == '2'
			|mapSolids[xTileUL][yTileUL] == '3'
			|mapSolids[xTileUL][yTileUL] == '4'
			|mapSolids[xTileUL][yTileUL] == '5'
			|mapSolids[xTileUL][yTileUL] == '6'
			|mapSolids[xTileUL][yTileUL] == '7'
			|mapSolids[xTileUL][yTileUL] == '8'
			|mapSolids[xTileUL][yTileUL] == '9'
			|mapSolids[xTileUL][yTileUL] == 'y'){edgeUL = y%tile - 14;} //edgeUL = the remainder of the value of y divided by tile width, minus 14 gives the distance to the nearby solid object
			else{edgeUL = 1110;}
			
			
				return edgeUL;
		}
		
		private int wallUR(){ 
			
			xTileUR = ((int)((x + upWall)/tile))%wid;
			yTileUR = ((int)((y + upEdge)/tile))%hig;
			
			if(mapSolids[xTileUR][yTileUR] == 's'
			|mapSolids[xTileUR][yTileUR] == '1'
			|mapSolids[xTileUR][yTileUR] == '2'
			|mapSolids[xTileUR][yTileUR] == '3'
			|mapSolids[xTileUR][yTileUR] == '4'
			|mapSolids[xTileUR][yTileUR] == '5'
			|mapSolids[xTileUR][yTileUR] == '6'
			|mapSolids[xTileUR][yTileUR] == '7'
			|mapSolids[xTileUR][yTileUR] == '8'
			|mapSolids[xTileUR][yTileUR] == '9'
			|mapSolids[xTileUR][yTileUR] == 'y'){edgeUR = y%tile - 14;}
			else{edgeUR = 1110;}
			
				return edgeUR;
		}
		
		private int wallLU(){ 
			
			xTileLU = ((int) ((x + leftEdge)/tile))%wid;
			yTileLU = ((int) ((y + leftWall)/tile))%hig;
			
			if(mapSolids[xTileLU][yTileLU] == 's'
			|mapSolids[xTileLU][yTileLU] == '1'
			|mapSolids[xTileLU][yTileLU] == '2'
			|mapSolids[xTileLU][yTileLU] == '3'
			|mapSolids[xTileLU][yTileLU] == '4'
			|mapSolids[xTileLU][yTileLU] == '5'
			|mapSolids[xTileLU][yTileLU] == '6'
			|mapSolids[xTileLU][yTileLU] == '7'
			|mapSolids[xTileLU][yTileLU] == '8'
			|mapSolids[xTileLU][yTileLU] == '9'
			|mapSolids[xTileLU][yTileLU] == 'y'){edgeLU = x%tile - 70;}
			else{edgeLU = 1110;}
			
				return edgeLU;
		}
		
		private int wallLD(){ 
			
			xTileLD = ((int) ((x + leftEdge)/tile))%wid;
			yTileLD = ((int) ((y + leftWall)/tile))%hig;
			
			if(mapSolids[xTileLD][yTileLD] == 's'
			|mapSolids[xTileLD][yTileLD] == '1'
			|mapSolids[xTileLD][yTileLD] == '2'
			|mapSolids[xTileLD][yTileLD] == '3'
			|mapSolids[xTileLD][yTileLD] == '4'
			|mapSolids[xTileLD][yTileLD] == '5'
			|mapSolids[xTileLD][yTileLD] == '6'
			|mapSolids[xTileLD][yTileLD] == '7'
			|mapSolids[xTileLD][yTileLD] == '8'
			|mapSolids[xTileLD][yTileLD] == '9'
			|mapSolids[xTileLD][yTileLD] == 'y'){edgeLD = x%tile - 70;}
			else{edgeLD = 1110;}
			
			
				return edgeLD;
		}

		private int wallRU(){ 
			
			xTileRU = ((int)((x + rightEdge)/tile))%wid;
			yTileRU = ((int)((y + rightWall)/tile))%hig;
			
			if(mapSolids[xTileRU][yTileRU] == 's'
			|mapSolids[xTileRU][yTileRU] == '1'
			|mapSolids[xTileRU][yTileRU] == '2'
			|mapSolids[xTileRU][yTileRU] == '3'
			|mapSolids[xTileRU][yTileRU] == '4'
			|mapSolids[xTileRU][yTileRU] == '5'
			|mapSolids[xTileRU][yTileRU] == '6'
			|mapSolids[xTileRU][yTileRU] == '7'
			|mapSolids[xTileRU][yTileRU] == '8'
			|mapSolids[xTileRU][yTileRU] == '9'
			|mapSolids[xTileRU][yTileRU] == 'y'){edgeRU = 50 - x%tile;}
			else{edgeRU = 1110;}
			
				return edgeRU;
		}
		
		private int wallRD(){ 
			
			xTileRD = ((int) ((x + rightEdge)/tile))%wid;
			yTileRD = ((int) ((y + rightWall)/tile))%hig;
			
			if(mapSolids[xTileRD][yTileRD] == 's'
			|mapSolids[xTileRD][yTileRD] == '1'
			|mapSolids[xTileRD][yTileRD] == '2'
			|mapSolids[xTileRD][yTileRD] == '3'
			|mapSolids[xTileRD][yTileRD] == '4'
			|mapSolids[xTileRD][yTileRD] == '5'
			|mapSolids[xTileRD][yTileRD] == '6'
			|mapSolids[xTileRD][yTileRD] == '7'
			|mapSolids[xTileRD][yTileRD] == '8'
			|mapSolids[xTileRD][yTileRD] == '9'
			|mapSolids[xTileRD][yTileRD] == 'y'){edgeRD = 50 - x%tile;}
			else{edgeRD = 1110;}
			
			
				return edgeRD;
		}
		
		private int wallDL(){ 
			
			xTileDL = ((int) ((x + downWall)/tile))%wid;
			yTileDL = ((int) ((y + downEdge)/tile))%hig;
			
			if(mapSolids[xTileDL][yTileDL] == 's'
			|mapSolids[xTileDL][yTileDL] == '1'
			|mapSolids[xTileDL][yTileDL] == '2'
			|mapSolids[xTileDL][yTileDL] == '3'
			|mapSolids[xTileDL][yTileDL] == '4'
			|mapSolids[xTileDL][yTileDL] == '5'
			|mapSolids[xTileDL][yTileDL] == '6'
			|mapSolids[xTileDL][yTileDL] == '7'
			|mapSolids[xTileDL][yTileDL] == '8'
			|mapSolids[xTileDL][yTileDL] == '9'
			|mapSolids[xTileDL][yTileDL] == 'y'){edgeDL = 36 - y%tile;}
			else{edgeDL = 1110;}
			
				return edgeDL;
		}
		
		private int wallDR(){ 
			
			xTileDR = ((int) ((x + downWall)/tile))%wid;
			yTileDR = ((int) ((y + downEdge)/tile))%hig;
			
			if(mapSolids[xTileDR][yTileDR] == 's'
			|mapSolids[xTileDR][yTileDR] == '1'
			|mapSolids[xTileDR][yTileDR] == '2'
			|mapSolids[xTileDR][yTileDR] == '3'
			|mapSolids[xTileDR][yTileDR] == '4'
			|mapSolids[xTileDR][yTileDR] == '5'
			|mapSolids[xTileDR][yTileDR] == '6'
			|mapSolids[xTileDR][yTileDR] == '7'
			|mapSolids[xTileDR][yTileDR] == '8'
			|mapSolids[xTileDR][yTileDR] == '9'
			|mapSolids[xTileDR][yTileDR] == 'y'){edgeDR = 36 - y%tile;}
			else{edgeDR = 1110;}
			
				return edgeDR;
		}
		
}
