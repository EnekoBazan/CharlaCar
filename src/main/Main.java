package main;

import java.awt.EventQueue;

import db.GestorBD;
import domainLN.Usuario;
import guiLP.VentanaPrincipal;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorBD gestorBD = GestorBD.getGestorDB();
					gestorBD.connect(); // Establece la conexión
			        boolean loginExitoso = gestorBD.existeUsuarioLogin("nombreUsuario", "contraseña123");

			        if (loginExitoso) {
			            System.out.println("Login exitoso.");

			            // Obtener el usuario logeado
			            Usuario usuarioLogeado = gestorBD.getUsuarioLogeado();
			            if (usuarioLogeado != null) {
			                System.out.println("Usuario logeado: " + usuarioLogeado);
			            } else {
			                System.out.println("No se pudo recuperar el usuario logeado.");
			            }
			        } else {
			            System.out.println("Credenciales incorrectas.");
			        }

			        // Cerrar la conexión
			        gestorBD.close();
			    

					new VentanaPrincipal();	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
