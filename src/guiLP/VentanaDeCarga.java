package guiLP;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;

public class VentanaDeCarga extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelPrincipal = new JPanel();
    public JProgressBar progressBar = new JProgressBar(0, 100);
    private JLabel lCargando = new JLabel("Cargando", JLabel.CENTER);

    // Callback al completarse la carga
    private Runnable onCargaCompleta;

    public VentanaDeCarga(Runnable onCargaCompleta) {
        this.onCargaCompleta = onCargaCompleta; // Asignar el callback
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(230, 100);
        setTitle("Cargando...");
        setLocationRelativeTo(null);
        setResizable(false);

        // PanelPrincipal
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(lCargando, BorderLayout.NORTH);
        progressBar.setStringPainted(true);
        panelPrincipal.add(progressBar, BorderLayout.CENTER);
        add(panelPrincipal);

        setVisible(true);

        iniciarCarga();
    }

    private void iniciarCarga() {
        // Hilo de ProgressBar
        Thread hiloCarga = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(10); // Simula el tiempo de carga
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(i);
            }

            if (onCargaCompleta != null) {
                onCargaCompleta.run();
            }

            dispose();
        });

        // Hilo de texto "Cargando..."
        Thread hiloTexto = new Thread(() -> {
            String[] textos = {"Cargando      ", "Cargando .    ", "Cargando . .  ", "Cargando . . ."};
            int index = 0;

            while (progressBar.getValue() < 100) {
                lCargando.setText(textos[index]);
                index = (index + 1) % textos.length;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciar los hilos
        hiloCarga.start();
        hiloTexto.start();
    }
}
