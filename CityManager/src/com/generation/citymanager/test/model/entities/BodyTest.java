package com.generation.citymanager.test.model.entities;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.generation.citymanager.model.entities.Body;

class BodyTest
{
	
	@Test
	void testGetArea()
	{
		Body body = new Body("Residential",10,10,20,20);
		assert(body.getArea()==100);
	}

	@Test
	void testCollides()
	{
		Body bodyA = new Body("Residential",10,10,20,20);
		Body bodyB = new Body("Residential",5,5,11,11);
		assert(bodyA.collides(bodyB));
		assert(bodyB.collides(bodyA));
		
		Body bodyC = new Body("Residential",1,1,10,10);
		Body bodyD = new Body("Residential",20,20,30,30);
		assert(!bodyC.collides(bodyD));
		assert(!bodyD.collides(bodyC));
		
	}

}
