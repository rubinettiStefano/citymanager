package com.generation.citymanager.model.database;

import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.dao.BodyDAO;
import com.generation.citymanager.model.dao.CitizenDAO;
import com.generation.citymanager.model.dao.CityDAO;
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
	
	public Database(CityDAO cityDAO, BodyDAO bodyDAO, CitizenDAO citizenDAO)
	{
		this.cityDAO = cityDAO;
		this.bodyDAO = bodyDAO;
		this.citizenDAO = citizenDAO;
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
	
	
	public City getCity(String ID)
	{
		for(City c : getCities())
			if(c.ID.equals(ID))
				return c;
		
		return null;
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
	
	public List<Citizen> getCitizens(String namePart)
	{
		List<Citizen> res  = new ArrayList<Citizen>();
		
		for(City city : getCities())
			for(Body b : city.bodies)
				for(Citizen c : b.citizens)
					if((c.name+"-"+c.surname).contains(namePart))
						res.add(c);
		return res;
	}
	
	
	
	/**
	 * 1 - city null, eccezione
	 * 2 - city non valida, eccezione
	 * 3- city già presente, eccezione
	 * 4 - tutto a posto, inseriamo
	 * @param city
	 */
	public void insertCity(City city)
	{
		if(city==null)
			throw new RuntimeException("city is null, cannot insert");
		if(city.ID ==null || city.name == null || city.w <=0 || city.h <=0)
			throw new RuntimeException("city is not valid, cannot insert");
		if(getCity(city.ID)!=null)
			throw new RuntimeException("city is not present, cannot insert");
		
		cityDAO.saveCity(city) 	;
	}

	/**
	 * 1 - city null, eccezione
	 * 2 - city non valida, eccezione
	 * 3- city NON presente, eccezione
	 * 4 - tutto a posto, inseriamo
	 * @param city
	 */
	public void updateCity(City city)
	{
		
		if(city==null)
			throw new RuntimeException("city is null, cannot update");
		if(city.ID ==null || city.name == null || city.w <=0 || city.h <=0)
			throw new RuntimeException("city is not valid, cannot update");
		if(getCity(city.ID)==null)
			throw new RuntimeException("city is not present, cannot update");
		
		
		cityDAO.saveCity(city);
	}

	/**
	 * non posso cancellare qualcosa che non esiste
	 * non posso cancellare una città che ha ancora edifici
	 * @param ID
	 */
	public void deleteCity(String ID)
	{
		if(getCity(ID)==null)
			throw new RuntimeException("city is not present, cannot delete");
		if(getCity(ID).bodies.size()>0)
			throw new RuntimeException("city has at least 1 body, cannot delete");
		
				
		cityDAO.deleteCity(ID);
	}
	
	/**
	 * 1 - body null, eccezione
	 * 2 - body non valida, eccezione
	 * 3- body già presente, eccezione
	 * 4 - tutto a posto, inseriamo
	 * @param city
	 */
	public void insertBody(Body body)
	{
		if(body==null)
			throw new RuntimeException("body is null, cannot insert");
		if(
				body.ID ==null || body.name == null || 
				body.type == null || body.left <=0 	||
				body.bottom <=0 	|| body.right <=0 	||
				body.top <=0 	|| body.cityID == null
		  )
			throw new RuntimeException("body is not valid, cannot insert");
		if(getBody(body.ID)!=null)
			throw new RuntimeException("body is already present, cannot insert");
		
		bodyDAO.saveBody(body);
	}

	private Body getBody(String ID)
	{
		for(City c : getCities())
			for(Body b : c.bodies )
				if(b.ID.equals(ID))
					return b;
		return null;
	}

	public void updateBody(Body body)
	{
		if(body==null)
			throw new RuntimeException("body is null, cannot update");
		if(
				body.ID ==null 		|| body.name == null 	|| 
				body.type == null   || body.left <=0 		||
				body.bottom <=0 	|| body.right <=0 		||
				body.top <=0 		|| body.cityID == null
		  )
			throw new RuntimeException("body is not valid, cannot update");
		if(getBody(body.ID)==null)
			throw new RuntimeException("body is not present, cannot update");
		
		bodyDAO.saveBody(body);
	}

	public void deleteBody(String ID)
	{
		if(getBody(ID)==null)
			throw new RuntimeException("Body is not present, cannot delete");
		if(getBody(ID).citizens.size()>0)
			throw new RuntimeException("Body has at least 1 citizen, cannot delete");
		
		
		bodyDAO.deleteBody(ID);
	}
	
	public void insertCitizen(Citizen citizen)
	{
		if(citizen==null)
			throw new RuntimeException("citizen is null, cannot insert");
		if(
				citizen.name == null || 
				citizen.surname == null ||
				citizen.bodyID == null
		  )
			throw new RuntimeException("citizen is not valid, cannot insert");
		if(getCitizen(citizen.ID)!=null)
			throw new RuntimeException("citizen is already present, cannot insert");
		
		citizenDAO.saveCitizen(citizen);
	}

	public void updateCitizen(Citizen citizen)
	{
		if(citizen==null)
			throw new RuntimeException("citizen is null, cannot update");
		if(
				citizen.name == null || 
				citizen.surname == null ||
				citizen.bodyID == null
		  )
			throw new RuntimeException("citizen is not valid, cannot update");
		if(getCitizen(citizen.ID)==null)
			throw new RuntimeException("citizen is not present, cannot update");
		
		citizenDAO.saveCitizen(citizen);
	}

	public void deleteCitizen(String ID)
	{
		if(getCitizen(ID)==null)
			throw new RuntimeException("citizen is not present, cannot delete");
		
		citizenDAO.deleteCitizen(ID);
	}
	
	private Citizen getCitizen(String ID)
	{
		for(City c : getCities())
			for(Body b : c.bodies )
				for(Citizen ci : b.citizens)
					if(ci.ID.equals(ID))
					return ci;
		return null;
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
