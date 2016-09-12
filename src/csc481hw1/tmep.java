package csc481hw1;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PShape;

public class tmep extends PApplet {
	
	private int windowWidth = 600;
	private int windowHeight = 400;
	
	PShape agentshape;
	
	public static void main(String[] args) {
		PApplet.main("csc481hw1.Section1");
	}
	
	// setup() and draw() should look familiar, and they work exactly the same way as they did in the Processing IDE. The only new concession we have to make is that when we use the size()function to set the size of the screen, we put that in settings(), and we put it first. settings()runs before anything else happens, so we can't use any other Processing functions in it, except to set the size of the screen.
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(0);
		
	}

}