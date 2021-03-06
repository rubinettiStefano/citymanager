package com.generation.citymanager.model.dao;

import java.util.List;

import com.generation.citymanager.model.entities.Body;

public interface BodyDAO 
{

	List<Body> getBodies();

	List<Body> getBodies(String namePart);
	
	Body getBody(String ID);
	
	boolean saveBody(Body body);
	
	boolean deleteBody(String ID);
	
	
	
}