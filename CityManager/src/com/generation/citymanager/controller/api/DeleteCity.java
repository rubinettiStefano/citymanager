package com.generation.citymanager.controller.api;

import java.sql.SQLException;
import java.util.Scanner;

import com.generation.citymanager.model.database.Database;
import com.generation.citymanager.model.database.DatabaseFactory;

public class DeleteCity
{

	public static void main(String[] args)
	{
		Database database;

		Scanner keyboard = new Scanner(System.in);
		try
		{
			database = DatabaseFactory.make("city.db");
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		System.out.println("Insert ID");
		String ID = keyboard.nextLine();
		
		if(database.deleteCity(ID))
			System.out.println("Deleted");
		else
			System.out.println("Not Delete. Problem");

	}

}