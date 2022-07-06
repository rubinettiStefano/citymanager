package com.generation.citymanager.model.dao;

import com.generation.citymanager.model.entities.Body;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe BodyDAO serve ad effettuare operazioni IO della classe BODY in un
 * DB.
 * 
 * 
 * @author Giuseppe
 *
 */
public class BodyDAOCSV implements BodyDAO
{

	String source;
	List<Body> cache = null;

	public BodyDAOCSV(String source)
	{
		this.source = source;

	}

	@Override
	public List<Body> getBodies()
	{
		if (cache != null)
			return cache;
		List<Body> res = new ArrayList<Body>();

		try
		{
			File file = new File(source);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine())
			{
				String row = reader.nextLine();
				String[] parts = row.split(",");
				Body body = new Body(parts[0], // ID
				                parts[1],
				                parts[2],
				                Integer.parseInt(parts[3]),
				                Integer.parseInt(parts[4]),
				                Integer.parseInt(parts[5]),
				                Integer.parseInt(parts[6]),
				                parts[7]);
				res.add(body);
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
	public List<Body> getBodies(String namePart)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Body getBody(String ID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveBody(Body body)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBody(String ID)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
