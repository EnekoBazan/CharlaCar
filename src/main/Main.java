package main;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import guiLP.VentanaPrincipal;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new VentanaPrincipal();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// Crear un HashMap para almacenar los usuarios (nombreUsuario -> contraseña)
		Map<String, String> usuariosMap = new HashMap<>();
        
        // Definir cuántos usuarios crear
        int numeroUsuarios = 10; // Por ejemplo, creamos 10 usuarios
        
        // Agregar usuarios al mapa (usuario1 -> contraseña1, usuario2 -> contraseña2, ...)
        for (int i = 1; i <= numeroUsuarios; i++) {
            String nombreUsuario = "usuario" + i;
            String contraseña = "contraseña" + i;  // Puedes cambiar la lógica de generación de contraseñas
            usuariosMap.put(nombreUsuario, contraseña);
        }

        // Mostrar los usuarios y contraseñas almacenados en el mapa
        for (Map.Entry<String, String> entry : usuariosMap.entrySet()) {
            System.out.println("Usuario: " + entry.getKey() + " - Contraseña: " + entry.getValue());
        }

        // Ejemplo de verificación de login
        String usuarioLogin = "usuario5";
        String contraseñaLogin = "contraseña5";
        
        if (usuariosMap.containsKey(usuarioLogin) && usuariosMap.get(usuarioLogin).equals(contraseñaLogin)) {
            System.out.println("Login exitoso para: " + usuarioLogin);
        } else {
            System.out.println("Nombre de usuario o contraseña incorrecta");
        }
	}
}
