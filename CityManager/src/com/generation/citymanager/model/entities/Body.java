package com.generation.citymanager.model.entities;

/**
 * Un Body è qualunque cosa definita GEOGRAFICAMENTE all'interno di una città. <br/>
 * Essendo una Entity eredità l'ID
 * @author Stefano
 *
 */
public class Body extends Entity
{
	String type,name;
	int left,bottom, right,top;
	Segment horizontal;
	Segment vertical;
	String cityID;
	City city;
	
	public Body(String ID, String type, int left, int bottom, int right, int top)
	{
		this.ID = ID;
		this.type 	= type;
		this.left 	= left;
		this.bottom = bottom;
		this.right	= right;
		this.top 	= top;
		horizontal  = new Segment(left,right);
		vertical	= new Segment(bottom,top);
	}
	
	public Body(String ID,  String type,String name, int left, int bottom, int right, int top, String cityID)
	{
		this.ID = ID;
		this.type 	= type;
		this.name = name;
		this.left 	= left;
		this.bottom = bottom;
		this.right	= right;
		this.top 	= top;
		horizontal  = new Segment(left,right);
		vertical	= new Segment(bottom,top);
		this.cityID = cityID;
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
