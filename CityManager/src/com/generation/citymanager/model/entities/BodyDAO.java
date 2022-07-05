package com.generation.citymanager.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
					String [] parts = row.split(",");
					
					Body body = new Body
									(
										parts[0], parts[1], parts[2],
										Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), 
										Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
										parts[7]
									);
					
					res.add(body);
					
				}
				reader.close();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				
			} 
		
			cache = res; 
			return res;

		}
		
}