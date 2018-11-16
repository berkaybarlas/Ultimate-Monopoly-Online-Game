package com.nullPointer.Model;

import java.util.ArrayList;

public class Point {
	private int x;
	private int y;
	private ArrayList<Integer> location = new ArrayList<Integer>(2);
	
	public Point(int x, int y) {
		super();
		this.setX(x);
		this.setY(y);
		location.add(x);
		location.add(y);
		
	}
	public ArrayList<Integer> getLocation() {
		return location;
	}

	public void setLocation(int x, int y) {
		this.setX(x);
		this.setY(y);
		
		location.add(x);
		location.add(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
