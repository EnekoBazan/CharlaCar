package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades extends Properties {

    private static final long serialVersionUID = 1L;
    private final String CONFIG_FILE_PATH = "conf/config.properties";

    public Propiedades() {
        super();
    }

    /**
     * Guarda las propiedades en el archivo especificado.
     */
    public void guardar() {
        try {
            // Directorio "conf" (si no existe, lo crea)
            File configDir = new File("conf");
            if (!configDir.exists()) {
                configDir.mkdir();
            }

            // Configuración por defecto
            setProperty("favicon", "resources/images/favicon.png");
            setProperty("estrellaA1", "resources/images/estrellaA1.png");
            setProperty("estrellaA2", "resources/images/estrellaA2.png");
            setProperty("estrellaA3", "resources/images/estrellaA3.png");
            setProperty("estrellaA4", "resources/images/estrellaA4.png");
            setProperty("estrellaA5", "resources/images/estrellaA5.png");

            // Guardar las propiedades
            try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
                store(output, "Configuración de la aplicación");
                System.out.println("Archivo de configuración guardado exitosamente en: " + CONFIG_FILE_PATH);
            }
        } catch (IOException io) {
            System.err.println("Error al guardar el archivo de configuración: " + io.getMessage());
            io.printStackTrace();
        }
    }

    /**
     * Carga las propiedades desde el archivo especificado.
     */
    public void cargar() {
        File configFile = new File(CONFIG_FILE_PATH);

        if (!configFile.exists()) {
            System.out.println("El archivo de configuración no existe. Creando archivo predeterminado...");
            guardar(); // Crear un archivo predeterminado si no existe
        }

        try (FileInputStream input = new FileInputStream(configFile)) {
            load(input);
            System.out.println("Archivo de configuración cargado desde: " + CONFIG_FILE_PATH);
        } catch (IOException ex) {
            System.err.println("Error al cargar el archivo de configuración: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Obtiene una propiedad o devuelve un valor predeterminado si no existe.
     *
     * @param key           Clave de la propiedad.
     * @param defaultValue  Valor predeterminado si no existe la clave.
     * @return Valor de la propiedad o el valor predeterminado.
     */
    public String getPropertyOrDefault(String key, String defaultValue) {
        return getProperty(key, defaultValue);
    }
}
