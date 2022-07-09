package com.generation.citymanager.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.entities.Citizen;

public class CitizenDAOSQL implements CitizenDAO
{

	Connection connection;

	public CitizenDAOSQL(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public List<Citizen> getCitizens() 
	{
		List<Citizen> res = new ArrayList<Citizen>();
		
		try
		{
    		Statement readCmd = connection.createStatement();
    		String sql = "select * from Citizen";
    		ResultSet rows = readCmd.executeQuery(sql);
    		
    		while(rows.next())					// while(reader.hasNextLine())
    		{
    			// facendo next ho già letto	   String row = reader.nextLine();
    			Citizen citizen = _rowToCitizen(rows);	// City city = _rowToCity(row);
    			res.add(citizen);					// res.add(city);
    		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
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
		try
		{
    		if(getCitizen(citizen.ID)==null)
    		{
    			// INSERT
    			Statement writeCmd = connection.createStatement();
    			// soluzione poco carina... ma va per ora.
    			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    			String sql = 
    					"insert into Citizen (id,name,surname,bodyID) values('[id]','[name]','[surname]','[bodyID]')";
    			
    			sql = sql.replace("[id]", 	citizen.ID		);
    			sql = sql.replace("[name]", citizen.name	);
    			sql = sql.replace("[surname]", 	citizen.surname+""	);
    			sql = sql.replace("[bodyID]", 	citizen.bodyID+""	);
    			writeCmd.execute(sql);
    		}
    		else
    		{
    			Statement writeCmd = connection.createStatement();
    			// soluzione poco carina... ma va per ora.
    			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    			String sql = 
    					"UPDATE Citizen set name = '[name]', surname='[surname]',bodyID='[bodyID]' where id='[id]'";
    			
    			sql = sql.replace("[id]", 	citizen.ID		);
    			sql = sql.replace("[name]", citizen.name	);
    			sql = sql.replace("[surname]", 	citizen.surname+""	);
    			sql = sql.replace("[bodyID]", 	citizen.bodyID+""	);
    			writeCmd.execute(sql);
    		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteCitizen(String ID)
	{
		try
		{
    		Statement writeCmd = connection.createStatement();
    		// soluzione poco carina... ma va per ora.
    		// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    		String sql = 
    				"Delete from Citizen where id= '"+ID+"'";
    		
    		writeCmd.execute(sql);
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    		return false;
    	}
		return true;
	}

	@Override
	public Citizen getCitizen(String ID)
	{
		try
		{
			Statement readCmd = connection.createStatement();
			String sql = "select * from Citizen where id = '"+ID+"'";
			ResultSet row = readCmd.executeQuery(sql);
			if(row.next())
				return _rowToCitizen(row);
			else 
				return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
