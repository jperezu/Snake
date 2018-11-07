package jmp.ekans;

import java.awt.Graphics2D;

public class Ball {
	int x;
	int y;
	int r;
	int xa = 1;
	int ya = 0;
	@SuppressWarnings("unused")
	private ekans game;

	public Ball(ekans game, int x, int y,  int r) {
		this.x = x; this.r = r;
		this.y = y;
		this.game= game;
	}

	void move() {
		if (xa != 0){
			x = x + xa;
		}
		 else if (ya != 0){
			y = y + ya;
		}
		if (x < 0)
			x = 773;
		if (x >773)
			x = 0;
		if (y< 40)
			y = 744;
		if (y> 744)
			y = 40;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXa(){
		return xa;
	}
	public int getYa(){
		return ya;
	}
	
	public int setXa(int x1){
		xa = x1;
		return 0;
	}
	public int setYa(int y1){
		ya = y1;
		return 0;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void paint(Graphics2D g) {
		g.fillOval(x, y, r, r);
	}
}
