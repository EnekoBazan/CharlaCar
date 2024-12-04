package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Propiedades() {

		super();

	}
	public void guardar() {
		
		//images
		setProperty("favicon", "resources/images/favicon.png");
		
		//estrellas
		setProperty("estrellaA1", "resources/images/estrellaA1.png");
		
		try (FileOutputStream output = new FileOutputStream("conf/config.properties")) {
			store(output, "Configuración de la base de datos");
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	public void cargar() {
		try (FileInputStream input = new FileInputStream("conf/config.properties")) {
			load(input);
			// Leer propiedades
//			System.out.println("URL: " + getProperty("database.url"));
//			System.out.println("Usuario: " + getProperty("database.usuario"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
