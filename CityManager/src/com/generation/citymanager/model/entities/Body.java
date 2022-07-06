package com.generation.citymanager.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Un body è qualunque cosa io possa aggiungere alla città
 * (parco, casa, centro commerciale...)
 * definito GEOGRAFICAMENTE. so sempre dovè. <br/>
 * E' una Entity quindi eredita ID.
 * @author Lorenzo
 *
 */
public class Body extends Entity
{
	// PROPRIETA DI OGGETTO CLASSE BODY
	// VARCHAR(100)
	String type; // Rresidential, commercial, park...
	String name; //OPZIONALE
	int left, bottom, right, top;  						// DICHIARAZIONI VARIABILI
	Segment horizontal;
	Segment vertical;
	//CHIAVE ESTERNA
	String cityID;
	City city;
	List<Citizen> citizens = new ArrayList<Citizen>();
	List<Review> reviews = new ArrayList<Review>(); // LISTA VUOTA
	
	
	/**
	 * costruttore di body
	 * proprieta che definiscono un corpo
	 * @param ID
	 * @param type
	 * @param left
	 * @param bottom
	 * @param right
	 * @param top
	 */
	public Body(String ID, String type, int left, int bottom, int right, int top) // COSTRUTTORE (METODO SPECIALE)
	{																				// CREA UN OGETTO DI QUESTA CLASSE
		this.ID	  =ID;
		this.type = type;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	public Body(String ID,String type, String name, int left, int bottom, int right, int top) //2 COSTRUTTORE CON AGGIUNTA DEL PARAMETRO NOME
	{
		this.ID	  =ID;
		this.type = type;
		this.name = name;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	public Body(String iD, String type, String name, int left, int bottom, int right, int top, Segment horizontal,
			Segment vertical, String cityID) 
	{
		ID = iD;
		this.type = type;
		this.name = name;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		this.cityID = cityID;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	
	public Body(String csv) // COSTRUTTORE DA FILE CSV
	{
		String[] parts = csv.split(","); // VETTORE DI STRINGHE E LE DIVIDIAMO CON LA ","
		ID		= parts[0];
		type	= parts[1];
		name	= parts[2];
		left	= Integer.parseInt(parts[3]); //GLI INTERI VANNO TRASFORMATI PERCHE 
		bottom	= Integer.parseInt(parts[4]); //SONO TUTTE CONSIDERATE STRINGHE
		right	= Integer.parseInt(parts[5]);
		top		= Integer.parseInt(parts[6]);
		cityID	= parts[7];
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
		
	}
	
	public String toString() // METODO DI RITORNO STRINGA PER STAMPARE 
	{
		return type+ " located at ("+left+","+bottom+")-("+right+","+top+")";
	}
	
	/**
	 * restituisce l area del body
	 * @return
	 */
	public int getArea() // METODO PER CALCOLARE L AREA
	{
		return 	horizontal.getLength()
				*
				vertical.getLength();		
	}
	
	/**
	 * vediamo se il body che arriva da fuori collide con quello 
	 * che abbiamo noi
	 * @param other
	 * @return
	 */
	public boolean collides(Body other) // METODO BOOLEANO RESTITUISCE DUE VALORI TRUE O FALSE
	{
		return this.horizontal.collides(other.horizontal) && this.vertical.collides(other.vertical);
	}
	// CI ARRIVA UN PARAMETRO DA FUORI CHE IN QUESTO CASO LO CHIAMIAMO OTHER(DI TIPO BODY)
	// E VEDIAMO SE COLLIDONO CON I NOSTRI BODY

	public boolean addCitizen(Citizen newCitizen)
	{
		citizens.add(newCitizen); // SE E' ARRIVATO FIN QUI VUOL DIRE CHE NON COLLIDE CON NESSUNO E VIENE
		// IMPOSTO LA CHIAVE ESTERNA IN BODY!
		newCitizen.bodyID = this.ID;
		// collego il campo city di newbody a ME STESSO
		newCitizen.body = this;
		
		return true;	
	}
	
	public boolean addReview(Review newReview)
	{
		reviews.add(newReview); // SE E' ARRIVATO FIN QUI VUOL DIRE CHE NON COLLIDE CON NESSUNO E VIENE
		// IMPOSTO LA CHIAVE ESTERNA IN BODY!
		newReview.bodyID = this.ID;
		// collego il campo city di newbody a ME STESSO
		newReview.body = this;
		
		return true;	
	}
	
}	

