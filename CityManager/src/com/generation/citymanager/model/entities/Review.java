package com.generation.citymanager.model.entities;

public class Review extends Entity
{
	String title;
	String text;
	int score;
	String bodyID;
	Body body;
	
	public Review(String ID, String title, String text, int score, String bodyID) 
	{
		this.ID =ID;
		this.title = title;
		this.text = text;
		this.score = score;
		this.bodyID = bodyID;
	}
	
	public Review(String csv) // COSTRUTTORE DA CSV
	{
		String[] parts = csv.split(",");
		ID  		= parts[0];
		title		= parts[1];
		text		= parts[2];
		score		= Integer.parseInt(parts[3]);
		bodyID		= parts[4];
	}
	
	
}
