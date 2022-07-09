package com.generation.citymanager.test.model.dao;

import org.junit.jupiter.api.Test;

import com.generation.citymanager.model.dao.CityDAOCSV;
import com.generation.citymanager.model.entities.City;

class CityDAOCSVTest
{

	@Test
	void testCRUDCity()
	{
		//Voglio che il mio Test sia indipendente dal mondo, ciò che faccio nei test deve rimanere nei test
		//mi creo un file apposito, per non intaccare il resto del programma
		CityDAOCSV dao = new CityDAOCSV("cityTest.csv");
		City badData = new City("CITY01","CittàImmaginaria",10,-20);
		assert(!dao.saveCity(badData));  //Il metodo deve restituire false
		assert(dao.getCity("CITY01")==null); //Verifico che non sia presente nel file
		
		
		// ok, ora ne inseriamo una corretta.
		// verifichiamo di averla salvata, verifichiamo che ci sia
		// verifichiamo che la lista delle città sia lunga 1 
		City newCityGood = new City("CITY01","CittàGiusta",10,10);
		assert(dao.saveCity(newCityGood)); //il metodo restituisce true
		assert(dao.getCity("CITY01")!=null); //verifico che esista nel file
		assert(dao.getCities().size()==1); //verifico che ne abbia inserita una sola
		
		// ops, mi sono accorto che ho sbagliato dimensione
		// modifico, risalvo, ricarico, e verifico che la dimensione sia quella NUOVA
		
		// ultimo: mi sono rotto le scatole. Questa città deve morire all'alba
		// la cancello
		
		// verifico di non avere più niente
		
	}
	
	

}
