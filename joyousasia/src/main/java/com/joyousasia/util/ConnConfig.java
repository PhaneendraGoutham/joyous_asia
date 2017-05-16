package com.joyousasia.util;
import java.io.*;
import java.util.*;

public final class ConnConfig
{
	static String filename = "config.properties";
	static Properties prop;
	
	private static String WEB_APP_ROOT = "WEB_APP_ROOT";
	private static String WEB_APP_PATH = "web.app.path";
	private static String LOGO_PATH_SAVE = "logo.path.save";
	
	static{	
		try {
			if ( prop!=null )prop=null;
		 	prop = new Properties();
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)); 
		}catch (Exception e){
			System.out.println("Web Configuration file not found");
			e.printStackTrace();
		}
	}
	
	public static void reloadFile(){
		try {
			synchronized(prop){
			 	prop = new Properties();
				prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)); 
			}
		}catch (IOException e){}
	}

	/**
	 * Retrieve the value of configuration
	 */
	public static String getProperty(String key) {
		synchronized(prop){
			return prop.getProperty(key); 
		}
	}
	
	public static String getWebAppRoot()
	{
		synchronized( prop )
		{
			return prop.getProperty( WEB_APP_ROOT ); 
		}
	}
	
	public static String getWebAppPath()
	{
		synchronized( prop )
		{
			return prop.getProperty( WEB_APP_PATH ); 
		}
	}
	
	public static String getLogoPathSave()
	{
		synchronized( prop )
		{
			return prop.getProperty( LOGO_PATH_SAVE ); 
		}
	}
	
}
