package guiLP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
//import javax.swing.JSlider;
import javax.swing.JTextField;

import db.GestorBD;
import domainLN.Usuario;
import domainLN.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;

public class VentanaRegistro extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JPasswordField passwordField;
	private JCheckBox checkCarnet;
//    private JSlider sliderRating;
//    private JLabel lblRatingValue;
	private JButton btnRegistrar;
	private JLabel lblLogin;
	
	private JLabel lblMatricula;
	private JLabel lblAsientos;
	private JTextField txtMatricula;
	private JTextField txtAsientos;
	

	GestorBD gestorDB = GestorBD.getGestorDB();

	public VentanaRegistro() {
		setModal(true);
		setSize(700, 300);
		setTitle("CharlaCar (Registro)");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		setLocationRelativeTo(null);

		ImageIcon icon = new ImageIcon("resources/images/favicon.png");
		;
		setIconImage(icon.getImage());

		getContentPane().setBackground(new Color(217, 239, 248));
		
		JLabel lblRegistroUsuario = new JLabel("Registro de Usuario");
		lblRegistroUsuario.setFont(new Font("Poppins", Font.BOLD, 14));
		lblRegistroUsuario.setBounds(30, 10, 260, 20);
		lblRegistroUsuario.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(lblRegistroUsuario);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 60, 100, 20);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(140, 60, 150, 20);
		getContentPane().add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(30, 100, 100, 20);
		getContentPane().add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(140, 100, 150, 20);
		getContentPane().add(txtApellido);

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(30, 140, 100, 20);
		getContentPane().add(lblDNI);

		txtDNI = new JTextField();
		txtDNI.setBounds(140, 140, 150, 20);
		getContentPane().add(txtDNI);

		JLabel lblClave = new JLabel("Contraseña:");
		lblClave.setBounds(30, 180, 100, 20);
		getContentPane().add(lblClave);

		passwordField = new JPasswordField();
		passwordField.setBounds(140, 180, 150, 20);
		getContentPane().add(passwordField);

		JLabel lblCarnet = new JLabel("Carnet:");
		lblCarnet.setBounds(30, 220, 100, 20);
		getContentPane().add(lblCarnet);

		checkCarnet = new JCheckBox("Posee Carnet");
		checkCarnet.setBounds(140, 220, 150, 20);
		checkCarnet.setBackground(new Color(217, 239, 248));
		getContentPane().add(checkCarnet);

		lblLogin = new JLabel("Ya tienes cuenta?");
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setFont(new Font("Poppins", Font.BOLD, 10));
		lblLogin.setBounds(465, 190, 150, 30);
		getContentPane().add(lblLogin);

		lblLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaLogin vLogin = new VentanaLogin();
				vLogin.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {
				lblLogin.setText("<html><u>Ya tienes cuenta?</u></html>"); // ayuda de copilot
			}

			public void mouseExited(MouseEvent e) {
				lblLogin.setText("Ya tienes cuenta?");
			}
		});
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(450, 160, 120, 30);

		btnRegistrar.setForeground(new Color(33, 150, 243));
		btnRegistrar.setBackground(Color.white);
		btnRegistrar.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
		btnRegistrar.setPreferredSize(new Dimension(60, 25));
		getContentPane().add(btnRegistrar);
		
		//if (checkCarnet.isSelected()) {
			
		JLabel lblRegistroVehiculo = new JLabel("Registro de Vehículo");
		lblRegistroVehiculo.setFont(new Font("Poppins", Font.BOLD, 14));
		lblRegistroVehiculo.setBounds(400, 10, 200, 30);
		lblRegistroVehiculo.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(lblRegistroVehiculo);
		
		lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setBounds(400, 60, 100, 20);
		getContentPane().add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(490, 60, 120, 20);
		getContentPane().add(txtMatricula);

		lblAsientos = new JLabel("Asientos:");
		lblAsientos.setBounds(400, 100, 100, 20);
		getContentPane().add(lblAsientos);

		txtAsientos = new JTextField();
		txtAsientos.setBounds(490, 100, 120, 20);
		getContentPane().add(txtAsientos);
		//}
		
		txtMatricula.setEnabled(false);
		txtAsientos.setEnabled(false);
		
		checkCarnet.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        boolean tieneCarnet = checkCarnet.isSelected();
		        txtMatricula.setEnabled(tieneCarnet);
		        txtAsientos.setEnabled(tieneCarnet);
		    }
		});
		
		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = txtNombre.getText();
				String apellido = txtApellido.getText();
				String contraseña = new String(passwordField.getPassword());
				String dni = txtDNI.getText();
				boolean carnet = checkCarnet.isSelected();
				
				String matricula = txtMatricula.getText();
				int asientos = Integer.parseInt(txtAsientos.getText());
				
				
				boolean usuarioExistente = false;
				gestorDB.connect();
				for (Usuario usuario : gestorDB.getUsuarios()) {
					if (usuario.getDni().equals(dni)) {
						usuarioExistente = true;
						break;
					}
				}
				if (usuarioExistente) {
					JOptionPane.showMessageDialog(null, "El DNI ya está registrado. Intenta con otro.");
				} else if (nombreUsuario.isEmpty() || contraseña.isEmpty() || dni.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre de usuario y la contraseña no pueden estar vacíos.");
				} else {
					Usuario nuevoUsuario = new Usuario(dni, nombreUsuario, apellido, contraseña, carnet, 0);
					gestorDB.insertarUsuarios(nuevoUsuario);
					Vehiculo v = new Vehiculo(matricula, asientos, nuevoUsuario);
					gestorDB.insertarVehiculo(v);
					System.out.println(nuevoUsuario);
					JOptionPane.showMessageDialog(null, "Registro exitoso. Bienvenido " + nombreUsuario);
					dispose();
				}
				gestorDB.close();
			}

		});
	}
}
