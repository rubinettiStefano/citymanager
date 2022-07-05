package com.generation.citymanager.model.entities;

import java.util.List;

public class Database
{
	CityDAO cityDAO;
	BodyDAO bodyDAO;
	
	public Database(String cityFile, String bodyFile)
	{
		cityDAO = new CityDAO(cityFile);
		bodyDAO = new BodyDAO(bodyFile);
	}
	
	public List<City> getCities()
	{
		List<City>cities = cityDAO.getCities();
		List<Body>bodies = bodyDAO.getBodies();
		
		for(City city:cities)
			for(Body body:bodies)
				if(body.cityID.equals(city.ID))
					city.addBody(body);
		
		return cities;
	}
	
	
}
