package com.generation.citymanager.model.entities;

/**
 * UNA CLASSE SEGMENT SERVE A DEFINIRE , UN INTERVALLO TRA DUE NUMERI SA:
 * - CALCOLARE LA PROPRIA LUNGHEZZA 
 * - SE COLLIDE CON UN ALTREO SEGMENTO
 * 
 * @author Lorenzo
 *
 */
public class Segment 
{
	
	// proprieta' di un oggetto di classe Segment
	int a,b; // DICHIARAZIONE VARIABILI
	
	/**
	 * COSTRUTTORE PER SEGMENT - INSERIRE I DUE NUMERI IN ORDINE
	 * PRIMA IL PIU PICCOLO, POI IL PIU GRANDE
	 * @param a
	 * @param b
	 */
	public  Segment(int a, int b) // COSTRUTTORE (METODO SPECIALE)
	{
		this.a = a;
		this.b = b;
	}
	
	public String toString() //METODO DI RITORNO STRINGA (STAMPA)
	{
		return "From " + a + " to " + b;
	}
	
	/**
	 * RESTITUISCE LA LUNGHEZZA DEL SEGMENTO
	 * @return
	 */
	public int getLength() // METODO DI RITORNO INTERO, LUNGHEZZA SEGMENTO 
	{
		return b - a;
	}
	
	/**
	 * DUE SEGMENTI COLLIDONO SE HANNO PIU DI UN PUNTO IN COMUNE
	 * @param other
	 * @return
	 */
	public boolean collides(Segment other) // METODO BOOLEANO ENTRA UN PARAMETRO IN QUESTO CASO UN SEGMENTO
	{										// COLLIDE O NO?
		// quanti segmenti ho in questo metodo?
		// il primo e' scritto sopra, si chiama other
		// l'altro e' qui: si chiama THIS e lo ho SEMPRE a disposizione
		return 	(other.a > this.a 	&& other.a < this.b ) || //other inizia in this
				(this.a  > other.a 	&& this.a  < other.b) || //this  inizia in other
				(this.a==other.a || this.b==other.b)	   ; //iniziano o finiscono nello stesso punto
					// espressione BOOLEANA 
					// condizione
					// predicato
	}
}
