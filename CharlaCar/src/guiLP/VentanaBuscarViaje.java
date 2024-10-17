package guiLP;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaBuscarViaje extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tablaBusqueda;
	private String[] cabecera;
	private String[][] datosEjemplo;
	private JScrollPane scrollPane;
	
	public VentanaBuscarViaje() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 700);
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		
		tablaBusqueda = new JTable( );
		scrollPane = new JScrollPane(tablaBusqueda);
		
		this.add(scrollPane, BorderLayout.CENTER);
	}
}
