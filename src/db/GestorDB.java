package db;

public class GestorDB {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/charlacar.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
//	public GestorBD() {		
//		try {
//			//Cargar el diver SQLite
//			Class.forName(DRIVER_NAME);
//		} catch (ClassNotFoundException ex) {
//			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
//			ex.printStackTrace();
//		}
//	}
	
}
