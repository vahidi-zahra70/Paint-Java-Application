import java.awt.*;


public abstract class MyShape{
	
	String color;
	String user;
	int x1;
	int y1;
	int x2;
	int y2;
	
	MyShape(int x1,int y1,int x2,int y2,String color,String user){
		
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.color=color;
		this.user=user;
		

	}
	public MyShape() {
		// TODO Auto-generated constructor stub
	}
	public abstract void draw(Graphics g);
	
}