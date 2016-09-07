package csc481hw1;

import processing.core.PApplet;

public class Section1 extends PApplet {

	public static void main(String[] args) {
		PApplet.main("csc481hw1.Section1");
	}
	
	// setup() and draw() should look familiar, and they work exactly the same way as they did in the Processing IDE. The only new concession we have to make is that when we use the size()function to set the size of the screen, we put that in settings(), and we put it first. settings()runs before anything else happens, so we can't use any other Processing functions in it, except to set the size of the screen.
	public void settings() {
		size(300,300);
	}
	
	public void setup() {
		fill(120,50,240);
	}
	
	public void draw() {
		ellipse(width/2,height/2,second(),second());
	}

}