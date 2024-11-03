package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelNorte = new JPanel(new BorderLayout());
	private JPanel panelCentro = new JPanel(new BorderLayout());
	private JPanel panelSur = new JPanel(new BorderLayout());
	private JPanel panelUsuario = new JPanel(new BorderLayout());

	
	private JLabel lbNombre = new JLabel();
	private JLabel lbApellido = new JLabel();
	private JLabel lbDNI = new JLabel();
		
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
		
		//Usuario
      	Border bordeUsuario = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeUsuario = BorderFactory.createTitledBorder(bordeUsuario,"Usuario");
    	panelNorte.setBorder(tituloBordeUsuario);
    	
		lbNombre.setText("Nombre: ");
		panelNorte.add(lbNombre, BorderLayout.NORTH);
		
		lbApellido.setText("Apellido: ");
		panelNorte.add(lbApellido, BorderLayout.CENTER);
		
		lbDNI.setText("DNI: ");
		panelNorte.add(lbDNI, BorderLayout.SOUTH);
		
		//Rating
      	Border bordeRating = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeRating = BorderFactory.createTitledBorder(bordeRating,"Rating");
    	panelCentro.setBorder(tituloBordeRating);
    	
    	
    	//Viajes
      	Border bordeViajes = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeViajes = BorderFactory.createTitledBorder(bordeViajes,"Viajes");
    	panelSur.setBorder(tituloBordeViajes);
    	
    	panelPrincipal.add(panelNorte, BorderLayout.NORTH);
    	panelPrincipal.add(panelCentro, BorderLayout.CENTER);
    	panelPrincipal.add(panelSur, BorderLayout.SOUTH);

		add(panelPrincipal);
		
	}
	public void ratingEstrellas() {
		
	}
//	public static void main(String[] args) {
//		VentanaPerfil v = new VentanaPerfil();
//	}
}
