package com.generation.citymanager.model.dao;

import java.sql.SQLException;
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
	 * Elenco di TUTTE le citt� in archivio
	 * @return
	 * @throws SQLException 
	 */
	List<City> getCities() throws SQLException;
	
	/**
	 * legge dall'archivio la citt� con l'ID passato.
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
	 * Voglio cancellare la citt� con questo ID
	 * @param ID
	 * @return
	 */
	boolean deleteCity(String ID);
	
	/**
	 * tutte le citt� con namePart nel nome
	 * @param namePart
	 * @return
	 */
	List<City> getCities(String namePart);
	
}
