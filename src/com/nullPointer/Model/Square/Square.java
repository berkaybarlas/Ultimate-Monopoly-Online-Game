package com.nullPointer.Model.Square;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public abstract class Square {
	private String name;
	private String type;
	private int position;
	private int layer;
	
	public Square(String name, String type, int position, int layer){
		this.name = name;
		this.type = type;
		this.position = position;
		this.layer = layer;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public abstract void evaluateSquare(GameEngine gameEngine);
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
}
