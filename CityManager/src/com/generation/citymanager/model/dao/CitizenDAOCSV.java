package com.generation.citymanager.model.dao;

import com.generation.citymanager.model.entities.Citizen;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CitizenDAOCSV implements CitizenDAO
{

	String source;
	List<Citizen> cache = null;

	public CitizenDAOCSV(String source)
	{
		this.source = source;

	}

	@Override
	public List<Citizen> getCitizens()
	{
		if (cache != null)
		{
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
				                parts[1],
				                parts[2],
				                parts[3]);
				res.add(citizen);
			}

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveCitizen(Citizen citizen)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCitizen(String ID)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Citizen getCitizen(String ID)
	{
		// TODO Auto-generated method stub
		return null;
	}
}