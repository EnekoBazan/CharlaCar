package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JButton btnCrearViaje;
	private JButton btnBuscarViaje;
	private JButton btnLogIn;
	private JButton btnRegistro;
	private JButton btnUsuario;
	
	private JPanel panelCentral;
	private JPanel panelPrincipal;
	private JPanel panelTop;
	private JPanel panelBottom;
	
	private JLabel lblTexto1;
	private JLabel lblTexto2;
	
	private Logger logger;
	
	//Barra de menus
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuUsuario = new JMenu("Usuario");
	//Elementos JMenuJuegos
	private JMenuItem menuItemPerfil = new JMenuItem("Perfil");
	private JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar sesión");
	
	public VentanaPrincipal()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 700, 750, 500);
		
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png"));//imagen generada con IA
		setIconImage(icon.getImage());
		
		logger = Logger.getLogger(VentanaPrincipal.class.getName());
		
		panelCentral = new JPanel(new GridLayout(2,2, 0, 5));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		
		panelPrincipal = new JPanel(new BorderLayout());
		
		
		////////Panel inferior
		panelBottom = new JPanel(new GridLayout(2,1, 10, 5));
		
		lblTexto1 = new JLabel("👥 +50,000 usuarios | 🚗 +1,000 viajes diarios | 💰 -40% en costes | 🌍 Viajes en toda España ");
		lblTexto1.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto
		lblTexto1.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); 
		//lblTexto1.setFont(new Font("Arial", Font.BOLD, 11)); //no se ven los iconos
		
		lblTexto2 = new JLabel("🔒 Viajes seguros y verificados | ⭐ 4.8/5 valoración media");
		lblTexto2.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto
		lblTexto2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		//lblTexto2.setFont(new Font("Arial", Font.BOLD, 11)); //no se ven los iconos
		
		panelBottom.add(lblTexto1);
		panelBottom.add(lblTexto2);
		panelPrincipal.add(panelBottom, BorderLayout.SOUTH);
		////////
		
		panelTop = new JPanel(new BorderLayout());
		panelTop.setBackground(Color.LIGHT_GRAY);
		JPanel panelTop1 = new JPanel(new BorderLayout());
		JPanel panelTop2 = new JPanel(new BorderLayout());

		panelTop.add(panelTop1, BorderLayout.EAST);
		panelTop.add(panelTop2, BorderLayout.CENTER);

		btnUsuario = new JButton("Usuario");
		
		btnCrearViaje = new JButton("Crear Viaje");
		//btnCrearViaje.setIcon(icon); //icono del boton
		panelCentral.add(btnCrearViaje);
				
		btnBuscarViaje = new JButton("Buscar Viaje");
		panelCentral.add(btnBuscarViaje);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		
		btnLogIn = new JButton("LogIn");
		panelTop2.add(btnLogIn);
		
		btnRegistro = new JButton("Registrarse");
		panelTop2.add(btnRegistro);
		panelPrincipal.add(panelTop, BorderLayout.NORTH);
		
		// Configuración JMenuUsuario
		menuBar.setVisible(false); // Oculta la barra de menu, se abre con botones Juegos/Usuarios
		menuBar.add(menuUsuario);
		menuUsuario.setMnemonic(KeyEvent.VK_F);
		// Items del menu "Usuario"
		menuUsuario.add(menuItemPerfil);
		menuItemPerfil.setMnemonic(KeyEvent.VK_S);
		menuUsuario.addSeparator();
		menuUsuario.add(menuItemCerrarSesion);
		menuItemCerrarSesion.setMnemonic(KeyEvent.VK_S);
//		btnPerfil = new JButton("Perfil");
		
		panelTop1.add(btnUsuario, BorderLayout.EAST);
		panelTop1.setBorder(new EmptyBorder(10,10,10,10));
		panelTop1.setBackground(Color.LIGHT_GRAY);
		panelTop2.add(btnLogIn, BorderLayout.EAST);
		panelTop2.add(btnRegistro, BorderLayout.WEST);
		panelTop2.setBorder(new EmptyBorder(10,10,10,10));
		panelTop2.setBackground(Color.LIGHT_GRAY);


		//ACTIVAR EL LISTENER DE CADA OBJETO
		btnLogIn.addActionListener(this);
		btnRegistro.addActionListener(this);
	    btnUsuario.addActionListener(this);
	    btnBuscarViaje.addActionListener(this);
	    btnCrearViaje.addActionListener(this);
		
		this.add(panelPrincipal);
		
	//	this.validate();//repaint pantalla
		
//		btnUsuario.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JComponent source = (JComponent) e.getSource();
//				JPopupMenu popupMenu = menuUsuario.getPopupMenu();
//				popupMenu.show(source,0, source.getHeight());
//				logger.info("Has abierto el menu 'Usuario'");
//			}
//		});
	}
	
	
	//ARRIBA PARA LLAMAM A LA LLAMADA CON EL OBJETO
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("logIn")) {
			logger.info("Boton LogIn pulsado");
			VentanaLogin vLogin = new VentanaLogin();
			vLogin.setVisible(true);
		}else if (e.getActionCommand().equalsIgnoreCase("registrarse")){
			logger.info("Boton Registro pulsado");
			VentanaRegistro vRegistro = new VentanaRegistro();
			vRegistro.setVisible(true);
		}else if (e.getActionCommand().equalsIgnoreCase("perfil")) {
			logger.info("Boton Perfil pulsado");
			VentanaPerfil vPerfil = new VentanaPerfil();
			vPerfil.setVisible(true);
		}else if(e.getActionCommand().equalsIgnoreCase("Buscar viaje")) {
			logger.info("Boton buscar viaje");
			VentanaBuscarViaje vBuscarViaje = new VentanaBuscarViaje();
			vBuscarViaje.setVisible(true);
		}else if(e.getActionCommand().equalsIgnoreCase("Crear viaje")) {
			logger.info("Boton crear viaje");
			VentanaCrearViaje vCrearViaje = new VentanaCrearViaje();
			vCrearViaje.setVisible(true);
		}else if(e.getActionCommand().equalsIgnoreCase("Usuario")) {
			JComponent source = (JComponent) e.getSource();
			JPopupMenu popupMenu = menuUsuario.getPopupMenu();
			popupMenu.show(source,0, source.getHeight());
			logger.info("Has abierto el menu 'Usuario'");		
		}
	}
}
