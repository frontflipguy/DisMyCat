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
		
			x=x-sp;
			System.out.printf("%s\n",x);
			}
		
		public void moveRight() {
			
			x=x+sp;
			System.out.printf("%s\n",x);
			}
		
		public void moveUp() {
			
			y=y-sp;
			System.out.printf("%s\n",y);
			}
		
		public void moveDown() {
			
			y=y+sp;
			System.out.printf("%s\n",y);
			}


		public int getXPosition() {
			
			return x;
		}


		public int getYPosition() {
		
			return y;
		}
	
}
