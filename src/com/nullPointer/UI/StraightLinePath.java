package com.nullPointer.UI;

import java.awt.Point;

public class StraightLinePath implements Path{
	 int startX, startY, endX, endY, steps;
	    int currentStep = -1;      // This makes the first step 0
	    double deltaX, deltaY;

	    /** Constructor Stores the points, and builds the information
	     *  needed to construct the next point.  Note that for a path we
	     *  need the initial point for the path (X1, Y1), the final point
	     *  for the path (X2, Y2), and the number of steps in the path (
	     *  numSteps).
	     */
	    public StraightLinePath(int X1, int Y1, int X2, int Y2, int numSteps) {
	        startX = X1;
	        startY = Y1;
	        endX = X2;
	        endY = Y2;
	        steps = numSteps;
	        deltaX = ((double)(X2 - X1)) / steps;
	        deltaY = ((double)(Y2 - Y1)) / steps;
	    }

	    /** 
	     *  Check to see if the path has MoreSteps
	     */
	    public boolean hasMoreSteps() {
	        if (currentStep > steps)
	            return false;
	        return true;
	    }

	    /** 
	     *  Get the next position.  If the path has no more steps, return
	     *  the current position.
	     */
	    public Point nextPosition() {
	        currentStep++;
	        if (currentStep > steps)
	            return new Point(endX, endY);
	        return new Point((int)(startX + (deltaX * currentStep)),
	                (int)(startY + (deltaY * currentStep)));
	    }
	}
