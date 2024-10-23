package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class VentanaPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;
	
	public VentanaPerfil() {
		
		//setModal(true); solo si queremos que no se pueda interactuar con la ventana principal mientras esta este abierta
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(450, 420);
		setTitle("Perfil");
		setVisible(true);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png"));//imagen generada con IA
		setIconImage(icon.getImage());
		
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(217, 239, 248));
		
	}
//	public static void main(String[] args) {
//		VentanaPerfil v = new VentanaPerfil();
//	}
}
