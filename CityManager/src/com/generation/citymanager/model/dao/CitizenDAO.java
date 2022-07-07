package com.generation.citymanager.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.generation.citymanager.model.entities.Citizen;

public interface CitizenDAO 
{

	List<Citizen> getCitizens() throws SQLException;

	List<Citizen> getCitizens(String surnamePart);
	
	boolean saveCitizen(Citizen citizen);
	
	boolean deleteCitizen(String ID);
	
	Citizen getCitizen(String ID);
	
	
}