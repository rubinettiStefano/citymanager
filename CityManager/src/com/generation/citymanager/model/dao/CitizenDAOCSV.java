package com.generation.citymanager.model.dao;

import com.generation.citymanager.model.entities.Citizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CitizenDAOCSV implements CitizenDAO {

	String source;
	List<Citizen> cache = null;

	public CitizenDAOCSV(String source) 
	{
		this.source = source;
	}

	@Override
	public List<Citizen> getCitizens()
	{
		
		if (cache != null) {
			return cache;

		}

		List<Citizen> res = new ArrayList<Citizen>();

		try 
		{

			File file = new File(source);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine())

			{
				String row = reader.nextLine();
				String[] parts = row.split(",");
				Citizen citizen = new Citizen(parts[0], // ID
						parts[1], parts[2], parts[3]);
				res.add(citizen);
			}
			reader.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("non trovo il file source");
		}

		cache = res;
		return res;

	}

	@Override
	public List<Citizen> getCitizens(String surnamePart)
	{
		List<Citizen> res = new ArrayList<Citizen>();
		for (Citizen citizen : getCitizens())
			if (citizen.ID.contains(surnamePart))
				res.add(citizen);
		return null;

	}

	private void _refreshFile()
	{
		// scorre la cache e trasforma ogni oggetto city in una riga del file
		String updatedRows = "";
		for (int i = 0; i < cache.size(); i++)
		{
			Citizen citizen = cache.get(i);
			updatedRows += citizen.ID + "," + citizen.name + "," + citizen.surname + "," + citizen.bodyID;
			if (i < cache.size() - 1)
				updatedRows += "\n";
		}

		try 
		{
			FileWriter writer = new FileWriter(source);
			writer.write(updatedRows);
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Errore rigenerazione file");
		}
	}

	@Override
	public boolean saveCitizen(Citizen citizen)
	{
		if (citizen.ID == null || citizen.name == null || citizen.bodyID == null)
			return false;

		Citizen oldVersion = getCitizen(citizen.ID);
		// update: cancello la vecchia versione...
		if (oldVersion != null)
			deleteCitizen(citizen.ID);
		// AGGIUNGO LA NUOVA
		cache.add(citizen);
		_refreshFile();
		return true;
	}

	@Override
	public boolean deleteCitizen(String ID) 
	{
			Citizen citizen = getCitizen(ID);

			if (citizen == null)
				return false;

			// la tolgo dalla cache
			cache.remove(citizen);

			// sovrascrivo il file coi contenuti della cache
			_refreshFile();
			return true;
	}

	@Override
	public Citizen getCitizen(String ID) 
	{

		for (Citizen citizen : getCitizens())
			if (citizen.ID.equals(ID))
				return citizen;

		return null;
	}

}