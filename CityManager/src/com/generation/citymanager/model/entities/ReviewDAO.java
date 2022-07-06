package com.generation.citymanager.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewDAO 
{
	String source;
	List<Review> cache = null;

	public ReviewDAO(String source) 
	{
		this.source=source;
	}
	
	
	public List<Review> getReviews()
	{
		if(cache!=null)
		{
			return cache;
		}
		
		List<Review> res = new ArrayList<Review>();
		try 
		{
			File file = new File(source);
			Scanner reader = new Scanner(file);
		
			while(reader.hasNextLine()) //hasNextLine() : booleano. Mi dice se HO una riga
			{
				//leggi una riga
				String row = reader.nextLine();
				
				Review review = new Review(row);
						
				res.add(review);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("non trovo il file " + source);
		}
		//IMPOSTO LA CACHE
		cache = res; //la lista che ho prodotto viene messa in CACHE, dove la trover� la prossima volta
		return res;
	}

}
