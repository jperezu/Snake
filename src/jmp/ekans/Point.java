package jmp.ekans;

import java.awt.Graphics2D;

public class Point {

	@SuppressWarnings("unused")
	private ekans game;
	int x;
	int y;
	int r;
	public Point(ekans game, int x, int y,  int r) {
		this.x = x; 
		this.r = r;
		this.y = y;
		this.game= game;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void paint(Graphics2D g) {
		g.fillOval(x, y, r, r);
	}
}
