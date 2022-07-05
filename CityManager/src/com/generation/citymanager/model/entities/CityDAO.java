package com.generation.citymanager.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityDAO
{
	String source;
	List<City> cache = null;
	
	public CityDAO(String source)
	{
		this.source = source;
	}
	
	public List<City> getCities()
	{
		if(cache!=null)
			return cache;
		
		List<City> res = new ArrayList<City>();
		try
		{
			File file = new File(source);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine())
			{
				String row = reader.nextLine();
				String[] parts = row.split(",");
				City city = new City(parts[0],parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
				res.add(city);
			}
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Non trovo il file "+source);
		}
		cache = res;
		return res;
	}
}
