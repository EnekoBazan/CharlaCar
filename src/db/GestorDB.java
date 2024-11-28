package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import domainLN.Usuario;
import domainLN.Vehiculo;

public class GestorDB {

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/charlacar.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	private Connection conexionBD;

	//
	//
	
	public void GestorBD() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void connect() {
		try {
			conexionBD = DriverManager.getConnection(DATABASE_FILE);
			System.out.println("Conexión a la base de datos establecida.");
		} catch (SQLException e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}

	public Connection getConnection() {
		return conexionBD;
	}

	public void close() {
		try {
			if (conexionBD != null) {
				conexionBD.close();
				System.out.println("Conexión a la base de datos cerrada.");
			}
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
		}
	}
	public void crearTablaVehiculos() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			Statement stmt = con.createStatement()) {
			
		    String sql = """
		            CREATE TABLE IF NOT EXISTS Vehiculo (
		                matricula TEXT PRIMARY KEY,
		                plazas INTEGER NOT NULL CHECK (plazas > 0),
		                propietario TEXT,
		                FOREIGN KEY (propietario) REFERENCES Usuario(dni)
		            );
		        """;
	        	        
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Vehiculo");
	        }
		} catch (Exception ex) {
			System.err.format("* Error al crear la tabla Vehiculo: %s", ex.getMessage());
			ex.printStackTrace();			
		}
	}
	
	public void crearTablaUsuario() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			Statement stmt = con.createStatement()) {
			
			   String sql = """
				        CREATE TABLE IF NOT EXISTS Usuario (
				            dni TEXT PRIMARY KEY,                
				            nombre TEXT NOT NULL,              
				            apellido TEXT NOT NULL,            
				            contraseña TEXT NOT NULL,          
				            carnet INTEGER NOT NULL CHECK (carnet IN (0, 1)),
				            rating REAL NOT NULL                
				        );
				    """;
	        	        
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Usuario");
	        }
		} catch (Exception ex) {
			System.err.format("* Error al crear la tabla Usuario: %s", ex.getMessage());
			ex.printStackTrace();			
		}
	}
	

	public void insertarUsuarios(Usuario... usuarios) {

		try {

			String sql = "INSERT INTO Usuario (dni, nombre, apellido, contraseña, carnet, rating) VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			System.out.print("\n- Insertando Usuarios...");

			for (Usuario u : usuarios) {
				stmt.setString(1, u.getDni());
				stmt.setString(2, u.getNombre());
				stmt.setString(3, u.getApellido());
				stmt.setString(4, u.getContraseña());

				int carnet = u.isCarnet() ? 1 : 0;
				stmt.setInt(5, carnet);

				stmt.setDouble(6, u.getRating());

				if (stmt.executeUpdate() == 1) {
					System.out.format("\n - Usuario insertado: %s", u.toString());
				} else {
					System.out.format("\n - No se ha insertado el cliente: %s", u.toString());
				}
			}
		} catch (Exception ex) {
			System.err.format("\n* Error al insertar datos de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void insertarVehiculo(Vehiculo... vehiculos) {
		if (getConnection() == null) {
			System.err.println("No se puede insertar vehículos: conexión no establecida.");
			return;
		}

		try {
			String sql = "INSERT INTO Vehiculo (matricula, plazas, propietario) VALUES (?, ?, ?);";
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			for (Vehiculo v : vehiculos) {
				stmt.setString(1, v.getMatricula());
				stmt.setInt(2, v.getPlazas());

				if (v.getPropietario() != null) {
					stmt.setString(3, v.getPropietario().getDni());
				} else {
					stmt.setNull(3, java.sql.Types.ARRAY);
				}
				if (stmt.executeUpdate() == 1) {
					System.out.format("\n - Vehículo insertado: %s", v.toString());
				} else {
					System.out.format("\n - No se ha insertado el vehículo: %s", v.toString());
				}
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al insertar vehículo: %s", e.getMessage());
		}
	}

}
