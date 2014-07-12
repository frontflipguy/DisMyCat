package Dismycat;

public class Cat extends java.lang.Object{

	int x;
	int y;
	int sp = 6; //speed of cat
	
		public Cat(int x, int y){
			
			//this.x = x;
			//this.y = y;
			
		}
	
		
		public void moveLeft() {
			
			 if((x == 378 && (y > 216 && y < 288))){
				x=x;}
			 
				else if (x > -4) {
				x=x-sp;
				
				System.out.printf("%s, %s\n",x,y);
				}
			
			
			}
		
		public void moveRight() {
			 if((x == 420 && (y > 216 && y < 288))){
				x=x;}
			 
				else if (x < 966) {
				x=x+sp;
				
				System.out.printf("%s, %s\n",x,y);
				}
			}
		
		public void moveUp() {
			if ((y > 0) && ((y != 288 ) || (x > 378 && x < 420))) {
				y=y-sp;
				System.out.printf("%s, %s\n",x,y);
			}
			}
		
		public void moveDown() {
			if ((y < 408) && ((y != 216 ) || (x > 378 && x < 420))) {
				y=y+sp;
				System.out.printf("%s, %s\n",x,y);
			}
			}


		public int getXPosition() {
			
			return x;
		}


		public int getYPosition() {
		
			return y;
		}
	
}
