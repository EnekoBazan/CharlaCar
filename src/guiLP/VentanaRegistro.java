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

	GestorBD gestorDB = GestorBD.getGestorDB();

	public VentanaRegistro() {
		setModal(true);
		setSize(350, 350);
		setTitle("CharlaCar (Registro)");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		setLocationRelativeTo(null);

		ImageIcon icon = new ImageIcon("resources/images/favicon.png");
		;
		setIconImage(icon.getImage());

		getContentPane().setBackground(new Color(217, 239, 248));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 30, 100, 20);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(140, 30, 150, 20);
		getContentPane().add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(30, 70, 100, 20);
		getContentPane().add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(140, 70, 150, 20);
		getContentPane().add(txtApellido);

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(30, 110, 100, 20);
		getContentPane().add(lblDNI);

		txtDNI = new JTextField();
		txtDNI.setBounds(140, 110, 150, 20);
		getContentPane().add(txtDNI);

		JLabel lblClave = new JLabel("Contraseña:");
		lblClave.setBounds(30, 150, 100, 20);
		getContentPane().add(lblClave);

		passwordField = new JPasswordField();
		passwordField.setBounds(140, 150, 150, 20);
		getContentPane().add(passwordField);

		JLabel lblCarnet = new JLabel("Carnet:");
		lblCarnet.setBounds(30, 190, 100, 20);
		getContentPane().add(lblCarnet);

		checkCarnet = new JCheckBox("Posee Carnet");
		checkCarnet.setBounds(140, 190, 150, 20);
		checkCarnet.setBackground(new Color(217, 239, 248));
		getContentPane().add(checkCarnet);

		lblLogin = new JLabel("Ya tienes cuenta?");
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setFont(new Font("Poppins", Font.BOLD, 10));
		lblLogin.setBounds(120, 280, 100, 30);
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
		// no tine que poner el rating el usuario que se registra
//        JLabel lblRating = new JLabel("Calificación:");
//        lblRating.setBounds(30, 230, 100, 20);
//        getContentPane().add(lblRating);
//        
//        sliderRating = new JSlider(0, 100, 50);
//        sliderRating.setBounds(140, 230, 150, 20);
//        sliderRating.setMajorTickSpacing(25);
//        sliderRating.setPaintTicks(true);
//        getContentPane().add(sliderRating);

//        lblRatingValue = new JLabel("50");  // Muestra el valor inicial del slider
//        lblRatingValue.setBounds(300, 230, 30, 20);
//        getContentPane().add(lblRatingValue);
//        
//        sliderRating.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                lblRatingValue.setText(String.valueOf(sliderRating.getValue()));
//            }
//        });
		// cambio
		// comentario 2
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(115, 255, 100, 30);

		btnRegistrar.setForeground(new Color(33, 150, 243));
		btnRegistrar.setBackground(Color.white);
		btnRegistrar.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
		btnRegistrar.setPreferredSize(new Dimension(60, 25));
		getContentPane().add(btnRegistrar);

		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = txtNombre.getText();
				String apellido = txtApellido.getText();
				String contraseña = new String(passwordField.getPassword());
				String dni = txtDNI.getText();
				boolean carnet = checkCarnet.isSelected();

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
					System.out.println(nuevoUsuario);
					JOptionPane.showMessageDialog(null, "Registro exitoso. Bienvenido " + nombreUsuario);
					dispose();
				}
				gestorDB.close();
			}

		});
	}
}
