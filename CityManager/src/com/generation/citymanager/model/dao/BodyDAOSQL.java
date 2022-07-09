package com.generation.citymanager.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.entities.Body;

public class BodyDAOSQL implements BodyDAO
{
	Connection connection;

	public BodyDAOSQL(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public List<Body> getBodies() 
	{
		List<Body> res = new ArrayList<Body>();
		try
		{
    		Statement readCmd = connection.createStatement();
    		String sql = "select * from Body";
    		ResultSet rows = readCmd.executeQuery(sql);
    		
    		while(rows.next())					// while(reader.hasNextLine())
    		{
    			// facendo next ho già letto	   String row = reader.nextLine();
    			Body body = _rowToBody(rows);	// body body = _rowTobody(row);
    			res.add(body);					// res.add(body);
    		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
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
							rows.getString("bodyID")
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
		try
		{
			Statement readCmd = connection.createStatement();
			String sql = "select * from Body where id = '"+ID+"'";
			ResultSet row = readCmd.executeQuery(sql);
			if(row.next())
				return _rowToBody(row);
			else 
				return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean saveBody(Body body)
	{
		try
		{
    		if(getBody(body.ID)==null)
    		{
    			// INSERT
    			Statement writeCmd = connection.createStatement();
    			// soluzione poco carina... ma va per ora.
    			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    			String sql = 
    					"insert into body (id,name,type,left,bottom,right,top,cityID) "
    					+ "values('[id]','[name]','[type]','[left]','[bottom]','[right]','[top]','[cityID]')";
    			
    			sql = sql.replace("[id]", 	body.ID		);
    			sql = sql.replace("[name]", body.name	);
    			sql = sql.replace("[type]", 	body.type+""	);
    			sql = sql.replace("[left]", 	body.left+""	);
    			sql = sql.replace("[bottom]", 	body.bottom+""	);
    			sql = sql.replace("[right]", 	body.right+""	);
    			sql = sql.replace("[top]", 	 body.top+""	);
    			sql = sql.replace("[cityID]", 	body.cityID+""	);
    			//
    			// insert into body (id,name,w,h) values('body05','Ancona',300,300);
    			writeCmd.execute(sql);
    		}
    		else
    		{
    			Statement writeCmd = connection.createStatement();
    			// soluzione poco carina... ma va per ora.
    			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    			String sql = 
    					"UPDATE body set name = '[name]', type='[type]',left='[left]',bottom='[bottom]',right='[right]',top='[top]',cityID='[cityID]' where id='[i]'";
    			
    			sql = sql.replace("[id]", 	body.ID		);
    			sql = sql.replace("[name]", body.name	);
    			sql = sql.replace("[type]", 	body.type+""	);
    			sql = sql.replace("[left]", 	body.left+""	);
    			sql = sql.replace("[bottom]", 	body.bottom+""	);
    			sql = sql.replace("[right]", 	body.right+""	);
    			sql = sql.replace("[top]", 	 body.top+""	);
    			sql = sql.replace("[cityID]", 	body.cityID+""	);
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
	public boolean deleteBody(String ID)
	{
		try
		{
    		Statement writeCmd = connection.createStatement();
    		// soluzione poco carina... ma va per ora.
    		// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    		String sql = 
    				"Delete from Body where id= '"+ID+"'";
    		
    		writeCmd.execute(sql);
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    		return false;
    	}
		return true;
	}

}
