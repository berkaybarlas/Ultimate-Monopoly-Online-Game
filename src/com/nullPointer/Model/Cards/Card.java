package com.nullPointer.Model.Cards;

import com.nullPointer.Model.GameEngine;

public abstract class Card {
	
	private String title;
	private boolean isImmediate;
	
	public Card(String title, boolean isImmediate)
	{
		setTitle(title);
		setImmediate(isImmediate);
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setImmediate(boolean isImmediate)
	{
		this.isImmediate = isImmediate;
	}
	
	public boolean getImmediate()
	{
		return isImmediate;
	}
	
	public abstract void playCard(GameEngine gameEngine);

}
