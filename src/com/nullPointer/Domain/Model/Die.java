package com.nullPointer.Domain.Model;
import java.util.*;

public abstract class Die {
private ArrayList<Integer> lastValues=new ArrayList<Integer>(3);
Random randomGen = new Random();

public void roll(){
}
public void roll(int times){
	lastValues.clear();
	for (int k=0;k<times;k++){
	int  n = randomGen.nextInt(6) + 1;
	lastValues.add(n);
	}
}



public ArrayList<Integer> getLastValues(){
	return lastValues;
}

public void setLastValues(ArrayList<Integer> list) {
    lastValues = list;
}

}
