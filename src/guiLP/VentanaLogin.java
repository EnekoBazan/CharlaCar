package guiLP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.GestorBD;

import java.awt.Font;

import domainLN.CharlaCarImpl;
import domainLN.Usuario;

public class VentanaLogin extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtUser;
	private JPasswordField passwordField;
	private JButton btnAcceder;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JLabel lblRegistro;

	GestorBD gestorDB = GestorBD.getGestorDB();
	static boolean loged = false;

	public VentanaLogin() {

		setModal(true);

		getContentPane().setLayout(null);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//			@Override
//            public void windowClosing(java.awt.event.WindowEvent e) {
//                JOptionPane.showMessageDialog(null, "No puedes cerrar esta ventana");
//			}
//		});	

		setSize(300, 200);
		setTitle("CharlaCar (LogIn)");
		setResizable(false);

		txtUser = new JTextField();
		txtUser.setBounds(105, 25, 90, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(105, 70, 90, 20);
		getContentPane().add(passwordField);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(30, 25, 90, 15);
		getContentPane().add(lblUsuario);

		lblClave = new JLabel("Clave:");
		lblClave.setBounds(30, 70, 90, 15);
		getContentPane().add(lblClave);

		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(105, 110, 90, 20);

		btnAcceder.setForeground(new Color(33, 150, 243));
		btnAcceder.setBackground(Color.white);
		btnAcceder.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
		btnAcceder.setPreferredSize(new Dimension(60, 25));

		getContentPane().add(btnAcceder);

		lblRegistro = new JLabel("No estas Registrado?");
		lblRegistro.setForeground(Color.BLUE);
		lblRegistro.setFont(new Font("Poppins", Font.BOLD, 10));
		lblRegistro.setBounds(100, 130, 110, 20);
		getContentPane().add(lblRegistro);

		ImageIcon icon = new ImageIcon("resources/images/favicon.png");
		setIconImage(icon.getImage());

		getContentPane().setBackground(new Color(217, 239, 248));

		setLocationRelativeTo(null);

		lblRegistro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaRegistro vRegistro = new VentanaRegistro();
				vRegistro.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {
				lblRegistro.setText("<html><u>No estas Registrado?</u></html>"); // ayuda de copilot
			}

			public void mouseExited(MouseEvent e) {
				lblRegistro.setText("No estas Registrado?");
			}
		});

		btnAcceder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtUser.getText();
				String contraseña = new String(passwordField.getPassword());
				
				try {
					gestorDB.connect(); 

					if (gestorDB.existeUsuarioLogin(nombre, contraseña)) {
						VentanaPrincipal.btnLogIn.setVisible(false);
						VentanaPrincipal.btnRegistro.setVisible(false);
						JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
	                   
						Usuario usuarioLogeado = gestorDB.getUsuarioByNombreAndContraseña(nombre, contraseña);
						
	                    gestorDB.setUsuarioLogeado(usuarioLogeado);
	                    
	             		VentanaPrincipal.btnUsuario.setEnabled(true);
	             		
	             		System.out.println(usuarioLogeado);
	             		
						dispose(); 
					} else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});

//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand().equalsIgnoreCase("acceder")) {
//			for (Usuario user : userService.getListUsers()) {
//				if (user.getNombre().equalsIgnoreCase(txtUser.getText())
//						&& user.getContraseña().equalsIgnoreCase(passwordField.getText())) {
//					JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombre());
//					dispose();
//					VentanaPrincipal vPrincipal = new VentanaPrincipal();
//					vPrincipal.setVisible(true);
//					break;
//				} else {
//					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
//				}
//			}
//		} // else if(e.getActionCommand()) {}
//
//	}
	}
}
