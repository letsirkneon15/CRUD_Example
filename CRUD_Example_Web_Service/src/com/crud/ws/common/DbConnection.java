package com.crud.ws.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * This class is to handle Oracle DB connection
 * @author Kristel
 * @version 1.0
 * @since 2019.05.29
 */
public class DbConnection {
	
	/**
	 * This method manages the Hibernate Session Factory.</br>
	 * NOTE: This does not return the actual data. Only the connection
	 * @param config	Path to the configuration file.</br>
	 *                  If null is passed, it will get default (root)
	 * @return SessionFactory	This is the hibernate connection configuration
	 */
	@SuppressWarnings("deprecation")
	public static SessionFactory hibernateConnection(String config) {
		SessionFactory factory = null;
		try{
			if (config == null) {
				factory = new Configuration().configure().buildSessionFactory();
			} else {
				factory = new Configuration().configure(config).buildSessionFactory();
			}
		}catch (Throwable ex) {
			// need to change to better handling of errors --> ???
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		return factory;
	}
}
