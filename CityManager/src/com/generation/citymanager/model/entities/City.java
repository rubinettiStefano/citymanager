package com.generation.citymanager.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/> 
 * <br/> 
 * <br/> 
 * <img src="https://c.tenor.com/_BiwWBWhYucAAAAd/what-huh.gif" />
 * <button>Premimi</button>
 * <input type="text"></input>
 * @author rubin
 *
 */
public class City extends Entity
{
	String name;
	int w,h;
	List<Body> bodies = new ArrayList<Body>();
	
	public City(String ID, String  name, int w, int h)
	{
		this.ID = ID;
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
		
		for(Body oldBody : bodies)
			if(oldBody.collides(newBody))
				return false;
		
		
		bodies.add(newBody);
		newBody.cityID = this.ID;
		newBody.city = this;
		return true;
	}
}
