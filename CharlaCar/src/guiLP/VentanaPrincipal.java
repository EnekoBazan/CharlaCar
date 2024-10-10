package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JDialog ventanaLogin;
	private JButton btnCrearViaje;
	private JButton btnBuscarViaje;
	private JButton btnLogIn;
	private JButton btnRegistro;
	private JPanel panelCentral;
	private JPanel panelPrincipal;
	private JPanel panelTop;
	
	public VentanaPrincipal()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 700, 750, 500);
		
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/FotoPerfil.png"));//imagen generada con IA
		setIconImage(icon.getImage());
		
		panelCentral = new JPanel(new GridLayout(2, 2, 10, 10));
		panelCentral.setBorder(BorderFactory.createTitledBorder("CharlaCar"));
		
		panelPrincipal = new JPanel(new BorderLayout());
		
		panelTop = new JPanel(new FlowLayout());
		panelTop.setBackground(Color.BLUE);
		
		JPanel espacio = new JPanel();
		panelCentral.add(espacio);
		
		btnCrearViaje = new JButton("Crear Viaje");
		//btnCrearViaje.setIcon(icon); //icono del boton
		panelCentral.add(btnCrearViaje);
		
		JPanel espacio2 = new JPanel();
		panelCentral.add(espacio2);
		
		btnBuscarViaje = new JButton("Buscar Viaje");
		panelCentral.add(btnBuscarViaje);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		
		btnLogIn = new JButton("LogIn");
		panelTop.add(btnLogIn);
		
		btnRegistro = new JButton("Registrarse");
		panelTop.add(btnRegistro);
		panelPrincipal.add(panelTop, BorderLayout.NORTH);
		
		btnLogIn.addActionListener(this);
		btnRegistro.addActionListener(this);
		
		this.add(panelPrincipal);
		
		ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible( true );
		
	//	this.validate();//repaint pantalla
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("logIn")) {
			VentanaLogin vLogin = new VentanaLogin();
			vLogin.setVisible(true);
		}else if (e.getActionCommand().equalsIgnoreCase("registrarse")){
			VentanaRegistro vRegistro = new VentanaRegistro();
			vRegistro.setVisible(true);
		}
		
	}

}
