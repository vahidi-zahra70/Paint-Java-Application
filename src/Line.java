import java.awt.*;

public class Line extends MyShape {
	
	public Line(int x1,int y1,int x2,int y2,String color,String user){
		super(x1,y1,x2,y2,color,user);
		
	}
	public Line() {
		// TODO Auto-generated constructor stub
	}
	public  void draw(Graphics g){
		
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
		
		
		g.drawLine(x1,y1,x2,y2);
	}

}
