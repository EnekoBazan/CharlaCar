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
	
	//Crear Tablas
	
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
	
	public void crearTablaViaje() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			Statement stmt = con.createStatement()) {
			
			   String sql = """
				        CREATE TABLE IF NOT EXISTS Viaje (
				            id INTEGER PRIMARY KEY,                
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
	
	public void crearTablaViajeUsuario() {

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			Statement stmt = con.createStatement()) {
			
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
	
	//Inserts
	
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

	public void insertarViaje(Viaje... viajes) {
		if (getConnection() == null) {
			System.err.println("No se puede insertar viajes: conexión no establecida.");
			return;
		}
		
		try {
			String sql = "INSERT INTO Viaje (id, origen, destino, plazas, dni_contuctor) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			for (Viaje viaje : viajes) {
				stmt.setInt(1, viaje.getId());
				stmt.setString(2, viaje.getOrigen());
				stmt.setString(3, viaje.getDestino());
				stmt.setInt(4, viaje.getPlazas());
				if (viaje.getConductor() != null) {
					stmt.setString(5, viaje.getConductor().getDni());
				} else {
					stmt.setNull(5, java.sql.Types.ARRAY);
				}
			}
			
		} catch (SQLException e) {
			System.err.format("\n* Error al insertar viaje: %s", e.getMessage());
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
			
		} catch (SQLException  e) {
	        System.err.format("\n* Error al insertar relación Viaje-Usuario: %s", e.getMessage());
		}
		
	}
	
	//Gets
	
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
            } else {
                System.out.println("\n- No se encontró un viaje con el ID: " + id);
            }
        } catch (SQLException e) {
            System.err.format("\n* Error al recuperar el viaje por ID: %s", e.getMessage());
        }
        
        return viaje;
    }
    public HashMap<Usuario, List<Viaje>> getViajeUsuario() {
    	
    	return null;
    }
    public HashMap<Usuario, List<Viaje>> getViajeUsuarioId() {
    	
    	return null;
    }

}
