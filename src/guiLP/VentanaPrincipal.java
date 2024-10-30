package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.border.LineBorder;

import domainLN.CharlaCarImpl;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JButton btnCrear;
	private JButton btnBuscar;
	private JButton btnLogIn;
	private JButton btnRegistro;
	private JButton btnUsuario;
	
	private JPanel panelCentral;
	private JPanel panelPrincipal;
	private JPanel panelTop;
	private JPanel panelBottom;
	private JPanel panelBotonBuscar;
	private JPanel panelBotonCrear;
	
	private JLabel lblTituloBuscar;
	private JLabel lblTituloCrear;
	
	private JPanel panelTextosBuscar;
	private JLabel lblTextoBuscar;
	private JLabel lblTextoBuscar1;
	private JLabel lblTextoBuscar2;
	
	private JPanel panelTextosCrear;
	private JLabel lblTextoCrear;
	private JLabel lblTextoCrear1;
	private JLabel lblTextoCrear2;
	
	private JLabel lblTextoAbajo1;
	private JLabel lblTextoAbajo2;
	
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
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png"));//imagen generada con IA
		setIconImage(icon.getImage());
		
		logger = Logger.getLogger(VentanaPrincipal.class.getName());
		
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(237, 242, 255));
		
		////////Panel inferior
		panelBottom = new JPanel(new GridLayout(2,1, 10, 5));
		
		lblTextoAbajo1 = new JLabel("👥 +50,000 usuarios | 🚗 +1,000 viajes diarios | 💰 -40% en costes | 🌍 Viajes en toda España ");
		lblTextoAbajo1.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto
		lblTextoAbajo1.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); 
		//lblTexto1.setFont(new Font("Arial", Font.BOLD, 11)); //no se ven los iconos
		
		lblTextoAbajo2 = new JLabel("🔒 Viajes seguros y verificados | ⭐ 4.8/5 valoración media");
		lblTextoAbajo2.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto
		lblTextoAbajo2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		//lblTexto2.setFont(new Font("Arial", Font.BOLD, 11)); //no se ven los iconos
		
		panelBottom.add(lblTextoAbajo1);
		panelBottom.add(lblTextoAbajo2);
		panelBottom.setBackground(new Color(217, 239, 248 ));
		panelPrincipal.add(panelBottom, BorderLayout.SOUTH);
		////////
		
		panelTop = new JPanel(new BorderLayout());
		panelTop.setBackground(Color.LIGHT_GRAY);
		JPanel panelTop1 = new JPanel(new BorderLayout());
		JPanel panelTop2 = new JPanel(new BorderLayout());

		panelTop.add(panelTop1, BorderLayout.WEST);
		panelTop.add(panelTop2, BorderLayout.CENTER);
		
		
		///////////BUSCRA Y CREAR VIAJES
		panelCentral = new JPanel(new GridLayout(1,2, 20, 0));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
		panelCentral.setBackground(new Color(217, 239, 248));
		
		panelBotonCrear = new JPanel(new BorderLayout(0,10));
		panelBotonCrear.setBackground(Color.WHITE);
		panelBotonCrear.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 20));
		
		//Ceancion de labels para el panel de crear
		lblTituloCrear = new JLabel("¿Eres conductor?");
		lblTituloCrear.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloCrear.setForeground(new Color(33, 150, 243));
		lblTituloCrear.setFont(new Font("Arial", Font.BOLD, 18));
		
		lblTextoCrear = new JLabel("• Crea tu viaje y comparte gastos");
		lblTextoCrear.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoCrear.setForeground(Color.darkGray);
		lblTextoCrear.setFont(new Font("Arial", Font.BOLD, 12));
		
		lblTextoCrear1 = new JLabel("• Publica tu viaje y no viajes solo");
		lblTextoCrear1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoCrear1.setForeground(Color.darkGray);
		lblTextoCrear1.setFont(new Font("Arial", Font.BOLD, 12));
		
		lblTextoCrear2 = new JLabel("• Elige tus preferencias y horarios");
		lblTextoCrear2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoCrear2.setForeground(Color.darkGray);
		lblTextoCrear2.setFont(new Font("Arial", Font.BOLD, 12));
		
		btnCrear = new JButton("Crear Viaje");
		btnCrear.setBackground(new Color(33, 150, 243));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
		btnCrear.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		panelBotonCrear.add(lblTituloCrear, BorderLayout.NORTH);
		panelBotonCrear.add(lblTextoCrear, BorderLayout.CENTER);
		panelTextosCrear = new JPanel(new GridLayout(3, 1, 5, 5));
		panelTextosCrear.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 35));
		panelTextosCrear.setBackground(Color.WHITE);
		panelTextosCrear.add(lblTextoCrear);
		panelTextosCrear.add(lblTextoCrear1);
		panelTextosCrear.add(lblTextoCrear2);
		panelBotonCrear.add(panelTextosCrear, BorderLayout.CENTER);
		panelBotonCrear.add(btnCrear, BorderLayout.SOUTH);
		
		JPanel buscarContenido = new JPanel(new BorderLayout(0, 10));
		buscarContenido.setBackground(Color.WHITE);
		
		panelBotonBuscar = new JPanel(new BorderLayout(0,10));
		panelBotonBuscar.setBackground(Color.WHITE);
		panelBotonBuscar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 15));
		
		//crearcion de labels para el panel buscar
		lblTituloBuscar = new JLabel("¿Buscas un viaje?");
		lblTituloBuscar.setForeground(new Color(33, 150, 243));
		lblTituloBuscar.setFont(new Font("Arial", Font.BOLD, 18));
		lblTituloBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTextoBuscar = new JLabel("• Encuentra el viaje perfecto para ti");
		lblTextoBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoBuscar.setForeground(Color.darkGray);
		lblTextoBuscar.setFont(new Font("Arial", Font.BOLD, 12));

		lblTextoBuscar1 = new JLabel("• Viaja de forma económica y sostenible");
		lblTextoBuscar1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoBuscar1.setForeground(Color.darkGray);
		lblTextoBuscar1.setFont(new Font("Arial", Font.BOLD, 12));

		lblTextoBuscar2 = new JLabel("• Conecta con conductores verificados");
		lblTextoBuscar2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoBuscar2.setForeground(Color.darkGray);
		lblTextoBuscar2.setFont(new Font("Arial", Font.BOLD, 12));
		
		btnBuscar = new JButton("Buscar Viaje");
		btnBuscar.setBackground(new Color(33, 150, 243));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
		btnBuscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		panelBotonBuscar.add(lblTituloBuscar, BorderLayout.NORTH);
		panelTextosBuscar = new JPanel(new GridLayout(3, 1, 0, 0));
		panelTextosBuscar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		panelTextosBuscar.setBackground(Color.WHITE);
		panelTextosBuscar.add(lblTextoBuscar);
		panelTextosBuscar.add(lblTextoBuscar1);
		panelTextosBuscar.add(lblTextoBuscar2);
		panelBotonBuscar.add(panelTextosBuscar, BorderLayout.CENTER);
		panelBotonBuscar.add(btnBuscar, BorderLayout.SOUTH);
		
		panelCentral.add(panelBotonCrear);
		panelCentral.add(panelBotonBuscar);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		/////////////////
		
		btnLogIn = new JButton("LogIn");
		btnLogIn.setForeground(new Color(33, 150, 243 ));
		btnLogIn.setBackground(Color.white);
//		btnLogIn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnLogIn.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
		btnLogIn.setPreferredSize(new Dimension(60, 25));
		panelTop1.add(btnLogIn);
		
		btnRegistro = new JButton("Registrarse");
		btnRegistro.setForeground(new Color(33, 150, 243 ));
		btnRegistro.setBackground(Color.white);
		btnRegistro.setPreferredSize(new Dimension(95, 25));
		btnRegistro.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
		//btnRegistro.setBorder(new LineBorder(Color.black));
		panelTop1.add(btnRegistro);
		panelPrincipal.add(panelTop, BorderLayout.NORTH);
		
		btnUsuario = new JButton("Usuario");
		btnUsuario.setForeground(new Color(33, 150, 243 ));
		btnUsuario.setBackground(Color.white);
		btnUsuario.setPreferredSize(new Dimension(70, 25));
		btnUsuario.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
		
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

		
		panelTop2.add(btnRegistro, BorderLayout.WEST);
		panelTop1.setBorder(new EmptyBorder(10,35,10,10));
		panelTop1.setBackground(new Color(217, 239, 248 ));
		panelTop1.add(btnLogIn, BorderLayout.WEST);
		panelTop2.add(btnUsuario, BorderLayout.EAST);
		panelTop2.setBorder(new EmptyBorder(10,0,10,35));
		panelTop2.setBackground(new Color(217, 239, 248 ));


		//ACTIVAR EL LISTENER DE CADA OBJETO
		btnLogIn.addActionListener(this);
		btnRegistro.addActionListener(this);
	    btnUsuario.addActionListener(this);
	    btnBuscar.addActionListener(this);
	    btnCrear.addActionListener(this);
	    menuItemPerfil.addActionListener(this);
		
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
		}else if(e.getActionCommand().equalsIgnoreCase("Buscar viaje")) {
			logger.info("Boton buscar viaje");
			VentanaBuscarViaje vBuscarViaje = new VentanaBuscarViaje();
			vBuscarViaje.setVisible(true);
		}else if(e.getActionCommand().equalsIgnoreCase("Crear viaje")) {
			logger.info("Boton crear viaje");
			VentanaCrearViaje vCrearViaje = new VentanaCrearViaje();
			vCrearViaje.setVisible(true);
//		}else if(e.getActionCommand().equalsIgnoreCase("Usuario")) {
//			JComponent source = (JComponent) e.getSource();
//			JPopupMenu popupMenu = menuUsuario.getPopupMenu();
//			popupMenu.show(source,0, source.getHeight());
//			logger.info("Has abierto el menu 'Usuario'");
			
//			if(e.getActionCommand().equalsIgnoreCase("Perfil")) {
//                VentanaPerfil vPerfil = new VentanaPerfil();
//                vPerfil.setVisible(true);
//			} else if (e.getActionCommand().equalsIgnoreCase("Cerrar sesión")) {
//				// Cerrar sesion
//			}
		}
		menuItemPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPerfil vPerfil = new VentanaPerfil();
				vPerfil.setVisible(true);
				
			}
		});
		btnUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuUsuario.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
				logger.info("Has abierto el menu 'Usuario'");				
			}
		});
	}
	
	
}
