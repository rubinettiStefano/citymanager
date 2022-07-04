package com.generation.citymanager.model.entities;

public class TestSegments
{

	public static void main(String[] args) 
	{
		Segment s1,s2;
		
		s1 = new Segment(1,6);
		s2 = new Segment(6,9);
		
		System.out.println(s1.collides(s2));
		
	}

}
