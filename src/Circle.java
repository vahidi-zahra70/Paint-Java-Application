
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends MyShape{
	
	public Circle (int x1,int y1,int x2,int y2,String color,String user){
		super(x1,y1,x2,y2,color,user);
		
	}
	
	public Circle() {
		// TODO Auto-generated constructor stub
	}

	public  void draw(Graphics g){
		
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int width = Math.abs(x1- x2);
		int height = Math.abs(y1- y2);
		
		if(color.equals("Blue")){
			g.setColor(Color.BLUE);
		}

		else if(color.equals("Black")){
			g.setColor(Color.BLACK);
		}
		else if(color.equals("Red")){
			g.setColor(Color.RED);
			
		}
		else if(color.equals("Green")){
			g.setColor(Color.GREEN);
		}
		
		g.drawOval(x,y,width,height);
	}

}