package com.nullPointer.UI;
import java.awt.*;
import java.util.*;
import javax.swing.JPanel;

public class Animator extends JPanel implements Runnable{

	Vector elementsToDraw = new Vector();
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Program Interrupted");
				System.exit(0);
			}
			repaint();
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		Enumeration e = elementsToDraw.elements();
		while (e.hasMoreElements())
			((Drawable) e.nextElement()).draw(g);
	}
	public void addDrawable(Drawable d) {
		elementsToDraw.addElement(d);
	}
	public void removeDrawable(Drawable d) {
		elementsToDraw.removeElement(d);
	}

}
