package com.infotech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author kishan Kumar
 */
public class HibernateUtil {
  private static StandardServiceRegistry standardServiceRegistry;
  private static SessionFactory sessionFactory;

  static{
	    if (sessionFactory == null) {
	      try {
	        // Create StandardServiceRegistry
	        standardServiceRegistry = new StandardServiceRegistryBuilder()
	            .configure()
	            .build();
	        // Create MetadataSources
	        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
	        // Create Metadata
	        Metadata metadata = metadataSources.getMetadataBuilder().build();
	        // Create SessionFactory
	        sessionFactory = metadata.getSessionFactoryBuilder().build();
	      } catch (Exception e) {
	        e.printStackTrace();
	        if (standardServiceRegistry != null) {
	          StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
	        }
	      }
	    }
  }
  //Utility method to return SessionFactory
  public static SessionFactory getSessionFactory() {
	  return sessionFactory;
  }
  
  public static Date getDate(String doj) {
		Date date = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			date = dateFormat.parse(doj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}

}