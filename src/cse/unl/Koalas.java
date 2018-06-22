package cse.unl;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Koalas extends GraphicsProgram{
	public static final int APPLICATION_WIDTH = 470;
	public static final int APPLICATION_HEIGHT = 470;
	
	private MouseMotionListener mml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
//			double x = arg0.getX();
//			if(x > APPLICATION_WIDTH - PADDLE_WIDTH) {
//				x = APPLICATION_WIDTH - PADDLE_WIDTH;
//			}
//			else if(x < 0) {
//				x = 0;
//			}
//			paddle.setLocation(x, APPLICATION_HEIGHT - PADDLE_Y_OFFSET);
			double x = arg0.getX();
			double y = arg0.getY();
			if(gc.getElementAt(x,  y) != null) {
				split(gc.getElementAt(x, y));
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	GCanvas gc = this.getGCanvas();
	GImage pic = new GImage("assets/aham.png", APPLICATION_WIDTH, APPLICATION_HEIGHT);
	int[][] pixels;
	
	public void run() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		gc.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		gc.addMouseMotionListener(mml);
		pixels = pic.getPixelArray();
		GOval start = new GOval(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		start.setFilled(true);
		Color startColor = new Color(getRGBForOval(start)[0], getRGBForOval(start)[1], getRGBForOval(start)[2]);
		start.setFillColor(startColor);
		start.setColor(startColor);
		add(start);
		while(true) {
			pause(1000/30);
		}
	}
	
	public int[] getRGBForOval(GOval obj) {
		int x = (int) obj.getX();
		int y = (int) obj.getY();
		int diameter = (int) obj.getHeight();
		int r, g, b;
		r = this.pic.getRed(pixels[x][y]);
		g = this.pic.getGreen(pixels[x][y]);
		b = this.pic.getBlue(pixels[x][y]);
		for(int i = y + 1; i < y + diameter; i++) {
			for(int j = x + 1; j < x + diameter; j++) {
				r += this.pic.getRed(pixels[i][j]);
				r = r / 2;
				g += this.pic.getGreen(pixels[i][j]);
				g = g / 2;
				b += this.pic.getBlue(pixels[i][j]);
				b = b / 2;
			}
		}
		int[] color = {r, g, b};
		return color;
	}
	
	public void split(GObject obj) {
		double x, y, radius;
		x = obj.getX();
		y = obj.getY();
		radius = obj.getHeight() / 2;
		if(radius < 2) {
			return;
		}
		GOval topLeft, topRight, bottomLeft, bottomRight;
		topLeft = new GOval(x, y, radius, radius);
		topRight = new GOval(x + radius, y, radius, radius);
		bottomLeft = new GOval(x, y + radius, radius, radius);
		bottomRight = new GOval(x + radius, y + radius, radius, radius);
		topLeft.setFilled(true);
		topRight.setFilled(true);
		bottomLeft.setFilled(true);
		bottomRight.setFilled(true);
		Color tR = new Color(getRGBForOval(topRight)[0], getRGBForOval(topRight)[1], getRGBForOval(topRight)[2]);
		topRight.setFillColor(tR);
		topRight.setColor(tR);
		Color tL = new Color(getRGBForOval(topLeft)[0], getRGBForOval(topLeft)[1], getRGBForOval(topLeft)[2]);
		topLeft.setFillColor(tL);
		topLeft.setColor(tL);
		Color bR = new Color(getRGBForOval(bottomRight)[0], getRGBForOval(bottomRight)[1], getRGBForOval(bottomRight)[2]);
		bottomRight.setFillColor(bR);
		bottomRight.setColor(bR);
		Color bL = new Color(getRGBForOval(bottomLeft)[0], getRGBForOval(bottomLeft)[1], getRGBForOval(bottomLeft)[2]);
		bottomLeft.setFillColor(bL);
		bottomLeft.setColor(bL);
		add(topLeft);
		add(topRight);
		add(bottomLeft);
		add(bottomRight);
		remove(obj);
	}

}
