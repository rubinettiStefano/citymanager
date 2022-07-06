package com.generation.citymanager.model.dao;
import com.generation.citymanager.model.entities.City;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Questa classe ci permette di creare una città tramite un file dell'hardDisk, e in futuro anche di modificare il file.
 * è fornita di un sistema di Try and Catch per gestire l' exeption qualora il file non fosse raggiungibile, e di un sistema di chache che permette di salvare il file in una memoria temporanea, velocizzando un'eventuale seconda lettura
 * 
 * @author User
 *
 */
public class CityDAOCSV implements CityDAO
{	
	//per caricare la città mi serviranno due DAO

	String source;
	List<City> cache = null;
	
	public CityDAOCSV(String source)
	{
		this.source=source;
		
	}
	
	public City getCity(String ID)
	{
		for(City city:getCities())
			if(city.ID.equals(ID))
				return city;
		
		return null;
	}
	
	public List<City> getCities()
	{
		
		if(cache!=null)
			return cache;
		List <City>	res= new ArrayList<City>();
	
		try 
		{
		
			File file = new File(source);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine())		
			{
				String row = reader.nextLine();				//legge una riga
				String[] parts = row.split(",");			//la divide per ogni virgola
				City city = new City(parts[0],parts[1],Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));			
				res.add(city);
				
				
				
				
			}
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("non trovo il file source");
		}
		cache= res;						//imposto la cache, la lisdta che ho prodotto viene messa in CACHE, dove la troverò la prossima volta
		return res;
	}

	@Override
	public boolean saveCity(City city) 
	{
		
		if(city.ID==null || city.name==null || city.w<=0 || city.h<=0)
			return false;
		// 1 - la città che mi arriva è nuova o è già presente?
		City oldVersion = getCity(city.ID);
		// update: cancello la vecchia versione...
		if(oldVersion!=null)
			deleteCity(city.ID);
		// AGGIUNGO LA NUOVA
		cache.add(city);
		_refreshFile();
		return true;
	}

	@Override
	public boolean deleteCity(String ID) 
	{
		City city = getCity(ID);
		
		if(city==null)
			return false;
		
		// la tolgo dalla cache
		cache.remove(city);
		
		// sovrascrivo il file coi contenuti della cache
		_refreshFile();
		return true;
	}

	private void _refreshFile()
	{
		// scorre la cache e trasforma ogni oggetto city in una riga del file
		String updatedRows = "";
		for(int i=0;i<cache.size();i++)
		{
			City city = cache.get(i);
			updatedRows+=city.ID+","+city.name+","+city.w+","+city.h;
			if(i<cache.size()-1)
				updatedRows+="\n";
		}
		
		try
		{
			FileWriter writer = new FileWriter(source);
			writer.write(updatedRows);
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Errore rigenerazione file");
		}
	}
	
	@Override
	public List<City> getCities(String namePart) 
	{
		List<City> res = new ArrayList<City>();
		for(City city:getCities())
			if(city.name.toLowerCase().contains(namePart.toLowerCase()))
				res.add(city);

		return res;
	}
	
}
