package com.generation.citymanager.model.dao;

import java.util.List;

import com.generation.citymanager.model.entities.City;

/**
 * Contratto che specifica i metodi che dovranno essere offerti da tutti i CityDAO
 * @author FP80
 *
 */
public interface CityDAO 
{

	/**
	 * Elenco di TUTTE le città in archivio
	 * @return
	 */
	List<City> getCities();
	
	/**
	 * legge dall'archivio la città con l'ID passato.
	 * se non la trova, restituisce null
	 * @param ID
	 * @return
	 */
	City getCity(String ID);
	
	/**
	 * Voglio salvare city sul db
	 * @param newCity
	 * @return
	 */
	boolean saveCity(City city);
	
	/**
	 * Voglio cancellare la città con questo ID
	 * @param ID
	 * @return
	 */
	boolean deleteCity(String ID);
	
	/**
	 * tutte le città con namePart nel nome
	 * @param namePart
	 * @return
	 */
	List<City> getCities(String namePart);
	
}
