package com.generation.citymanager.controller.api;

import java.sql.SQLException;

import com.generation.citymanager.model.database.Database;
import com.generation.citymanager.model.database.DatabaseFactory;
import com.generation.citymanager.model.entities.City;

public class SeeAllCities
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Database database = DatabaseFactory.make("city.db");
		
		for(City city : database.getCities())
			System.out.println(city.name + " w:" + city.w + " h:" + city.h + " n.bodies: "+city.bodies.size()+" population:"+city.getPopulation());
	}
}
