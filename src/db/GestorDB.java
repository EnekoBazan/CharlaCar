package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domainLN.Usuario;

public class GestorDB {

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/charlacar.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	private Connection conexionBD;

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

}
