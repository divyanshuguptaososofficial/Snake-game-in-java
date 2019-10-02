package snake.entities;

import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {

	private int xCoor,yCoor,WIDTH,HEIGHT;
	
	public BodyPart(int xCoor,int yCoor,int titlesize) {
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		WIDTH=titlesize;
		HEIGHT =titlesize;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xCoor * WIDTH , yCoor * HEIGHT, WIDTH, HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * WIDTH +  2, yCoor * HEIGHT + 2, WIDTH-4, HEIGHT-4);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}
