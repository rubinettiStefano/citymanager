package com.generation.citymanager.model.entities;
import java.util.List;
import java.util.Scanner;
public class SearchForActivity 
{

	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		
		Database database = new Database("city.csv", "body.csv", "citizen.csv", "review.csv");
		
		System.out.println("Inserire l' attivita da cercare");
		String activity = keyboard.nextLine();
		
		System.out.println("In che citta'?");
		String city = keyboard.nextLine();
		
		System.out.println("Cerco "+activity+" a "+city);
		
		List<Body> res = database.getBodies(city, activity);
		
		if(res.size()>0)
			for(Body body:res)
				System.out.println(body);
		else
			System.out.println("NESSUN RISULTATO TROVATO");
		// select * from City inner join Body on City.ID=Body.CityId where name like %activity% and city.Name = name 
		keyboard.close();
	}

}
