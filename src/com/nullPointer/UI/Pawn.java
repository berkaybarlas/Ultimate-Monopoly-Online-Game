package com.nullPointer.UI;

import java.awt.*;

import com.nullPointer.Domain.Model.Player;

public class Pawn implements Drawable {
    private Point position;
    private Path myPath = new StraightLinePath(410, 410, 10, 10, 100);
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
        /*if (player != null && player.getPosition() != player.getTargetPosition()) {
            if (myPath != null && myPath.hasMoreSteps())
                position = myPath.nextPosition();
            else {
                int numberOfSteps = (int) (10.0 + (Math.random() * 100.0));

                //myPath = new StraightLinePath(410, 410, 10, 10, numberOfSteps);
                position = myPath.nextPosition();
                System.out.println("[Pawn]: Move pawn " + position.x + " " + position.y);
            }
            paint(g);
            //playerController.increaseCurrentPosition(player);
        }*/
        position = new Point(100,100);
        paint(g);
    }
}
