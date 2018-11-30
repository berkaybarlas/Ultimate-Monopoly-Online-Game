package com.nullPointer.UI;

import java.awt.Point;

public interface Path {
	/** 
	 *  Check to see if the path has MoreSteps
	 */
	public boolean hasMoreSteps();

	/** 
	 *  Get the next position.  If the path has no more steps, return
	 *  the current position.
	 */
	public Point nextPosition();
}
