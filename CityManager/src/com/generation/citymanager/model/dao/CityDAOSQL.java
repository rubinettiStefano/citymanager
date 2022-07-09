package com.generation.citymanager.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.citymanager.model.entities.City;

public class CityDAOSQL implements CityDAO
{

	Connection connection;

	public CityDAOSQL(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public List<City> getCities() 
	{
		
    		List<City> res = new ArrayList<>();
		try
		{
    		// 1	- CARICARE TUTTE LE CITTÀ DAL  DB
    		// 1.1  - PREPARARE LO STATEMENT
    		// P.S. - una Connection è una factory di Statement
    		Statement readCmd = connection.createStatement();
    		// Statement = il contenitore di una query
    		// 1.2 - preparare la stringa query
    		String sql = "select * from City";
    		// la query, espressa in SQL
    		// 1.3 - eseguire la query: lo statement è una factory di ResultSet
    		ResultSet rows = readCmd.executeQuery(sql);
    		// resultSet -> Insieme di righe, il RISULTATO di una query
    		
    		// 2 - Prendere OGNI riga, Convertirla in una CITY, aggiungerla a RES
    		// DB								SCANNER
    		while(rows.next())					// while(reader.hasNextLine())
    		{
    			// facendo next ho già letto	   String row = reader.nextLine();
    			City city = _rowToCity(rows);	// City city = _rowToCity(row);
    			res.add(city);					// res.add(city);
    		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return res;
	}

	private City _rowToCity(ResultSet rows) throws SQLException
	{
		return new City
					(
						rows.getString("id"),
						rows.getString("name"),
						rows.getInt("w"),
						rows.getInt("h")
					);
	}

	@Override
	public City getCity(String ID)
	{
		try
		{
			Statement readCmd = connection.createStatement();
			String sql = "select * from City where id = '"+ID+"'";
			ResultSet row = readCmd.executeQuery(sql);
			if(row.next())
				return _rowToCity(row);
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
	public boolean saveCity(City city) 
	{
		try
		{
    		if(getCity(city.ID)==null)
    		{
    			// INSERT
    			Statement writeCmd = connection.createStatement();
    			// soluzione poco carina... ma va per ora.
    			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    			String sql = 
    					"insert into City (id,name,w,h) values('[id]','[name]','[w]','[h]')";
    			
    			sql = sql.replace("[id]", 	city.ID		);
    			sql = sql.replace("[name]", city.name	);
    			sql = sql.replace("[w]", 	city.w+""	);
    			sql = sql.replace("[h]", 	city.h+""	);
    			//
    			// insert into City (id,name,w,h) values('CITY05','Ancona',300,300);
    			writeCmd.execute(sql);
    		}
    		else
    		{
    			Statement writeCmd = connection.createStatement();
    			// soluzione poco carina... ma va per ora.
    			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    			String sql = 
    					"UPDATE City set name = '[name]', w='[w]',h='[h]' where id='[i]'";
    			
    			sql = sql.replace("[id]", 	city.ID		);
    			sql = sql.replace("[name]", city.name	);
    			sql = sql.replace("[w]", 	city.w+""	);
    			sql = sql.replace("[h]", 	city.h+""	);
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
	public boolean deleteCity(String ID)
	{
		try
		{
    		Statement writeCmd = connection.createStatement();
    		// soluzione poco carina... ma va per ora.
    		// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
    		String sql = 
    				"Delete from City where id= '"+ID+"'";
    		
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
	public List<City> getCities(String namePart)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
