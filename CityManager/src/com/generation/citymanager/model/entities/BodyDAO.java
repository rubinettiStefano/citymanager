package com.generation.citymanager.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * legge e scrive body da file
 * @author Lorenzo
 *
 */
public class BodyDAO 
{

	String source;
	List<Body> cache = null; 
		
		
	public BodyDAO(String source)
	{
		this.source=source;
	}
		
	public List<Body> getBodies() 
	{
		if(cache!=null)
			return cache; 
			
		List<Body> res = new ArrayList<Body>();
		try 
		{
			File file = new File(source); 
			Scanner reader = new Scanner(file);
					
			while(reader.hasNextLine()) 
			{
				String row = reader.nextLine(); 						
				Body body = new Body(row);
						
				res.add(body);
			}
			reader.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
			
		cache = res; 
		return res;

	}
		
}
