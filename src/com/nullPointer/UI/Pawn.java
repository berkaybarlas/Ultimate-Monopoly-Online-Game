package com.nullPointer.UI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Player;

public class Pawn implements Drawable {
	private Point position;
	private Path myPath;
	private int positionIndex;
	private Player player;
	Animator animator = Animator.getInstance();

	public Pawn(Point point, Player player) {
		this.position = new Point(point.x, point.y);
		this.player = player;
		animator.addDrawable(this);

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
		g.fillOval(position.x, position.y, 20, 20);
		g.setColor(Color.RED);
	}

	public void draw(Graphics g) {
		ArrayList<Integer> path=Board.getInstance().getCurrentPath();
		HashMap<Integer, Point[]> squareMap=Board.getInstance().getSquareMap();
		if (player != null && player.getPosition() != player.getTargetPosition()) {
			if (myPath != null && myPath.hasMoreSteps()){
				position = myPath.nextPosition();               
			}
			else {
				for(int i=0;i<path.size();i++){
					PlayerController.getInstance().increaseCurrentPosition(player);
					int numberOfSteps = (int) (10.0 + (Math.random() * 100.0));
					myPath = new StraightLinePath(position.x, position.y 
							,(squareMap.get(path.get(i))[0].x+squareMap.get(path.get(i))[1].x)/2
							,(squareMap.get(path.get(i))[0].y+squareMap.get(path.get(i))[1].y)/2
							,numberOfSteps);
					position = myPath.nextPosition();
				}
			}
			paint(g);
		}
	}
}
