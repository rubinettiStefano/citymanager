package com.generation.citymanager.model.entities;

import java.util.ArrayList;
import java.util.List;

public class City extends Entity
 {
	//PROPRIETA DI OGGETTO DELLA CLASSE CITY
	public String name; // VARIABILE
	public int w;	// VARIABILI
	public int h;
	public List<Body> bodies = new ArrayList<Body>(); // LISTA VUOTA
	
	public City(String ID, String name, int w, int h) // COSTRUTTORE 
	{
		this.ID = ID;
		this.name = name;
		this.w = w;
		this.h = h;
	}
	
	public City(String csv) // COSTRUTTORE DA CSV
	{
		String[] parts = csv.split(",");
		ID  = parts[0];
		name	= parts[1];
		w		= Integer.parseInt(parts[2]);
		h		= Integer.parseInt(parts[3]);
	}
	
	// MI ARRIVA UN CORPO
	// IO NON DICO "SI PADRONE", IO CONTROLLO... C'E SPAZIO?
	//SE C'E SPAZIO, LO PRENDO E DICO "TRUE"
	// SE NON C'E, DICO FALSE
	public  boolean addBody(Body newBody) //PRENDE UNA PROPRIETA DELLA CLASSE BODY E RESTITUISCE UN BOOLEAN
	{
		if
		(
				newBody.bottom 	< 0			||
				newBody.top		< 0 		||
				newBody.left	< 0 		||
				newBody.right	< 0			||
				newBody.right	> this.w 	||
				newBody.top		> this.h	 
		)
		return false;
		
		// devo vedere se collide con uno qualunque degli altri corpi!!
		for(Body oldBody:bodies) // SCORRIMENTO DELLA LISTA 
			if(oldBody.collides(newBody)) // CONDIZIONE CONTROLLA SE IL NUOVO BODY COLLIDE CON UNO DEI VECCHI BODY
				return false;
		
		//io sto aggiungendo il body alla mia lista di corpi
		//rapporto DA CITY a BODY!
		bodies.add(newBody); // SE E' ARRIVATO FIN QUI VUOL DIRE CHE NON COLLIDE CON NESSUNO E VIENE
		// IMPOSTO LA CHIAVE ESTERNA IN BODY!
		newBody.cityID = this.ID;
		// collego il campo city di newbody a ME STESSO
		newBody.city = this;
		
		return true;		
	}
		
		
	public int getBuiltArea() // METODO DI RITORNO UN INTERO OSSIA LA SOMMA DI TUTTE LE AREE 
	{						  // DEI BODY CHE STANNO DENTRO LA NOSTRA LISTA
		int res = 0;
		// STO DICHIARANDO UNA VARIABILE A USO INTERNO DEL METODO
		// SCOPE = LOCALE AL METODO
			
		// SI LEGGE, PER OGNI CORPO BODY NELLA LISTA BODIES
		for(Body body:bodies) // SCORRIMENTO DEI CORPI BODY NELLA LISTA BODIES
			res+=body.getArea(); // DI QUEI CORPI PRENDIAMO L AREA E L AGGIUNGIAMO A RES 
		return res; // RESTITUIAMO RES // RES E' DETTA VARIABILE DI ACCUMULAZIONE	
	}
	
	public int getPopulation()
	{
		int res = 0;
		for(Body body:bodies)
			res+=body.citizens.size();
		
		return res;
	}
	
	
	public int getImu()
	{
		return  bodies
				.stream()
				.filter(b -> b.type.equalsIgnoreCase("residential") && b.citizens.size()>10)
				.map(b -> b.getArea() < 10 ?  b.getArea() : 10).reduce(0, Integer::sum);
	}

	@Override
	public String toString()
	{
		return "City [name=" + name + ", w=" + w + ", h=" + h + ", bodies=" + bodies + "]";
	}
	
	
	
}
