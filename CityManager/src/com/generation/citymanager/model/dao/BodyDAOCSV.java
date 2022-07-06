package com.generation.citymanager.model.dao;

import com.generation.citymanager.model.entities.Body;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
public class BodyDAOCSV implements BodyDAO {

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
			while (reader.hasNextLine()) {
				String row = reader.nextLine();
				String[] parts = row.split(",");
				Body body = new Body(parts[0], // ID
						parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
						Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
				res.add(body);
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
	public List<Body> getBodies(String namePart) {

		List<Body> res = new ArrayList<Body>();
		for (Body body : getBodies())
			if (body.name.toLowerCase().contains(namePart.toLowerCase()))
				res.add(body);

		return null;
	}

	private void _refreshFile() {
		String updatedRows = "";
		for (int i = 0; i < cache.size(); i++) {
			Body body = cache.get(i);
			updatedRows += body.ID + "," + body.name + "," + body.type + "," + body.right + "," + body.bottom + ","
					+ body.left + "," + body.top + "," + body.cityID;
			if (i < cache.size() - 1)
				updatedRows += "\n";
		}

		try {
			FileWriter writer = new FileWriter(source);
			writer.write(updatedRows);
			writer.close();
		} catch (IOException e) {
			System.out.println("Errore rigenerazione file");
		}
	}

	@Override
	public Body getBody(String ID) {
		for (Body body : getBodies())
			if (body.ID.equals(ID))
				return body;
		return null;

	}

	@Override
	public boolean saveBody(Body body) {
		if (body.ID == null || body.name == null || body.type == null || body.left < 0 || body.bottom < 0
				|| body.top < 0 || body.cityID == null)
			return false;

		Body oldVersion = getBody(body.ID);
		if (oldVersion != null)
			deleteBody(body.ID);
		cache.add(body);
		_refreshFile();
		return true;

	}

	@Override
	public boolean deleteBody(String ID) {

		Body body = getBody(ID);

		if (body == null)
			return false;
		cache.remove(body);
		_refreshFile();
		return true;

	}

}