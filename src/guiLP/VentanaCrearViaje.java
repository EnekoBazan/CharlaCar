package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaCrearViaje extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelSecundario = new JPanel(new BorderLayout());
	
	public VentanaCrearViaje() {
	
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(750, 450);
	setTitle("CharlaCar");
	setVisible(true);
	setLocationRelativeTo(null);
	
	setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());
	
	panelPrincipal.setBackground(new Color(217, 239, 248));
//	panelPrincipal.add(panelSecundario);
	panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
	panelSecundario.setBackground(Color.WHITE);


	this.add(panelPrincipal);
	}

}
