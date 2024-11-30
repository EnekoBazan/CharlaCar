package db;

import java.sql.SQLException;

public class CreadorBD {

	
public static void main(String[] args) throws SQLException {
		
		System.out.println("Conectando con la base de datos...");
		//GestorDB gestorDB = new GestorDB();

		GestorBD gestorDB= GestorBD.getGestorDB();
		gestorDB.connect();

		//Creacion de tablas
		System.out.println("Creando tablas...");
		gestorDB.crearTablaUsuario();
		gestorDB.crearTablaVehiculos();
		gestorDB.crearTablaViaje();
		gestorDB.crearTablaViajeUsuario();
		System.out.println("Cerrando conexion...");
		
//		gestorDB.getViajes();
		gestorDB.getViajePorId(2);
		gestorDB.getViajeUsuario();
		gestorDB.getViajeUsuarioId(5);

	}
}
