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
		        try {
		            registrarUsuario();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});}

		private void registrarUsuario() {
		    String nombreUsuario = txtNombre.getText().trim();
		    String apellido = txtApellido.getText().trim();
		    String contraseña = new String(passwordField.getPassword()).trim();
		    String dni = txtDNI.getText().trim();
		    boolean carnet = checkCarnet.isSelected();

		    if (nombreUsuario.isEmpty() || contraseña.isEmpty() || dni.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben estar llenos.", "Validación", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    if (!validarDNI(dni)) {
		        JOptionPane.showMessageDialog(
		            null,
		            "El DNI ingresado no tiene un formato válido.\n" +
		            "Debe contener 8 números seguidos de una letra (por ejemplo, 12345678Z).",
		            "Error de Validación",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    gestorDB.connect();
		    try {
		        boolean usuarioExistente = gestorDB.getUsuarios().stream().anyMatch(usuario -> usuario.getDni().equals(dni));
		        if (usuarioExistente) {
		            JOptionPane.showMessageDialog(null, "El DNI ya está registrado. Intenta con otro.");
		            return;
		        }

		        Usuario nuevoUsuario = new Usuario(dni, nombreUsuario, apellido, contraseña, carnet, 0);
		        gestorDB.insertarUsuarios(nuevoUsuario);

		        if (carnet) {
		            String matricula = txtMatricula.getText().trim();
		            String asientosTexto = txtAsientos.getText().trim();

		            if (matricula.isEmpty() || asientosTexto.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Debes ingresar la matrícula y los asientos para registrar el vehículo.", "Validación", JOptionPane.WARNING_MESSAGE);
		                return;
		            }

		            int asientos;
		            try {
		                asientos = Integer.parseInt(asientosTexto);
		                if (asientos <= 0) throw new NumberFormatException();
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "El número de asientos debe ser un número entero positivo.", "Validación", JOptionPane.WARNING_MESSAGE);
		                return;
		            }

		            Vehiculo vehiculo = new Vehiculo(matricula, asientos, nuevoUsuario);
		            gestorDB.insertarVehiculo(vehiculo);
		        }

		        JOptionPane.showMessageDialog(null, "Registro exitoso. Bienvenido, " + nombreUsuario + "!");
		        dispose();
		    } finally {
		        gestorDB.close();
		    }
		}

		private boolean validarDNI(String dni) {
		    if (dni == null || dni.length() != 9) {
		        System.out.println("DNI inválido: longitud incorrecta");
		        return false; // Debe tener 9 caracteres
		    }

		    dni = dni.trim(); // Eliminar espacios
		    String numeros = dni.substring(0, 8); // Extraemos los primeros 8 caracteres
		    char letra = Character.toUpperCase(dni.charAt(8)); // Extraemos y normalizamos la letra

		    // Verificamos si los primeros 8 caracteres son números
		    if (!numeros.matches("\\d{8}")) {
		        System.out.println("DNI inválido: los primeros 8 caracteres no son números");
		        return false;
		    }

		    // Calculamos la letra correcta según el algoritmo
		    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		    int numeroDni = Integer.parseInt(numeros);
		    char letraCorrecta = letras.charAt(numeroDni % 23);

		    // Imprimir valores para depuración
		    System.out.println("DNI ingresado: " + dni);
		    System.out.println("Números: " + numeros);
		    System.out.println("Letra ingresada: " + letra);
		    System.out.println("Letra calculada: " + letraCorrecta);

		    // Comparamos la letra ingresada con la letra calculada
		    boolean resultado = letra == letraCorrecta;
		    if (!resultado) {
		        System.out.println("DNI inválido: la letra no coincide");
		    }
		    return resultado;
		}


}
