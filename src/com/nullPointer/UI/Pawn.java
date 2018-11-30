package com.nullPointer.UI;

import java.awt.*;

import com.nullPointer.Domain.Model.Player;

public class Pawn implements Drawable{
	private Point position;
	 private Path myPath;
	private int positionIndex;
	private Player player;
	public Pawn(Point point, Player player) {
		this.position = new Point(point.x, point.y);
		this.player = player;
		
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void changeX(int x) {
		position.x += x;
	}

	public void changeY(int y) {
		position.y += y;
	}

	public int getPositionIndex() {
		return positionIndex;
	}

	public void setPositionIndex(int positionIndex) {
		this.positionIndex = positionIndex;
	}

	public void paint(Graphics g) {
		g.fillOval(position.x, position.y , 20, 20);
	}
	public void move(Point start, Point end) {
		/*int fraction=100;
		int diffX=(int) (end.getX()-start.getX());
		int diffY=(int) (end.getY()-start.getY());
		int fracDiffX = diffX/fraction;
		int fracDiffY = diffY/fraction;
		for(int i=0;i<fraction;i++){
			position.move((int)start.getX()+fracDiffX, (int)start.getY()+fracDiffY);
			
		}*/
		
	}

	public void draw(Graphics g) {
        if (myPath != null && myPath.hasMoreSteps())
            position = myPath.nextPosition();
        else {
            // Get a random number of steps to make the balls move
            // at different speeds.  Note there has to be at least
            // 1 step in each path, but for appearances we used at least
            // 10 steps.
            int numberOfSteps = (int) (10.0 + (Math.random() * 100.0));

            if (direction == DOWN) {
                myPath = new StraightLinePath(410, 410, 10, 10, numberOfSteps);
                myPosition = myPath.nextPosition();
                direction = UP;
            }
            else {
                myPath = new StraightLinePath(10, 10, 410, 410, numberOfSteps);
                myPosition = myPath.nextPosition();
                direction = DOWN;
            }
        }
        g.setColor(Color.YELLOW);
        g.fillRect((int)position.getX(), (int)position.getY(), 15, 15);
        g.setColor(Color.BLACK);
       
    }
}
