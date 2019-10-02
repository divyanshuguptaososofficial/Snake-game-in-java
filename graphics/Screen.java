package snake.graphics;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import snake.entities.BodyPart;
import snake.entities.Food;

public class Screen extends JPanel implements Runnable  {

	public static final int width=800,height=800;
	private Thread thread;
	private boolean running= false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Food food;
	private ArrayList<Food> foods;
	
	private Random r;
	
	private int xCoor =10,yCoor=10;
	private int size=5;
	private boolean right = true,left=false,up=false,down=false;
	private int ticks=0;

	private Key key ;
	
	public Screen() {
		setFocusable(true);
		 key= new Key();
		addKeyListener(key);	
		
		
		setPreferredSize(new Dimension(width,height));
	snake = new ArrayList<BodyPart>();
	foods = new ArrayList<Food>();
	
	r = new Random();
		start();
	}
	
	public void tick() {
		
		if(snake.size()==0) {
			b=new BodyPart(xCoor,yCoor,10);
		snake.add(b);
		}
		
		if(foods.size()==0)
		{
			int xCoor = r.nextInt(79);
			int yCoor= r.nextInt(79);
			food =new Food(xCoor,yCoor,10);
			foods.add(food);
		}
		for(int i=0;i<foods.size();i++)
		{
			if(xCoor==foods.get(i).getxCoor() && yCoor==foods.get(i).getyCoor()) {
				size++;
				foods.remove(i);
				i--;
			}
		}
			for(int i =0;i<snake.size();i++)
			{
			
				if(xCoor==snake.get(i).getxCoor() && yCoor==snake.get(i).getyCoor()) {
					if(i !=snake.size()-1) {
						stop();
					}
				}
			}
			
			if(xCoor<0||xCoor>79||yCoor<0||yCoor>79)
			{
				stop();
			}
			
		
	
		ticks++;
	if(ticks>250000)
	{
		if(right) xCoor++;
			if(left) xCoor--;
				if(up) yCoor--;
					if(down) yCoor++;
					
					ticks=0;
					b=new BodyPart(xCoor,yCoor,10);
					snake.add(b);
					
					if(snake.size()>size)
					{
						snake.remove(0);
					}
	}
	}

	public void paint(Graphics g) {
		g.clearRect(0,0,width,height);
		g.setColor(Color.BLACK);
		
		for(int i =0;i<	width/10;i++)
		{
			g.drawLine(i*10,0, i*10, height);
		}
		for(int i =0;i<	height/10;i++)
		{
			g.drawLine(0,i*10,width, i*10);
		}
		for(int i=0;i<snake.size();i++)
		{
			snake.get(i).draw(g);
		}
		
		for(int i=0;i<foods.size();i++)
		{
			foods.get(i).draw(g);
		}
		
	}
	
	
	public void start() {
		running=true;
		thread = new Thread(this,"Game loop");
		thread.start();
	}

	public void stop() {
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
	
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	private class Key implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_RIGHT && !left)
			{
				up=false;
				down=false;
				right=true;
			}
		
			if(key==KeyEvent.VK_LEFT && !right)
			{
				up=false;
				down=false;
				left=true;
			}
			
			if(key==KeyEvent.VK_UP && !down)
			{
				right=false;
				left=false;
				up=true;
			}
		
			if(key==KeyEvent.VK_DOWN && !up)
			{
				right=false;
				left=false;
				down=true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
}
