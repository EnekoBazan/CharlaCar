package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class VentanaBuscarViaje extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tablaBusqueda;
	private String[] cabecera;
	private String[][] datosEjemplo;
	private JScrollPane scrollPane;
	private JButton btnUnirse;
	
	public VentanaBuscarViaje() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 400);
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		  
		btnUnirse = new JButton("Unirse");
		//btnUnirse.setSize(100, 50);
		
		
		cabecera = new String[] {"Model Coche", "Matricula", "Origen", "Destino", "Asientos Totales", "Asientos Ocupados"};
		/////DATOS DE EJEMPLO
		datosEjemplo = new String[][]
		{
			{"Ford Focus", "5933 HYS", "Bilbao", "Vitoria", "5", "0"},
			{"Ford Fiesta", "0983 ADE", "Bilbao", "Vitoria", "5", "3"},
			{"Seat Toledo", "2123 ING", "Bilbao", "Vitoria", "5", "4"},
			{"Seat Ibiza", "1456 FGP", "Bilbao", "Vitoria", "5", "1"},
			{"Seat Panda", "9873 BMN", "Bilbao", "Vitoria", "5", "2"}
		};
		tablaBusqueda = new JTable(datosEjemplo, cabecera );
		scrollPane = new JScrollPane(tablaBusqueda);
		
		////RENDERER
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			//con rgb negro claro
			result.setForeground(new Color(75,75,75));
			
			if (isSelected) {
				result.setBackground(Color.CYAN);
				result.setForeground(table.getSelectionForeground());			
			}
			else if (value instanceof Number) {
				result.setHorizontalAlignment(JLabel.CENTER);
			} else {
				//Si el valor es texto pero representa un número se renderiza centrado
				try {
					Integer.parseInt(value.toString());
					result.setHorizontalAlignment(JLabel.CENTER);				
				} catch(Exception ex) {
					result.setText(value.toString());
				}		
			}
			result.setOpaque(true);
			return result;
		};
		
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(new JLabel("Selecciona el viaje al que te quieres unir \n"), BorderLayout.NORTH);
		
		////BOTON UNIRSE hay que mejorarlo
		btnUnirse.setSize(100, 50);
		this.add(btnUnirse, BorderLayout.SOUTH);
		
		this.tablaBusqueda.setDefaultRenderer(Object.class, cellRenderer);
		
	}
}
