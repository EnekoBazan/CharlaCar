package guiLP;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JDialog ventanaLogin;
	private JButton btnCrearViaje;
	private JButton btnBuscarViaje;
	private JPanel PanelPrincipal;
	
	public VentanaPrincipal()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/FotoPerfil.png"));//imagen generada con IA
		setIconImage(icon.getImage());
		
		PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new GridLayout(2, 2, 10, 10));
		PanelPrincipal.setBorder(BorderFactory.createTitledBorder("CharlaCar"));
		
		JPanel blanc = new JPanel();
		PanelPrincipal.add(blanc);
		
		btnCrearViaje = new JButton("Crear Viaje");
		//btnCrearViaje.setIcon(icon); //icono del boton
		PanelPrincipal.add(btnCrearViaje);
		
		JPanel blanc1 = new JPanel();
		PanelPrincipal.add(blanc1);
		
		btnBuscarViaje = new JButton("Buscar Viaje");
		PanelPrincipal.add(btnBuscarViaje);
		
		this.add(PanelPrincipal);
		
		ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible( true );
		
	//	this.validate();//repaint pantalla
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Crear Viaje")) {
			System.out.println("Crear Viaje");
		}else if (e.getActionCommand().equalsIgnoreCase("Buscar Viaje")){
			System.out.println("Buscar Viaje");
		}
		
	}

}
