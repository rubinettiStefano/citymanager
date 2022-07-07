package com.generation.citymanager.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.generation.citymanager.model.dao.BodyDAOCSV;
import com.generation.citymanager.model.dao.BodyDAOSQL;
import com.generation.citymanager.model.dao.CitizenDAOCSV;
import com.generation.citymanager.model.dao.CitizenDAOSQL;
import com.generation.citymanager.model.dao.CityDAOCSV;
import com.generation.citymanager.model.dao.CityDAOSQL;

public class DatabaseFactory
{
	
	public static Database make(String cityFile,String bodyFile,String citizenFile) 
	{
		return new Database
						(
								new CityDAOCSV(cityFile),
								new BodyDAOCSV(bodyFile),
								new CitizenDAOCSV(citizenFile)
						);
	}
	
	public static Database make(String dbname) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:"+dbname);
		
		return new Database
						(
							new CityDAOSQL(connection),
							new BodyDAOSQL(connection),
							new CitizenDAOSQL(connection)
						);
	}
}
