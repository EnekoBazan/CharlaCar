package guiLP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domainLN.GestorLN;

public class VentanaLogin extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnAcceder;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private GestorLN gestorLN;
	
	public VentanaLogin(JFrame esclavo ) {
		
		//constructor heredado
		super( esclavo );
		setModal(true);		
		
		gestorLN = new GestorLN();
		getContentPane().setLayout(null);
		
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                JOptionPane.showMessageDialog(null, "No puedes cerrar esta ventana");
			}
		});	
		
		setSize(300,200);
		setTitle("CharlaCar");
		
		textField = new JTextField();
		textField.setBounds(110, 10, 85, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
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
		btnAcceder.setBounds(80, 110, 90, 20);
		getContentPane().add(btnAcceder);
		btnAcceder.addActionListener(this);
		
		setLocationRelativeTo(null);
	}
	
	//TODA LA FUNCIONALIDAD DE ACTIONLISTENERS METIDO AQUI ENTRE IF
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("acceder")) {
//			String user = textField.getText();
//			String pass = passwordField.getText();
			System.out.println("Acceder");
			gestorLN.mostrarDatos();
			dispose();
			
		}//else if(e.getActionCommand()) {}
				
	}
}
