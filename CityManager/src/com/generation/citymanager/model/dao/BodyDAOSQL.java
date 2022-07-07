package com.generation.citymanager.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.entities.Body;
import com.generation.citymanager.model.entities.Citizen;
import com.generation.citymanager.model.entities.City;

public class BodyDAOSQL implements BodyDAO
{
	Connection connection;

	public BodyDAOSQL(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public List<Body> getBodies() throws SQLException
	{
		List<Body> res = new ArrayList<Body>();
		
		Statement readCmd = connection.createStatement();
		String sql = "select * from Body";
		ResultSet rows = readCmd.executeQuery(sql);
		
		while(rows.next())					// while(reader.hasNextLine())
		{
			// facendo next ho già letto	   String row = reader.nextLine();
			Body body = _rowToBody(rows);	// City city = _rowToCity(row);
			res.add(body);					// res.add(city);
		}
		
		return res;
	}

	private Body _rowToBody(ResultSet rows) throws SQLException
	{
		return new Body
						(
							rows.getString("id"),
							rows.getString("type"),
							rows.getString("name"),
							rows.getInt("left"),
							rows.getInt("bottom"),
							rows.getInt("right"),
							rows.getInt("top"),
							rows.getString("cityID")
						);
	}

	@Override
	public List<Body> getBodies(String namePart)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Body getBody(String ID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveBody(Body body)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBody(String ID)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
