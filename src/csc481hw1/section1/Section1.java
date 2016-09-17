package csc481hw1.section1;

import processing.core.PApplet;

public class Section1 extends PApplet {
	
	private int windowWidth = 600; // window frame width
	private int windowHeight = 400; // window frame height
	
	// agent is the movable rectangle 
	private float[] agent; // store the agents size and position, in the format of rect()
	private float agentOriginalY; // store the original x position
	private float agentOriginalX; // store the original y position
	
	// foundation is the bottom rectangle forming the floor
	private float[] foundation; // store the agents size and position, in the format of rect()
	
	// floatLine is the thing floating in the air
	private float[] floatRect;

	// define lines that overlap the window frame, used for collision detection
	// store the lines in the form of line()
	private float[] topBoundary;
	private float[] leftBoundary;
	private float[] rightBoundary;		
	
	boolean jumping; // is the agent jumping?
	float jumpingAngle; // what angle to use in the cosine funtion used to jump

	// star the program
	public static void main(String[] args) {
		PApplet.main("csc481hw1.section1.Section1");
	}
	
	public void settings() {
		size(windowWidth, windowHeight); // set the window demensions
	}
	
	public void setup() {
		windowWidth = 600;
		windowHeight = 400;
		
		agent = new float[] {windowWidth * .1f, windowHeight*.9f - 50, 25, 50};
		agentOriginalY = agent[1];
		agentOriginalX = agent[0];
		
		foundation = new float[] {0, windowHeight*.9f, windowWidth, windowHeight*.1f};
		floatRect = new float[] {windowWidth * .7f, windowHeight*.7f, windowWidth * .2f, windowHeight*.025f};
		
		topBoundary = new float[] {0, 0, windowWidth, 0};
		leftBoundary = new float[] {0, 0, 0, windowHeight};
		rightBoundary = new float[] {windowWidth, 0, windowWidth, windowHeight};
		
		jumpingAngle = 180;
		fill(120,50,240);
	}
	
	// wrapper to easily call rect() from just passing an array
	public void drawRect(float[] rect) {
		rect(rect[0], rect[1], rect[2], rect[3]);
	}
	// wrapper to easily call line() from just passing an array	
	public void drawLine(float[] line) {
		line(line[0], line[1], line[2], line[3]);
	}
	// wrapper to easily call fill() from just passing an array	
	public void drawFill(int[] rgb) {
		fill(rgb[0], rgb[1], rgb[2]);
	}
	// wrapper to easily call stroke() from just passing an array	
	public void drawStroke(int[] rgb) {
		stroke(rgb[0], rgb[1], rgb[2]);
	}
	
	// collision detection between two lines
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
	
	// collision detection between a rectangle and rectangle
	// copied from https://github.com/jeffThompson/CollisionDetection
	// RECTANGLE/RECTANGLE
	boolean rectRect(float r1x, float r1y, float r1w, float r1h, float r2x, float r2y, float r2w, float r2h) {
	  
	  // are the sides of one rectangle touching the other?
	  
	  if (r1x + r1w >= r2x &&    // r1 right edge past r2 left
	      r1x <= r2x + r2w &&    // r1 left edge past r2 right
	      r1y + r1h >= r2y &&    // r1 top edge past r2 bottom
	      r1y <= r2y + r2h) {    // r1 bottom edge past r2 top
	        return true;
	  }
	  return false;
	}
	
	// collision detection between a line and rectangle
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
	
	private boolean lineRectWrap(float[] r1, float[] r2) {
		return lineRect(r1[0], r1[1], r1[2], r1[3], r2[0], r2[1], r2[2], r2[3]);
	}
	
	private boolean rectRectWrap(float[] r1, float[] r2) {
		return rectRect(r1[0], r1[1], r1[2], r1[3], r2[0], r2[1], r2[2], r2[3]);
	}
	
	private boolean lineLineWrap(float[] l1, float[] l2) {
		return lineLine(l1[0], l1[1], l1[2], l1[3], l2[0], l2[1], l2[2], l2[3]);
	}
	
	public void draw() {
		background(0); // reset the background each frame
		drawFill(new int[] {221,221,221}); // light gray
		drawRect(foundation);
		drawRect(floatRect);
		drawFill(new int[] {255,255,255}); // white
		drawRect(agent);
		
		// redraw the agent if it's in the process of jumping
		if (jumping) {
			
			// used that colliding circles example from processing.org
			float newY = windowHeight*.9f - 50 + (200 * sin(radians(jumpingAngle)));
			agent[1] = newY; // set a new y position
			jumpingAngle += 1; // increment the jumping angle
			if (jumpingAngle == 360) { // stop jumping if reached the ground
				jumping  = false;
				jumpingAngle = 180;
				agent[1] =agentOriginalY;
			}
		}
		
		
		if (keyPressed) { // move the agent if the key is pressed
			if (keyCode == LEFT) {
				agent[0] -= 5; // move the x position left
			}
			if (keyCode == RIGHT) {
				agent[0] += 5; // move the x position right
			}
			if (key == ' ') { // begin jumping
				if (jumping == false) {
					jumping = true;
				}
			}
			
		}
		
		// check if the agent has collided with the boundaries
		// if it has then reset to its original position
		if (lineRectWrap(leftBoundary,agent) || lineRectWrap(rightBoundary,agent) ||
            rectRectWrap(floatRect, agent)) {
			jumping = false;
			agent[0] = agentOriginalX;
			agent[1] = agentOriginalY;
	  }
	}

}