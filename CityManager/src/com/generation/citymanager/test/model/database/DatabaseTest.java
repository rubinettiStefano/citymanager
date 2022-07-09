package com.generation.citymanager.test.model.database;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.generation.citymanager.model.database.Database;
import com.generation.citymanager.model.database.DatabaseFactory;

class DatabaseTest
{

	@Test
	void testCRUD()
	{
		Database db = null;
		
		try
		{
			db = DatabaseFactory.make("city.db");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			fail("Dovevo riuscire a creare il db");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail("Dovevo riuscire a creare il db");
		}
		
		//testo READ (get)
		
		
		//testo CREATE (insert)
		
		
		//testo UPDATE 
		
		
		//testo DELETE
		
		
	}

}
