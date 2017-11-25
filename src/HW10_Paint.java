import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class HW10_Paint extends JFrame {
	
	static Connection conn2=null;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	JButton LineBut, CircleBut, RecBut,ExistBut;
	JLabel label ;
	JRadioButton radioButton, radioButton_1, radioButton_2 ,rdbtnNewRadioButton;
	int currentAction=1;
	HW10_validation hw10_validation;
	

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			HW10_Paint frame = new  HW10_Paint();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public  HW10_Paint() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		super.getContentPane().setBackground(Color.white);
		this.setSize(500, 500);
		this.setTitle("Paint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JPanel buttonPanel = new JPanel();
		
		LineBut=makeMeButtons("\u062E\u0637", 2);
		LineBut.setBounds(335, 25, 89, 23);
		this.add(LineBut);



		CircleBut=makeMeButtons("\u062F\u0627\u06CC\u0631\u0647", 3);
		CircleBut.setBounds(335, 59, 89, 23);
		this.add(CircleBut);


		RecBut=makeMeButtons("\u0645\u0633\u062A\u0637\u06CC\u0644", 4);
		RecBut.setBounds(335, 93, 89, 23);
		this.add( RecBut);

		ExistBut = new JButton("\u062E\u0631\u0648\u062C");
		ExistBut.setBounds(335, 290, 89, 23);
		this.add(ExistBut);
		
		//Defining the action for exist button
		ExistBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try{
					HW10_validation hw10_validation=new HW10_validation();
					hw10_validation.setVisible(true);
				}
				catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});



		label = new JLabel("\u0627\u0646\u062A\u062E\u0627\u0628 \u0631\u0646\u06AF");
		label.setBounds(335, 140, 89, 23);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(label);
		

		radioButton = new JRadioButton("\u0645\u0634\u06A9\u06CC");
		buttonGroup.add(radioButton);
		radioButton.setBounds(348, 168, 109, 23);
		this.add(radioButton);

		radioButton_1 = new JRadioButton("\u0642\u0631\u0645\u0632");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(348, 189, 109, 23);
		this.add(radioButton_1);

		radioButton_2 = new JRadioButton("\u0633\u0628\u0632");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(348, 210, 109, 23);
		this.add(radioButton_2);

		rdbtnNewRadioButton = new JRadioButton("\u0622\u0628\u06CC");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(348, 231, 109, 23);
		this.add(rdbtnNewRadioButton);

		this.add(buttonPanel, BorderLayout.EAST);
		this.add(new DrawingBoard());

		this.setVisible(true);
		
		DBManager 	cnn=new DBManager();
		try {
			conn2=cnn.getconn();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}


	public JButton makeMeButtons(String iconFile, final int actionNum){
		JButton theBut = new JButton(iconFile);
		Icon butIcon = new ImageIcon(iconFile);
		theBut.setIcon(butIcon);

		//Recognizing the action for each select
		theBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentAction = actionNum;
			}
		});

		return theBut;
	}




	private class DrawingBoard 	extends JComponent 	{

		String color;
		Point drawStart, drawEnd;
		String user=HW10_validation.username;
		MyShape[] shapes;
		MyShape[] shapes2;

		{
			//Showing the past shapes for each user
			Shape_manager MM;
			MM=new Shape_manager();

			try {
				DBManager 	cnn=new DBManager();
				try {
					conn2=cnn.getconn();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				shapes=MM.showPreShapes(conn2,user);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



		public DrawingBoard() {

			this.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {

					
					if (currentAction != 1) {
						drawStart = new Point(e.getX(), e.getY());
						drawEnd = drawStart;
						repaint();

					}
				}

				public void mouseReleased(MouseEvent e) {

					if (currentAction != 1) {

						MyShape shape=null;
						
						//Line
						if (currentAction == 2 ) {
							
							//recognizing the selected color
							drawEnd = new Point(e.getX(), e.getY());
							if (rdbtnNewRadioButton.isSelected()) {
								color="Blue";}
							else if(radioButton.isSelected()){
								color="Black";}

							else if(radioButton_1.isSelected()){
								color="Red";}

							else if(radioButton_2.isSelected()){
								color="Green";}
							else color="Black";

							//making a new shape for each user
							shape=new Line(drawStart.x, drawStart.y,  drawEnd.x, drawEnd.y,color,user);
							
							//adding this shape to  the other shapes for each user
							addShape(shape);

							//adding this shape to  the database
							Shape_manager SS=new Shape_manager();
							try {
								SS.insertLine(shape, conn2);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						//Circle
						else if (currentAction == 3) {

							drawEnd = new Point(e.getX(), e.getY());

							//recognizing the selected color
							if (rdbtnNewRadioButton.isSelected()) {
								color="Blue";}
							else if(radioButton.isSelected()){
								color="Black";}

							else if(radioButton_1.isSelected()){
								color="Red";}

							else if(radioButton_2.isSelected()){
								color="Green";}
							else color="Black";

							//making a new shape for each user
							shape=new Circle(drawStart.x, drawStart.y,  drawEnd.x, drawEnd.y,color,user);
							
							//adding this shape to  the other shapes for each user
							addShape(shape);

							//adding this shape to  the database
							Shape_manager SS=new Shape_manager();
							try {
								SS.insertCircle(shape, conn2);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						//Rectangle
						else if (currentAction == 4) {

							drawEnd = new Point(e.getX(), e.getY());
							
							//recognizing the selected color
							if (rdbtnNewRadioButton.isSelected()) {
								color="Blue";}
							else if(radioButton.isSelected()){
								color="Black";}

							else if(radioButton_1.isSelected()){
								color="Red";}

							else if(radioButton_2.isSelected()){
								color="Green";}
							else color="Black";
							
							//making a new shape for each user
							shape=new Rectangle(drawStart.x, drawStart.y,  drawEnd.x, drawEnd.y,color,user);
							
							//adding this shape to  the other shapes for each user
							addShape(shape);

							//adding this shape to  the database
							Shape_manager SS=new Shape_manager();
							try {
								SS.insertRectangle(shape, conn2);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						
     					drawStart = null;
						drawEnd = null;
						repaint();
					}

				}
			});


		}

		//The function for adding shapes
		public void addShape(MyShape shape){
			if(shapes!=null){
				shapes2=new MyShape[shapes.length+1];

				for(int i=0;i<shapes.length;i++){
					shapes2[i]=shapes[i];
				}
			}
			else shapes2=new MyShape[1];
			shapes2[shapes2.length-1]=shape;


			shapes=new MyShape[shapes2.length];
			for(int i=0;i<shapes.length;i++){

				shapes[i]=shapes2[i];
			}

		}
		
		//The function for drawing shapes
		public void paint (Graphics g){
			super.paint(g);
			if(shapes!=null){
				for (int i=0;i<shapes.length;i++){
					shapes[i].draw(g);
				}
			}
		}

	}

}



