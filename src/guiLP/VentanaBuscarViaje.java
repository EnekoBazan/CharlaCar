package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JPanel panelSouth;
	private JPanel panelSouthDerecha;
	
	public VentanaBuscarViaje() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 400);
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);

		//CABECERA DE TABLA
		cabecera = new String[] {"Model Coche", "Matricula", "Origen", "Destino", "Asientos Totales", "Asientos Ocupados"};
		/////DATOS DE EJEMPLO
		datosEjemplo = new String[][]
		{
			{"Renault Clio", "5643 GHT", "Madrid", "Barcelona", "7", "2"},
			{"Volkswagen Golf", "8832 FDB", "Sevilla", "Valencia", "6", "3"},
			{"Peugeot 308", "3456 JKL", "Bilbao", "Santander", "5", "0"},
			{"Citroen C4", "7765 YUR", "Madrid", "Valladolid", "7", "1"},
			{"Hyundai i30", "2345 OUI", "Zaragoza", "Pamplona", "6", "4"},
			{"Toyota Yaris", "7788 HJK", "Alicante", "Murcia", "5", "1"},
			{"Honda Civic", "6754 FGE", "Granada", "Sevilla", "6", "0"},
			{"Nissan Qashqai", "4556 RDF", "Malaga", "Cordoba", "7", "3"},
			{"Kia Rio", "5678 WER", "Vigo", "Ourense", "5", "2"},
			{"BMW Serie 1", "1234 ABC", "Madrid", "Toledo", "5", "4"},
            {"Audi A3", "4567 DEF", "Barcelona", "Girona", "5", "1"},
            {"Mercedes Clase A", "7890 GHI", "Valencia", "Alicante", "5", "2"},
            {"Ford Raptor", "5933 HYS", "Bilbao", "Vitoria", "5", "0"},
            {"Ford Fiesta", "0983 ADE", "Madrid", "Sevilla", "4", "2"},
            {"Seat Toledo", "2123 ING", "Barcelona", "Girona", "3", "1"},
            {"Seat Ibiza", "1456 FGP", "Valencia", "Alicante", "2", "3"},
            {"Seat Panda", "9873 BMN", "Zaragoza", "Logroño", "5", "4"},
            {"Renault Clio", "5643 GHT", "Granada", "Málaga", "6", "1"},
            {"Volkswagen Golf", "8832 FDB", "Sevilla", "Córdoba", "7", "0"},
            {"Peugeot 308", "3456 JKL", "Bilbao", "Santander", "4", "3"},
            {"Citroen C4", "7765 YUR", "Madrid", "Toledo", "5", "2"},
            {"Hyundai i30", "2345 OUI", "Zaragoza", "Pamplona", "6", "4"},
            {"Toyota Yaris", "7788 HJK", "Alicante", "Murcia", "3", "2"},
            {"Honda Civic", "6754 FGE", "Vigo", "Ourense", "5", "0"},
            {"Nissan Qashqai", "4556 RDF", "León", "Oviedo", "6", "3"},
            {"Kia Rio", "5678 WER", "Burgos", "Valladolid", "4", "1"},
            {"BMW Serie 1", "1234 ABC", "Madrid", "Segovia", "5", "4"}

		};
		
		tablaBusqueda = new JTable(datosEjemplo, cabecera );
		scrollPane = new JScrollPane(tablaBusqueda);
		btnUnirse = new JButton("Unirse");
		btnUnirse.setSize(10, 10);
		panelSouth = new JPanel(new BorderLayout());
		panelSouthDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		////RENDERER
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			//con rgb negro claro
			result.setForeground(new Color(75,75,75));
			result.setBackground(Color.white);
			
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
		
		panelSouthDerecha.add(btnUnirse);
		
		panelSouth.add(new JLabel(" Selecciona el viaje al que te quieres unirte"));
		panelSouth.add(panelSouthDerecha, BorderLayout.EAST);

		this.add(panelSouth, BorderLayout.SOUTH);
		
		this.tablaBusqueda.setDefaultRenderer(Object.class, cellRenderer);
		
	}
}
