package main;

import java.awt.EventQueue;

import db.GestorBD;
import guiLP.VentanaDeCarga;
import guiLP.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GestorBD gestorBD = GestorBD.getGestorDB();
                gestorBD.connect();

                // Crear la ventana de carga con un callback para abrir la ventana principal
                new VentanaDeCarga(() -> {
                    EventQueue.invokeLater(() -> {
                        new VentanaPrincipal();
                    });
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
