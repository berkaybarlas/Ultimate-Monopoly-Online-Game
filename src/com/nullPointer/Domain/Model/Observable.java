package com.nullPointer.Domain.Model;

import com.nullPointer.UI.Observer;

public interface Observable {
	
	public void subscribe(Observer observer);
	public void publishEvent(String message);
	
}
