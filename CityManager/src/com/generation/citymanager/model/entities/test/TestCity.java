package com.generation.citymanager.model.entities.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.generation.citymanager.model.entities.Body;
import com.generation.citymanager.model.entities.City;

public class TestCity
{
	public static void main(String[] args) 																throws FileNotFoundException
	{
		City city = loadCityOLD("mordor.csv");
	}
	
	public static City loadCityOLD(String filename) 															throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner fileReader = new Scanner(file);
		
		String cityLine = fileReader.nextLine(); //"Mordor,100,100"
		String[] cityParts = cityLine.split(",");
		String name = cityParts[0]; // parts[0] contiene "Mordor"
		int w = Integer.parseInt(cityParts[1]); //Converto da "100" a 100
		int h = Integer.parseInt(cityParts[2]);
		
		City city = new City(name,w,h);
		
		String buildLine1 = fileReader.nextLine();
		String[] build1Parts = 	buildLine1.split(",");	
		Body build1 = new Body
						(
							build1Parts[0],
							build1Parts[1],
							Integer.parseInt(build1Parts[2]),
							Integer.parseInt(build1Parts[3]),
							Integer.parseInt(build1Parts[4]),
							Integer.parseInt(build1Parts[5])
						);
		city.addBody(build1);
		
		String buildLine2 = fileReader.nextLine();
		String[] build2Parts = 	buildLine2.split(",");	
		Body build2 = new Body
						(
							build2Parts[0],
							build2Parts[1],
							Integer.parseInt(build2Parts[2]),
							Integer.parseInt(build2Parts[3]),
							Integer.parseInt(build2Parts[4]),
							Integer.parseInt(build2Parts[5])
						);
		city.addBody(build2);
		
		String buildLine3 = fileReader.nextLine();
		String[] build3Parts = 	buildLine3.split(",");	
		Body build3 = new Body
						(
							build3Parts[0],
							build3Parts[1],
							Integer.parseInt(build3Parts[2]),
							Integer.parseInt(build3Parts[3]),
							Integer.parseInt(build3Parts[4]),
							Integer.parseInt(build3Parts[5])
						);
		city.addBody(build3);
		
		return city;
	}
	
	public static City loadCity(String filename) 															throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner fileReader = new Scanner(file);
		
		String cityLine = fileReader.nextLine(); //"Mordor,100,100"
		String[] cityParts = cityLine.split(",");
		String name = cityParts[0]; // parts[0] contiene "Mordor"
		int w = Integer.parseInt(cityParts[1]); //Converto da "100" a 100
		int h = Integer.parseInt(cityParts[2]);
		
		City city = new City(name,w,h);
		
		while(fileReader.hasNextLine())
		{
			String buildLine = fileReader.nextLine();
			String[] buildParts = 	buildLine.split(",");	
			Body build = new Body
							(
								buildParts[0],
								buildParts[1],
								Integer.parseInt(buildParts[2]),
								Integer.parseInt(buildParts[3]),
								Integer.parseInt(buildParts[4]),
								Integer.parseInt(buildParts[5])
							);
			city.addBody(build);
		}
		return city;
	}
	
}
