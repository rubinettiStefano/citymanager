package com.generation.citymanager.model.entities;

public class Segment 
{
	int a,b;
	
	public Segment(int a, int b)
	{
		this.a = a;
		this.b = b;
	}
	 
	public String toString()
	{
		return "From "+a +" to "+b;
	}
	
	public int getLength()
	{
		return b-a;
	}
	
	public boolean collides(Segment other)
	{
		return 	(other.a > this.a 	&& other.a < this.b ) || //other inizia in this
				(this.a  > other.a 	&& this.a  < other.b) || //this  inizia in other
				this.a==other.a || this.b==other.b		   ; //iniziano o finiscono nello stesso punto
	}
	
	
}
