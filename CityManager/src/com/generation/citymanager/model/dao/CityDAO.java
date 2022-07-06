package com.generation.citymanager.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.generation.citymanager.model.entities.City;


/**
 * io caricher� delle citt� dall'hard disk
 * legger� e scriver�
 * @author ED
 *
 */
public class CityDAO 
{
	//origine dei dati
	String source;
	
	List<City> cache = null; //null = ancora non esiste la lista, null vuol dire che ancora non esite l'oggetto
	//cache = memeoria temporanea, in cui io salvo i miei risultati per poterli riutilizzare
	
	
	public CityDAO(String source)
	{
		this.source=source;
		  
	}
	
	public List<City> getCities() 
	{
		
		if(cache!=null)
			return cache; 
		
		List<City> res = new ArrayList<City>();
		try 
			{
				File file = new File(source); // source = city.csv
				Scanner reader = new Scanner(file);
				
				while(reader.hasNextLine()) //� un booleano e mi dice se ho una riga
				{
					String row = reader.nextLine(); //stringa, legge una riga
					String [] parts = row.split(",");
					
					City city = new City(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
					
					res.add(city);
					
				}
				reader.close();
			} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				//syso("non trovo il file" +source);
			} 
		
		cache = res; //imposto la cache, la lista che ho prodotto viene messa in cache
		return res;

	}
	
	
	
	

}
