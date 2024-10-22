package guiLP;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VentanaCrearViaje extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public VentanaCrearViaje() {
	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(750, 450);
	setTitle("CharlaCar");
	setVisible(true);
	setLocationRelativeTo(null);
	
	setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());
	
	
	}
}
