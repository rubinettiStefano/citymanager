package com.generation.citymanager.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.entities.Body;
import com.generation.citymanager.model.entities.Citizen;

public class CitizenDAOSQL implements CitizenDAO
{

	Connection connection;

	public CitizenDAOSQL(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public List<Citizen> getCitizens() throws SQLException
	{
		List<Citizen> res = new ArrayList<Citizen>();
		
		Statement readCmd = connection.createStatement();
		String sql = "select * from Citizen";
		ResultSet rows = readCmd.executeQuery(sql);
		
		while(rows.next())					// while(reader.hasNextLine())
		{
			// facendo next ho già letto	   String row = reader.nextLine();
			Citizen citizen = _rowToCitizen(rows);	// City city = _rowToCity(row);
			res.add(citizen);					// res.add(city);
		}
		
		return res;
	}

	private Citizen _rowToCitizen(ResultSet rows) throws SQLException
	{
		return new Citizen
						(
							rows.getString("id"),	
							rows.getString("name"),			
							rows.getString("surname"),			
							rows.getString("bodyId")			

						);
	}

	@Override
	public List<Citizen> getCitizens(String surnamePart)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveCitizen(Citizen citizen)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCitizen(String ID)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Citizen getCitizen(String ID)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
