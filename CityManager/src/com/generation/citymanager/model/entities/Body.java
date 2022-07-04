package com.generation.citymanager.model.entities;

public class Body
{
	String type;
	int left,bottom, right,top;
	Segment horizontal;
	Segment vertical;
	
	public Body(String type, int left, int bottom, int right, int top)
	{
		this.type 	= type;
		this.left 	= left;
		this.bottom = bottom;
		this.right	= right;
		this.top 	= top;
		horizontal  = new Segment(left,right);
		vertical	= new Segment(bottom,top);
	}
	
	public String toString()
	{
		return type + " located at ("+left+","+bottom+")-"+"("+right+","+top+")";
	}
	
	public int getArea()
	{
		return horizontal.getLength()*vertical.getLength();
	}
	
	public boolean collides(Body other)
	{
		return this.horizontal.collides(other.horizontal) && this.vertical.collides(other.vertical);
	}
	
	
	
}
