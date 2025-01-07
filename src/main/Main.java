package main;

import java.awt.EventQueue;

import db.GestorBD;
import guiLP.VentanaDeCarga;
import guiLP.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Crear la ventana de carga con un callback para abrir la ventana principal
                new VentanaDeCarga(() -> {
                    try {
                        // Conectar la base de datos durante la carga
                        GestorBD gestorBD = GestorBD.getGestorDB();
                        gestorBD.connect();
                        
                        // Abrir la ventana principal
                        EventQueue.invokeLater(() -> {
                            new VentanaPrincipal();
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Error al conectar con la base de datos.");
                    }
                }, "Iniciando la aplicación...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
