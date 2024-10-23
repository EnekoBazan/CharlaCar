package guiLP;

import javax.swing.JDialog;

public class VentanaRegistro extends JDialog {
	private static final long serialVersionUID = 1L;

	public VentanaRegistro(){
		
		setModal(true);
		setSize(300,200);
		setTitle("CharlaCar (Registro)");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
