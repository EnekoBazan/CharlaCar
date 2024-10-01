package guiLP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JDialog ventanaLogin;
	
	public VentanaPrincipal()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/FotoPerfil.png"));//imagen generada con IA
		setIconImage(icon.getImage());
		
		ventanaLogin = new VentanaLogin( this );
		ventanaLogin.setVisible( true );
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
