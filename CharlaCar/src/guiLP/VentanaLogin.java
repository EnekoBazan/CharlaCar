package guiLP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

import domainLN.GestorLN;

public class VentanaLogin extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtUser;
	private JPasswordField passwordField;
	private JButton btnAcceder;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JLabel lblRegistro;
	private GestorLN gestorLN;
	
	public VentanaLogin() {
		
		setModal(true);		
		
		gestorLN = new GestorLN();
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                JOptionPane.showMessageDialog(null, "No puedes cerrar esta ventana");
			}
		});	
		
		setSize(300,200);
		setTitle("CharlaCar (LogIn)");
		
		txtUser = new JTextField();
		txtUser.setBounds(110, 10, 85, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 55, 85, 20);
		getContentPane().add(passwordField);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 15, 90, 15);
		getContentPane().add(lblUsuario);
		
		lblClave = new JLabel("Clave:");
		lblClave.setBounds(10, 60, 90, 15);
		getContentPane().add(lblClave);
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(90, 110, 90, 20);
		getContentPane().add(btnAcceder);
		btnAcceder.addActionListener(this);
		
		lblRegistro = new JLabel("No estas Registrado?");
		lblRegistro.setForeground(Color.BLUE);
		lblRegistro.setFont(new Font("Poppins", Font.BOLD, 10));
		lblRegistro.setBounds(85, 130, 300, 20);
		getContentPane().add(lblRegistro);
		
		setLocationRelativeTo(null);
		
		lblRegistro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				VentanaRegistro vRegistro = new VentanaRegistro();
				vRegistro.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {
				lblRegistro.setText("<html><u>No estas Registrado?</u></html>"); //ayuda de copilot
			}

			public void mouseExited(MouseEvent e) {
				lblRegistro.setText("No estas Registrado?");
			}
		});
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("acceder")) {
			System.out.println("Presionado Acceder");
			gestorLN.mostrarDatos();
			dispose();
			
		}//else if(e.getActionCommand()) {}
				
	}
}
