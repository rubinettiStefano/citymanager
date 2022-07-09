package com.generation.citymanager.test.model.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CityTest
{

	@Test
	void testAddBody()
	{
		// Creare una città C
		
		// provare a inserire un corpo FUORI dai confini di C
		// il metodo addBody deve restituire falso
		// la lunghezza della lista bodies deve restare 0
		
		// provare a inserire un corpo ADATTO a C
		// il metodo addBody deve restituire vero
		// c.bodies deve essere lunga 1
		
		// provare a inserire un corpo ADATTO a c ma che va in conflitto con quello precedente
		// il metodo addBody deve restituire falso
		// la lunghezza della lista bodies deve restare 1
				
		// provare a inserire un corpo ADATTO a c e SENZA conflitti
		// il metodo addBody deve restituire true
		// la lunghezza della lista bodies deve diventare due

	}

	@Test
	void testGetBuiltArea()
	{
		fail("Not yet implemented");
	}

	@Test
	void testGetPopulation()
	{
		fail("Not yet implemented");
	}

	@Test
	void testGetImu()
	{
		fail("Not yet implemented");
	}

}
