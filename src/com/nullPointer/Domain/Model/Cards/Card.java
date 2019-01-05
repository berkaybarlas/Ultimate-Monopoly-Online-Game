package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;

import java.io.Serializable;

public abstract class Card implements iCard, Serializable {
	
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
