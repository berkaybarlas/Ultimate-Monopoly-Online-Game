package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Pawn implements Drawable {
    private Point position;
    private Path myPath;
    private int positionIndex;
    private Player player;
    private Image pawnImage;
    Animator animator = Animator.getInstance();
    ArrayList<Integer> path = new ArrayList<>();
    HashMap<Integer, Point[]> squareMap = Board.getInstance().getSquareMap();

    public Pawn(Point point, Player player, File imFile) {
        this.position = new Point(point.x, point.y);
        this.player = player;
        setupPawnImage(imFile);
        animator.addDrawable(this);

    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setupPawnImage(File imFile) {
        try {
            File imageSource = new File(imFile.getPath());
            this.pawnImage = ImageIO.read(imageSource);
            pawnImage = pawnImage.getScaledInstance(((BufferedImage) pawnImage).getWidth()/8,((BufferedImage) pawnImage).getHeight()/8,Image.SCALE_SMOOTH);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
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

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

    public void paint(Graphics g) {
//        g.setColor(Color.RED);
//        g.fillOval(position.x, position.y, 20, 20);
        g.drawImage(pawnImage,position.x,position.y,null);
    }

    public void draw(Graphics g) {


        if (myPath != null && myPath.hasMoreSteps()) {
            position = myPath.nextPosition();
        } else {
            if (path.size() != 0) {
                int i = 0;
                PlayerController.getInstance().getCurrentPlayer().setPosition(path.get(i));
                int numberOfSteps = (int) (10.0);
                myPath = new StraightLinePath(position.x, position.y
                        , (squareMap.get(path.get(i))[0].x + squareMap.get(path.get(i))[1].x) / 2
                        , (squareMap.get(path.get(i))[0].y + squareMap.get(path.get(i))[1].y) / 2
                        , numberOfSteps);
                position = myPath.nextPosition();

                path.remove(i);
            }
        }
        paint(g);
    }

    public void delete() {
        animator.deleteDrawable(this);
    }
}
