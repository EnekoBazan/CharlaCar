package db;

import java.sql.SQLException;

/**
 * Clase para la creación de la base de datos y sus tablas.
 */
public class CreadorBD {

    public static void main(String[] args) {
        System.out.println("Iniciando la configuración de la base de datos...");

        GestorBD gestorDB = GestorBD.getGestorDB();
        try {
            conectarBaseDeDatos(gestorDB);
            crearTablas(gestorDB);
            realizarConsultasEjemplo(gestorDB);
        } catch (SQLException e) {
            System.err.println("Error durante la configuración de la base de datos: " + e.getMessage());
        } finally {
            cerrarConexion(gestorDB);
        }
    }

    private static void conectarBaseDeDatos(GestorBD gestorDB) throws SQLException {
        System.out.println("Conectando con la base de datos...");
        gestorDB.connect();
        System.out.println("Conexión establecida con éxito.");
    }

    private static void crearTablas(GestorBD gestorDB) throws SQLException {
        System.out.println("Creando tablas necesarias...");
        gestorDB.crearTablaUsuario();
        gestorDB.crearTablaVehiculos();
        gestorDB.crearTablaViaje();
        gestorDB.crearTablaViajeUsuario();
        System.out.println("Tablas creadas exitosamente.");
    }

    private static void realizarConsultasEjemplo(GestorBD gestorDB) throws SQLException {
        System.out.println("Ejecutando consultas de ejemplo...");
        gestorDB.getViajePorId(2);
        gestorDB.getViajeUsuario();
        gestorDB.getViajeUsuarioId(5);
        System.out.println("Consultas de ejemplo ejecutadas.");
    }

    private static void cerrarConexion(GestorBD gestorDB) {
        gestorDB.close();
		System.out.println("Conexión cerrada correctamente.");
    }
}
