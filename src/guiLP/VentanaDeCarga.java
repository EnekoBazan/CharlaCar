package guiLP;

import javax.swing.*;
import java.awt.*;

public class VentanaDeCarga extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelPrincipal = new JPanel();
    public JProgressBar progressBar = new JProgressBar(0, 100);
    private JLabel lCargando = new JLabel("", JLabel.CENTER);
    private JLabel estadoCarga = new JLabel("Iniciando...", JLabel.CENTER);
    private JButton btnCancelar = new JButton("Cancelar");

    private Runnable onCargaCompleta;

    public VentanaDeCarga(Runnable onCargaCompleta, String textoInicial) {
        this.onCargaCompleta = onCargaCompleta;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setTitle("Ventana de Carga");
        setLocationRelativeTo(null);
        setResizable(false);

        lCargando.setText(textoInicial);
        lCargando.setForeground(Color.WHITE);
        estadoCarga.setForeground(Color.WHITE);

        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(76, 175, 80));
        progressBar.setBackground(new Color(217, 239, 248));

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(33, 150, 243));
        panelPrincipal.add(lCargando, BorderLayout.NORTH);
        panelPrincipal.add(progressBar, BorderLayout.CENTER);
        panelPrincipal.add(estadoCarga, BorderLayout.SOUTH);

        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(244, 67, 54));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());

        panelPrincipal.add(btnCancelar, BorderLayout.EAST);
        add(panelPrincipal);

        setVisible(true);
        iniciarCarga();
    }

    private void iniciarCarga() {
        Thread hiloCarga = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(10);
                    progressBar.setValue(i);
                    estadoCarga.setText("Cargando... " + i + "% completado");
                }
                if (onCargaCompleta != null) {
                    onCargaCompleta.run();
                }
            } catch (InterruptedException e) {
                estadoCarga.setText("Carga cancelada.");
            } finally {
                dispose();
            }
        });

        Thread hiloTexto = new Thread(() -> {
            String[] textos = {"Cargando      ", "Cargando .    ", "Cargando . .  ", "Cargando . . ."};
            int index = 0;
            while (progressBar.getValue() < 100) {
                lCargando.setText(textos[index]);
                index = (index + 1) % textos.length;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btnCancelar.addActionListener(e-> hiloCarga.interrupt());

        hiloCarga.start();
        hiloTexto.start();
    }
}
