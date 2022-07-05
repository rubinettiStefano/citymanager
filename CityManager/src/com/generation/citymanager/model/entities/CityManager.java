package com.generation.citymanager.model.entities;

public class CityManager 
{

	public static void main(String[] args) 
	{
		
		Database database = new Database("city.csv","body.csv");
		
		System.out.println("Welcome to city manager, Please pick your city:");
		for(City city: database.getCities())
			System.out.println
			(
							city.ID+" - "+
							city.name+" - "+ 
							" n. bodies:  "+ city.bodies.size()
			);
		
	}

}