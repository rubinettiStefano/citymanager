package com.generation.citymanager.model.database;

import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.dao.BodyDAO;
import com.generation.citymanager.model.dao.CitizenDAO;
import com.generation.citymanager.model.dao.CityDAO;
import com.generation.citymanager.model.dao.CityDAOCSV;
import com.generation.citymanager.model.entities.Body;
import com.generation.citymanager.model.entities.Citizen;
import com.generation.citymanager.model.entities.City;

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
	
	public Database(String cityFile, String bodyFile, String citizenFile, String reviewFile)
	{
		cityDAO = new CityDAOCSV(cityFile);
		bodyDAO = new BodyDAO(bodyFile);
		citizenDAO = new CitizenDAO(citizenFile);
	}
	
	public List<City> getCities()
	{
		List<City> cities = cityDAO.getCities();
		List<Body> bodies = bodyDAO.getBodies();
		List<Citizen> citizens = citizenDAO.getCitizens();
		
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
		
		
//		for(Body body: bodies)
//			for(Review review: reviews)
//			if(body.ID.equals(review.bodyID))
//				body.addReview(review);
		
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
	
	public List<Citizen> getCitizen(String namePart)
	{
		List<Citizen> res  = new ArrayList<Citizen>();
		
		for(City city : getCities())
			for(Body b : city.bodies)
				for(Citizen c : b.citizens)
					if((c.name+"-"+c.surname).contains(namePart))
						res.add(c);
		return res;
	}
	
//	public List<Review> getReviews(String word)
//	{
//		List<Review> res = new ArrayList<Review>();
//		
//		for(City city : getCities())
//			for(Body body : city.bodies)
//				for(Review review : body.reviews)
//					if((review.title + " " + review.text).contains(word))
//				//  if(review.title.contains(word) && review.text.contains(word))  uguale a sopra
//						res.add(review);
//		return res;
//	}

}
