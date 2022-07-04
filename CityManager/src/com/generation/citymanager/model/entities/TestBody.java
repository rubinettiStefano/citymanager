package com.generation.citymanager.model.entities;

public class TestBody
{

	public static void main(String[] args)
	{
		Body casaprof = new Body("Residential",1,3,6,5);
		Body collision = new Body("Collidente",5,6,9,9);
		System.out.println(casaprof.collides(collision));

	}

}
