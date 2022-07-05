package com.generation.citymanager.model.entities;

public class CityManager 
{

	public static void main(String[] args) 
	{
		
		CityDAO cityDAO = new CityDAO("city.csv"); 
		System.out.println("Welcome to city manager, Please pick your city:");
		for(City city: cityDAO.getCities())
			System.out.println(city.ID+" - "+city.name+" - Estensione:  "+city.w+", "+city.h);
		
		
		BodyDAO bodyDAO = new BodyDAO("body.csv"); 
		System.out.println("Welcome to city manager, Please pick your body:");
		for(Body body: bodyDAO.getBodies())
			System.out.println(body.ID+" - "+body.name);
		
		
		

	}

}