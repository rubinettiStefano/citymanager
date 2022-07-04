package com.generation.citymanager.model.entities;

import java.util.ArrayList;
import java.util.List;

public class City
{
	String name;
	int w,h;
	List<Body> bodies = new ArrayList<Body>();
	
	public City(String  name, int w, int h)
	{
		this.name = name;
		this.w = w;
		this.h = h;
	}
	
	public boolean addBody(Body newBody)
	{
		if
		(
			newBody.bottom  < 0 	 ||
			newBody.top 	< 0 	 ||
			newBody.left	< 0 	 ||
			newBody.right   < 0 	 ||
			newBody.right   > this.w ||
			newBody.top     > this.h
		)
			return false;
		
		for(Body b : bodies)
			if(b.collides(newBody))
				return false;
		
		return bodies.add(newBody);
	}
}
