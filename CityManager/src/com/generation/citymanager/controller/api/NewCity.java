package com.generation.citymanager.controller.api;

import java.sql.SQLException;
import java.util.Scanner;

import com.generation.citymanager.model.database.Database;
import com.generation.citymanager.model.database.DatabaseFactory;
import com.generation.citymanager.model.entities.City;

public class NewCity
{

	public static void main(String[] args) throws ClassNotFoundException, SQLException
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
			keyboard.close();
			return;
		}
		System.out.println("Insert ID");
		String ID = keyboard.nextLine();
		System.out.println("Insert Name");
		String name = keyboard.nextLine();
		System.out.println("Insert width");
		int w = Integer.parseInt(keyboard.nextLine());
		System.out.println("Insert height");
		int h = Integer.parseInt(keyboard.nextLine());
		
		City city = new City(ID,name,w,h);
		
		
		try
		{
			database.insertCity(city);
			System.out.println("Tutt'appost");
		}
		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
		}
		keyboard.close();
		
	}

}
