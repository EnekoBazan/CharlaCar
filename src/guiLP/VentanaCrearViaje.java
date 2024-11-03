package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domainLN.CharlaCarImpl;
import domainLN.Usuario;
import domainLN.Viaje;

public class VentanaCrearViaje extends JFrame {
	
	private static final long serialVersionUID = 1L;

	//Paneles
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelPrincipalSur = new JPanel();
	private JPanel panelSecundario = new JPanel(new BorderLayout());
	private JPanel panelInfoEast = new JPanel(new GridLayout(5, 2));
	private JPanel panelInfoWest = new JPanel(new BorderLayout());
	
	//Boton
	private JButton btnCrear = new JButton("Crear");
	
	//Label
	private JLabel lbMatricula = new JLabel("Matrícula: ");
	private JLabel lbOrigen = new JLabel("Origen: ");
	private JLabel lbDestino = new JLabel("Destino: ");
	private JLabel lbFecha = new JLabel();
	private JLabel lbAsientoTotal = new JLabel("Asientos totales: ");
	private JLabel lbAsientoDisp = new JLabel("Asientos disponibles: ");
	
	//TextField
	private JTextField txtMatricula = new JTextField();
	private JTextField txtOrigen = new JTextField();
	private JTextField txtDestino = new JTextField();
	private JTextField txtFecha = new JTextField();
	private JTextField txtAsientoTotal = new JTextField();
	private JTextField txtAsientoDisp = new JTextField();

	//Fecha
	private JSpinner fecha = new JSpinner();
	
	//ComboBox
	private String[] numAsientos = { "1", "2", "3", "4", "5", "6", "7", "8"};
	private JComboBox jcAsientosDisp= new JComboBox(numAsientos);

	//Datos
	private Stream<Viaje> datosEjemplo;

	
	public VentanaCrearViaje() {
	
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(750, 450);
	setTitle("CharlaCar");
	setVisible(true);
	setLocationRelativeTo(null);
	
	setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());

	
	panelPrincipal.setBackground(new Color(217, 239, 248));
	panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));
	panelSecundario.setBackground(Color.WHITE);
	panelPrincipal.add(panelSecundario);
	panelPrincipalSur.setBackground(Color.WHITE);
	panelPrincipal.add(panelPrincipalSur, BorderLayout.SOUTH);
	panelPrincipalSur.add(btnCrear);
	btnCrear.setBackground(new Color(33, 150, 243));
	btnCrear.setForeground(Color.WHITE);
	btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
	
	
	panelSecundario.add(panelInfoEast, BorderLayout.CENTER);
	panelSecundario.add(panelInfoWest, BorderLayout.WEST);
	panelInfoEast.setBorder(new EmptyBorder(20, 20, 20, 20));
	panelInfoWest.setBorder(new EmptyBorder(20, 20, 20, 20));

	// PanelInfoWest
//	panelInfoEast.setLayout(new BoxLayout(panelInfoEast,BoxLayout.LINE_AXIS));
//	panelInfoEast.add(Box.createHorizontalStrut(1));
//	panelInfoEast.add(new JSeparator(SwingConstants.VERTICAL));
	panelInfoWest.add(lbMatricula, BorderLayout.CENTER);
	lbMatricula.setText("Matrícula: " + datosEjemplo);
	// PanelInfoEast
	panelInfoEast.add(lbOrigen, BorderLayout.CENTER);
	panelInfoEast.add(txtOrigen, BorderLayout.CENTER);

	panelInfoEast.add(lbDestino, BorderLayout.CENTER);
	panelInfoEast.add(txtDestino, BorderLayout.CENTER);
	lbDestino.setBorder(new EmptyBorder(30, 0, 10, 0));

	panelInfoEast.add(lbAsientoDisp, BorderLayout.CENTER);
	panelInfoEast.add(txtAsientoDisp, BorderLayout.CENTER);
	lbAsientoDisp.setBorder(new EmptyBorder(10, 0, 10, 0));
	
	panelInfoEast.add(lbAsientoTotal, BorderLayout.CENTER);
	panelInfoEast.add(txtAsientoTotal, BorderLayout.CENTER);
	lbAsientoTotal.setBorder(new EmptyBorder(10, 0, 10, 0));
	
//	jcAsientosDisp.setSelectedIndex(8);
	
	this.add(panelPrincipal);
	
	}

}
