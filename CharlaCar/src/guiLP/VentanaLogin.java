package guiLP;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnAcceder;
	
	public VentanaLogin(JFrame esclavo ) {
		
		//constructor heredado
		super( esclavo );
		setModal(true);		
		
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(300,200);
		setTitle("CharlaCar");
		
		//no funciona la imagen
		ImageIcon icon = new ImageIcon("images/FotoPerfil.jpg"); // imagen sacada de chatgpt
		///////////////////////////////////////////
		
		textField = new JTextField();
		textField.setBounds(110, 10, 85, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 55, 85, 20);
		getContentPane().add(passwordField);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 15, 90, 15);
		getContentPane().add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave:");
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
		if(e.getActionCommand().equalsIgnoreCase("acceder")) 
		{
			System.out.println("ventana login en invisible");
			setVisible(false);
			
		}//else if() {}
			
		
		
		
	}
}
