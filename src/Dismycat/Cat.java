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
			if (x > -4) {
				x=x-sp;
				System.out.printf("%s\n",x);
			}
			
			}
		
		public void moveRight() {
			if (x < 966) {
				x=x+sp;
				System.out.printf("%s\n",x);
			}
			}
		
		public void moveUp() {
			if (y > 0) {
				y=y-sp;
				System.out.printf("%s\n",y);
			}
			}
		
		public void moveDown() {
			if (y < 408) {
				y=y+sp;
				System.out.printf("%s\n",y);
			}
			}


		public int getXPosition() {
			
			return x;
		}


		public int getYPosition() {
		
			return y;
		}
	
}
