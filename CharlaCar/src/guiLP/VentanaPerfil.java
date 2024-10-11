package guiLP;

import javax.swing.JDialog;

public class VentanaPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;

	public VentanaPerfil() {
		
		//setModal(true); solo si queremos que no se pueda interactuar con la ventana principal mientras esta este abierta
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 500);
		
		setTitle("CharlaCar (Perfil)");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
