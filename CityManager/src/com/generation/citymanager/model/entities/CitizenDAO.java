package com.generation.citymanager.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CitizenDAO
{
	String source;
	List<Citizen> cache = null;

	public CitizenDAO(String source) 
	{
		this.source=source;
	}
	
	
	public List<Citizen> getCitizens()
	{
		if(cache!=null)
		{
			return cache;
		}
		
		List<Citizen> res = new ArrayList<Citizen>();
		try 
		{
			File file = new File(source);
			Scanner reader = new Scanner(file);
		
			while(reader.hasNextLine()) //hasNextLine() : booleano. Mi dice se HO una riga
			{
				//leggi una riga
				String row = reader.nextLine();//String : legge una riga
				//convertila in una città
				String[] parts = row.split(",");
				Citizen citizen = new Citizen(parts[0],parts[1],parts[2],parts[3]);
						
				res.add(citizen);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("non trovo il file " + source);
		}
		//IMPOSTO LA CACHE
		cache = res; //la lista che ho prodotto viene messa in CACHE, dove la troverò la prossima volta
		return res;
	}
	
		
		
}
	