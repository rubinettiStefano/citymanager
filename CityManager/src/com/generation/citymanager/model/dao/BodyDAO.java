package com.generation.citymanager.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.generation.citymanager.model.entities.Body;

public interface BodyDAO 
{

	List<Body> getBodies() throws SQLException;

	List<Body> getBodies(String namePart);
	
	Body getBody(String ID);
	
	boolean saveBody(Body body);
	
	boolean deleteBody(String ID);
	
	
	
}