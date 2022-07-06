package com.generation.citymanager.controller;

import com.generation.citymanager.model.database.Database;
import com.generation.citymanager.model.entities.City;

public class CityManager {

	public static void main(String[] args) 
	{
		System.out.println("Welcome to city manager");
		
		Database database = new Database("city.csv", "body.csv", "citizen.csv","review.csv");
		
		System.out.println("Pick a city");
		
		for(City city:database.getCities())
			System.out.println
			(
					city.ID+ " - "							+
					city.name+ " n. bodies: "				+
					city.bodies.size()+"n. population: "	+
					city.getPopulation()
					
			);

	}

}
