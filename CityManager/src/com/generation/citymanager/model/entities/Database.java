package com.generation.citymanager.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Database servira a fare tutte le LETTURE e le SCRITTURE su DB
 * @author Lorenzo
 *
 */
public class Database 
{
	CityDAO cityDAO;
	BodyDAO bodyDAO;
	CitizenDAO citizenDAO;
	ReviewDAO reviewDAO;
	
	public Database(String cityFile, String bodyFile, String citizenFile, String reviewFile)
	{
		cityDAO = new CityDAO(cityFile);
		bodyDAO = new BodyDAO(bodyFile);
		citizenDAO = new CitizenDAO(citizenFile);
		reviewDAO = new ReviewDAO(reviewFile);
	}
	
	public List<City> getCities()
	{
		List<City> cities = cityDAO.getCities();
		List<Body> bodies = bodyDAO.getBodies();
		List<Citizen> citizens = citizenDAO.getCitizens();
		List<Review> reviews = new ArrayList<Review>();
		
		for(City city:cities)
			for(Body body:bodies)
				if(city.ID.equals(body.cityID))
					city.addBody(body);
		
		// prendo tutti i body
		// li confronto con tutti i citizen
		// e decido quali collegare. stesso lavoro di prima
		for(Body body: bodies)
			for(Citizen citizen: citizens)
			if(body.ID.equals(citizen.bodyID))
				body.addCitizen(citizen);
		
		
		for(Body body: bodies)
			for(Review review: reviews)
			if(body.ID.equals(review.bodyID))
				body.addReview(review);
		
		return cities;		
	}
	
	public List<Body> getBodies(String cityname, String activity)
	{
		City selected = null;
		for(City city:getCities())
			if(city.name.equalsIgnoreCase(cityname))
			{
				selected = city;
				break;
			}
		
		if(selected==null)
			return new ArrayList<Body>();
		
		List<Body> res = new ArrayList<Body>();
		// SCORRO GLI EDIFICI DELLA CITTA' TROVATA
		for(Body body:selected.bodies) // name like %activity% OR type like %activity%
			if(body.name.contains(activity) || body.type.contains(activity))
				res.add(body);
		
		return res;
		
	}
	
	
}
