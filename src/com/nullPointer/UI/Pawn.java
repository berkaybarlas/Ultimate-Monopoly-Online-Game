package com.nullPointer.UI;

import java.awt.*;

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
    	System.out.println(position.getX()+"   "+position.getY());
        if (player != null && player.getPosition() != player.getTargetPosition()) {
            if (myPath != null && myPath.hasMoreSteps()){
            	System.out.println("Path is not null");
                position = myPath.nextPosition();
                paint(g);
            }
            else {
            	PlayerController.getInstance().increaseCurrentPosition(player);
                int numberOfSteps = (int) (10.0 + (Math.random() * 100.0));

                myPath = new StraightLinePath(position.x, position.y, 10, 10, numberOfSteps);
                position = myPath.nextPosition();
                paint(g);
                System.out.println("[Pawn]: Move pawn " + position.x + " " + position.y);
            }
        }
    }
}
