package com.nullPointer.Server;

public class MessageBox {
	private static MessageBox _instance;
	
	private MessageBox() {}
	
	public static MessageBox getInstance() {
		if(_instance == null) {
			_instance = new MessageBox();
		}
		return _instance;
	}
}
