package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.Viaje;

public class GestorBD {

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/charlacar.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	private Connection conexionBD;

	private static GestorBD gestorDB;

	private Usuario usuarioLogeado;

	public static GestorBD getGestorDB() {
		if (gestorDB == null) {
			gestorDB = new GestorBD();
		}
		return gestorDB;
	}

	//
	//
	private GestorBD() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void connect() {
		try {
			conexionBD = DriverManager.getConnection(CONNECTION_STRING);
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
			if (conexionBD != null && !conexionBD.isClosed()) {
				conexionBD.close();
				System.out.println("Conexión a la base de datos cerrada.");
			}
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
		}
	}

	// Obtener el usuario logeado

	public Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public Usuario setUsuarioLogeado(Usuario user) {
		return usuarioLogeado = user;
	}
	// Crear Tablas

	public void crearTablaUsuario() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

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

	public void crearTablaViaje() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = """
					    CREATE TABLE IF NOT EXISTS Viaje (
					        id INTEGER PRIMARY KEY AUTOINCREMENT,
					        origen TEXT NOT NULL,
					        destino TEXT NOT NULL,
					        plazas INTEGER NOT NULL,
					        dni_conductor INTEGER NOT NULL,
					        FOREIGN KEY (dni_conductor) REFERENCES Usuario(dni)
					    );
					""";

			if (!stmt.execute(sql)) {
				System.out.println("- Se ha creado la tabla Viaje");
			}
		} catch (Exception ex) {
			System.err.format("* Error al crear la tabla Viaje: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void crearTablaVehiculos() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

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

	public void crearTablaViajeUsuario() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = """
					    CREATE TABLE IF NOT EXISTS ViajeUsuario (
					        id_viaje INTEGER PRIMARY KEY,
					        dni_usuario TEXT,
					        FOREIGN KEY (dni_usuario) REFERENCES Usuario(dni),
					        FOREIGN KEY(id_viaje) REFERENCES Viaje(id)
					    );
					""";

			if (!stmt.execute(sql)) {
				System.out.println("- Se ha creado la tabla ViajeUsuario");
			}
		} catch (Exception ex) {
			System.err.format("* Error al crear la tabla ViajeUsuario: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}
	// DELETE

	public void deleteUsuario(Usuario usuario) {
		if (getConnection() == null) {
			System.err.println("No se puede eliminar el usuario: conexión no establecida.");
			return;
		}

		String sql = "DELETE FROM Usuario WHERE dni = ?;";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, usuario.getDni());

			if (stmt.executeUpdate() == 1) {
				System.out.format("\n- Usuario eliminado: %s", usuario.toString());
			} else {
				System.out.format("\n- No se ha eliminado el usuario: %s", usuario.toString());
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al eliminar el usuario: %s", e.getMessage());
		}
	}

	// Inserts

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

				stmt.setDouble(6, 0);

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

	public void insertarViaje(Viaje... viajes) {
		if (getConnection() == null) {
			System.err.println("No se puede insertar viajes: conexión no establecida.");
			return;
		}

		String sql = "INSERT INTO Viaje (origen, destino, plazas, dni_conductor) VALUES (?, ?, ?, ?);";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			for (Viaje viaje : viajes) {
				stmt.setString(1, viaje.getOrigen());
				stmt.setString(2, viaje.getDestino());
				stmt.setInt(3, viaje.getPlazas());
				if (viaje.getConductor() != null) {
					stmt.setString(4, viaje.getConductor().getDni());
				} else {
					stmt.setNull(4, java.sql.Types.VARCHAR);
				}

				if (stmt.executeUpdate() == 1) {
					System.out.format("Viaje insertado correctamente: %s%n", viaje);
				} else {
					System.out.format("Error al insertar el viaje: %s%n", viaje);
				}
			}
		} catch (SQLException e) {
			System.err.format("* Error al insertar viaje: %s%n", e.getMessage());
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

	public void insertarViajeUsuario(Viaje viaje, Usuario... usuarios) {
		if (getConnection() == null) {
			System.err.println("No se puede insertar viajes de usuarios: conexión no establecida.");
			return;
		}
		try {
			String sql = "INSERT INTO ViajeUsuario (id_viaje, dni_usuario) VALUES (?, ?);";
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			System.out.print("\n- Insertando relación Viaje-Usuario...");

			for (Usuario usuario : usuarios) {
				stmt.setInt(1, viaje.getId());
				stmt.setString(2, usuario.getDni());

				if (stmt.executeUpdate() == 1) {
					System.out.format("\n - Relación Viaje-Usuario insertada: Viaje ID=%d, Usuario DNI=%s",
							viaje.getId(), usuario.getDni());
				} else {
					System.out.format("\n - No se pudo insertar la relación Viaje-Usuario: Viaje ID=%d, Usuario DNI=%s",
							viaje.getId(), usuario.getDni());
				}
			}

		} catch (SQLException e) {
			System.err.format("\n* Error al insertar relación Viaje-Usuario: %s", e.getMessage());
		}

	}

	// Gets

	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT dni, nombre, apellido, contraseña, carnet, rating FROM Usuario";

		if (getConnection() == null) {
			System.err.println("No se puede obtener usuarios: conexión no establecida.");
			return usuarios;
		}

		try (PreparedStatement stmt = getConnection().prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String contraseña = rs.getString("contraseña");
				boolean carnet = rs.getInt("carnet") == 1;
				float rating = rs.getFloat("rating");

				usuarios.add(new Usuario(dni, nombre, apellido, contraseña, carnet, rating));
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al obtener usuarios: %s", e.getMessage());
		}

		return usuarios;
	}
	
	public Usuario getUsuarioByNombreAndContraseña(String nombre, String contraseña) {
	    String sql = "SELECT dni, nombre, apellido, contraseña, carnet, rating FROM Usuario WHERE nombre = ? AND contraseña = ?";

	    if (getConnection() == null) {
	        System.err.println("No se puede buscar el usuario: conexión no establecida.");
	        return null;
	    }

	    try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
	        stmt.setString(1, nombre);
	        stmt.setString(2, contraseña);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                String dni = rs.getString("dni");
	                String apellido = rs.getString("apellido");
	                boolean carnet = rs.getInt("carnet") == 1;
	                float rating = rs.getFloat("rating");

	                return new Usuario(dni, nombre, apellido, contraseña, carnet, rating);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.format("\n* Error al buscar el usuario con nombre %s y contraseña: %s", nombre, e.getMessage());
	    }

	    return null;
	}
	
	public Usuario getUsuarioByDni(String dni) {
		String sql = "SELECT dni, nombre, apellido, contraseña, carnet, rating FROM Usuario WHERE dni = ?";

		if (getConnection() == null) {
			System.err.println("No se puede buscar el usuario: conexión no establecida.");
			return null;
		}

		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, dni);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String contraseña = rs.getString("contraseña");
					boolean carnet = rs.getInt("carnet") == 1;
					float rating = rs.getFloat("rating");

					return new Usuario(dni, nombre, apellido, contraseña, carnet, rating);
				}
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al buscar el usuario con DNI %s: %s", dni, e.getMessage());
		}

		return null;
	}

	public List<Viaje> getViajes() {
		List<Viaje> viajes = new ArrayList<>();

		String sql = "SELECT id, origen, destino, plazas, dni_conductor FROM Viaje;";

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("\n- Recuperando todos los viajes...");

			while (rs.next()) {
				Viaje viaje = new Viaje();
				viaje.setId(rs.getInt("id"));
				viaje.setOrigen(rs.getString("origen"));
				viaje.setDestino(rs.getString("destino"));
				viaje.setPlazas(rs.getInt("plazas"));

				String dniConductor = rs.getString("dni_conductor");
				if (dniConductor != null) {
					Usuario conductor = new Usuario();
					conductor.setDni(dniConductor);
					viaje.setConductor(conductor);
				}
				viajes.add(viaje);
			}
			System.out.println(viajes);
		} catch (SQLException e) {
			System.err.format("\n* Error al recuperar los viajes: %s", e.getMessage());
		}

		return viajes;
	}

	public Viaje getViajePorId(int id) {
		Viaje viaje = null;

		String sql = "SELECT id, origen, destino, plazas, dni_conductor FROM Viaje WHERE id = ?;";

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				viaje = new Viaje();
				viaje.setId(rs.getInt("id"));
				viaje.setOrigen(rs.getString("origen"));
				viaje.setDestino(rs.getString("destino"));
				viaje.setPlazas(rs.getInt("plazas"));

				String dniConductor = rs.getString("dni_conductor");
				if (dniConductor != null) {
					Usuario conductor = new Usuario();
					conductor.setDni(dniConductor);
					viaje.setConductor(conductor);
				}
				System.out.println(viaje);
			} else {
				System.out.println("\n- No se encontró un viaje con el ID: " + id);
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al recuperar el viaje por ID: %s", e.getMessage());
		}

		return viaje;
	}

	public List<Vehiculo> getVehiculos() {
		List<Vehiculo> vehiculos = new ArrayList<>();
		String sql = "SELECT matricula, plazas, propietario FROM Vehiculo";

		if (getConnection() == null) {
			System.err.println("No se puede obtener vehículos: conexión no establecida.");
			return vehiculos;
		}

		try (PreparedStatement stmt = getConnection().prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String matricula = rs.getString("matricula");
				int plazas = rs.getInt("plazas");
				String propietarioDni = rs.getString("propietario");

				Usuario propietario = getUsuarioByDni(propietarioDni); // Recuperar el usuario propietario
				vehiculos.add(new Vehiculo(matricula, plazas, propietario));
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al obtener vehículos: %s", e.getMessage());
		}

		return vehiculos;
	}

	public Vehiculo getVehiculoPorUsuario(String dniUsuario) {
		String sql = "SELECT matricula, plazas, propietario FROM Vehiculo WHERE propietario = ?";
		Vehiculo vehiculo = null;

		if (getConnection() == null) {
			System.err.println("No se puede obtener el vehículo: conexión no establecida.");
			return null;
		}

		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, dniUsuario);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String matricula = rs.getString("matricula");
					int plazas = rs.getInt("plazas");
					String propietarioDni = rs.getString("propietario");

					Usuario propietario = getUsuarioByDni(propietarioDni);
					vehiculo = new Vehiculo(matricula, plazas, propietario);
					System.out.format("\n- Vehículo recuperado para el usuario con DNI %s: %s", dniUsuario,
							vehiculo.toString());
				} else {
					System.out.format("\n- No se encontró vehículo para el usuario con DNI %s.", dniUsuario);
				}
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al obtener el vehículo del usuario con DNI %s: %s", dniUsuario,
					e.getMessage());
		}

		return vehiculo;
	}

	public HashMap<Usuario, List<Viaje>> getViajeUsuario() {
		HashMap<Usuario, List<Viaje>> mapaViajeUsuario = new HashMap<>();
		String sql = """
				    SELECT u.dni, u.nombre, u.apellido, u.contraseña, u.carnet, u.rating,
				           v.id, v.origen, v.destino, v.plazas, v.dni_conductor
				    FROM ViajeUsuario vu
				    JOIN Usuario u ON vu.dni_usuario = u.dni
				    JOIN Viaje v ON vu.id_viaje = v.id
				""";

		if (getConnection() == null) {
			System.err.println("No se puede recuperar la relación Viaje-Usuario: conexión no establecida.");
			return mapaViajeUsuario;
		}

		try (PreparedStatement stmt = getConnection().prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				// Usuario
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String contraseña = rs.getString("contraseña");
				boolean carnet = rs.getInt("carnet") == 1;
				float rating = rs.getFloat("rating");

				Usuario usuario = new Usuario(dni, nombre, apellido, contraseña, carnet, rating);

				// Viaje
				int id = rs.getInt("id");
				String origen = rs.getString("origen");
				String destino = rs.getString("destino");
				int plazas = rs.getInt("plazas");
				String dniConductor = rs.getString("dni_conductor");

				Usuario conductor = dniConductor != null ? getUsuarioByDni(dniConductor) : null;
				Viaje viaje = new Viaje(id, origen, destino, plazas, conductor, null);

				mapaViajeUsuario.putIfAbsent(usuario, new ArrayList<>());
				mapaViajeUsuario.get(usuario).add(viaje);
			}
			System.out.println(mapaViajeUsuario);
		} catch (SQLException e) {
			System.err.format("\n* Error al recuperar la relación Viaje-Usuario: %s", e.getMessage());
		}

		return mapaViajeUsuario;
	}

	public HashMap<Usuario, List<Viaje>> getViajeUsuarioId(int id) {
		HashMap<Usuario, List<Viaje>> mapaViajesPorUsuarioId = new HashMap<>();

		if (getConnection() == null) {
			System.err.println("No se puede recuperar la relación Viaje-Usuario: conexión no establecida.");
			return mapaViajesPorUsuarioId;
		}

		String sql = """
				    SELECT Vu.dni_usuario, U.nombre, U.apellido, U.contraseña, U.carnet, U.rating,
				           V.id, V.origen, V.destino, V.plazas, V.dni_conductor
				    FROM ViajeUsuario Vu
				    JOIN Usuario U ON Vu.dni_usuario = U.dni
				    JOIN Viaje V ON Vu.id_viaje = V.id
				    WHERE V.id = ?;
				""";

		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					// Usuario
					String dni = rs.getString("dni_usuario");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String contraseña = rs.getString("contraseña");
					boolean carnet = rs.getInt("carnet") == 1;
					float rating = rs.getFloat("rating");

					Usuario usuario = new Usuario(dni, nombre, apellido, contraseña, carnet, rating);

					// Viaje
					int viajeId = rs.getInt("id");
					String origen = rs.getString("origen");
					String destino = rs.getString("destino");
					int plazas = rs.getInt("plazas");
					String dniConductor = rs.getString("dni_conductor");

					Usuario conductor = dniConductor != null ? getUsuarioByDni(dniConductor) : null;
					Viaje viaje = new Viaje(viajeId, origen, destino, plazas, conductor, null);

					mapaViajesPorUsuarioId.computeIfAbsent(usuario, k -> new ArrayList<>()).add(viaje);
				}
				System.out.println(mapaViajesPorUsuarioId);
			}

		} catch (SQLException e) {
			System.err.format("\n* Error al recuperar la relación Viaje-Usuario con ID %d: %s", id, e.getMessage());
		}

		return mapaViajesPorUsuarioId;
	}

	// Resto de funciones

	public boolean existeUsuarioLogin(String nombre, String contraseña) {
		String sql = "SELECT * FROM Usuario WHERE nombre = ? AND contraseña = ?";

		if (getConnection() == null) {
			System.err.println("No se puede verificar el usuario: conexión no establecida.");
			return false;
		}
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, nombre);
			pstmt.setString(2, contraseña);

			try (ResultSet rs = pstmt.executeQuery()) {
				return true;
			}
		} catch (SQLException e) {
			System.err.format("\n* Error al verificar las credenciales del usuario: %s", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
