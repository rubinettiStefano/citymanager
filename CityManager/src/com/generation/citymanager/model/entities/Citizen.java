package com.generation.citymanager.model.entities;

public class Citizen extends Entity
{
	// ho anche ID, anche se non si vede
	// campi miei
	public String name;

	public String surname;
	
	// riferimenti all edificio in cui abito(chiave esterna e collegamento diretto)
	public String bodyID;
	Body body;
	
	public Citizen(String ID, String name, String surname, String bodyID) 
	{
		this.ID   = ID;
		this.name = name;
		this.surname = surname;
		this.bodyID = bodyID;
	}
	
	public Citizen(String csv) // COSTRUTTORE DA CSV
	{
		String[] parts = csv.split(",");
		ID  		= parts[0];
		name		= parts[1];
		surname		= parts[2];
		bodyID		= parts[3];
	}
	
	
	
}
