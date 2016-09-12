package csc481hw1;

import processing.core.PApplet;

public class Section1 extends PApplet {
	
	private int windowWidth = 600;
	private int windowHeight = 400;
	
	private float[] agent = {windowWidth * .1f, windowHeight*.9f - 50, 25, 50};
	private float agentOriginalY = agent[1];
	private float agentOriginalX = agent[0];
	private float[] foundation = {0, windowHeight*.9f, windowWidth, windowHeight*.1f};

	private float[] topBoundary = {0, 0, windowWidth, 0};
	private float[] leftBoundary = {0, 0, 0, windowHeight};
	private float[] rightBoundary = {windowWidth, 0, windowWidth, windowHeight};		
	
	boolean jumping = false;
	float jumpingAngle = 180;

	public static void main(String[] args) {
		PApplet.main("csc481hw1.Section1");
	}
	
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	public void setup() {
		fill(120,50,240);
	}
	
	public void drawRect(float[] rect) {
		rect(rect[0], rect[1], rect[2], rect[3]);
	}
	
	public void drawLine(float[] line) {
		line(line[0], line[1], line[2], line[3]);
	}
	
	public void drawFill(int[] rgb) {
		fill(rgb[0], rgb[1], rgb[2]);
	}
	
	public void drawStroke(int[] rgb) {
		stroke(rgb[0], rgb[1], rgb[2]);
	}
	
	
	// copied from https://github.com/jeffThompson/CollisionDetection
	// LINE/LINE
	boolean lineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {

	  // calculate the direction of the lines
	  float uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
	  float uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));

	  // if uA and uB are between 0-1, lines are colliding
	  if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {

	    // optionally, draw a circle where the lines meet
	    float intersectionX = x1 + (uA * (x2-x1));
	    float intersectionY = y1 + (uA * (y2-y1));
	    fill(255,0,0);
	    noStroke();
	    ellipse(intersectionX, intersectionY, 20, 20);

	    return true;
	  }
	  return false;
	}
	
	// copied from https://github.com/jeffThompson/CollisionDetection
	// LINE/RECTANGLE
	boolean lineRect(float x1, float y1, float x2, float y2, float rx, float ry, float rw, float rh) {

	  // check if the line has hit any of the rectangle's sides
	  // uses the Line/Line function below
	  boolean left =   lineLine(x1,y1,x2,y2, rx,ry,rx, ry+rh);
	  boolean right =  lineLine(x1,y1,x2,y2, rx+rw,ry, rx+rw,ry+rh);
	  boolean top =    lineLine(x1,y1,x2,y2, rx,ry, rx+rw,ry);
	  boolean bottom = lineLine(x1,y1,x2,y2, rx,ry+rh, rx+rw,ry+rh);

	  // if ANY of the above are true, the line 
	  // has hit the rectangle
	  if (left || right || top || bottom) {
	    return true;
	  }
	  return false;
	}
	
	public void draw() {
		background(0);
		drawFill(new int[] {221,221,221}); // light gray
		drawRect(foundation);
		drawFill(new int[] {255,255,255}); // white
		drawRect(agent);
		
		if (jumping) {
			float newY = windowHeight*.9f - 50 + (200 * sin(radians(jumpingAngle)));
			agent[1] = newY;
			jumpingAngle += 1;
			if (jumpingAngle == 360) {
				jumping  = false;
				jumpingAngle = 180;
				agent[1] =agentOriginalY;
			}
		}
		
		
		if (keyPressed) {
			if (keyCode == LEFT) {
				agent[0] -= 5;
			}
			if (keyCode == RIGHT) {
				agent[0] += 5;
			}
			if (key == ' ') {
				if (jumping == false) {
					jumping = true;
				}
			}
			
			System.out.println(lineRect(
				     leftBoundary[0], leftBoundary[1], leftBoundary[2], leftBoundary[3], 
				     agent[0], agent[1], agent[2], agent[3]
				    ));
		}
		if (lineRect(
			     leftBoundary[0], leftBoundary[1], leftBoundary[2], leftBoundary[3], 
			     agent[0], agent[1], agent[2], agent[3]
			    )
		||
	    lineRect(
	    		 rightBoundary[0], rightBoundary[1], rightBoundary[2], rightBoundary[3], 
			     agent[0], agent[1], agent[2], agent[3]
			    )
	    ) {
			jumping = false;
			agent[0] = agentOriginalX;
			agent[1] = agentOriginalY;
			
	  }
	}

}