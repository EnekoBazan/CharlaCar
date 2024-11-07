package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import domainLN.CharlaCarImpl;
import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.TipoVehiculo;
import domainLN.Viaje;

import javax.swing.RowFilter;

public class VentanaBuscarViaje extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;

	private JTable tablaBusqueda;
	private String[] cabecera;
	private String[][] datosEjemplo;

	private JScrollPane scrollPane;
	private JButton btnUnirse;
	private JPanel panelSouth;
	private JPanel panelSouthDerecha;

	private JPanel panelNorth;
	private JLabel lblFiltro;
	private JTextField txtFiltro;

	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> rowSorter;

	// private JButton btnFiltrar;

	public VentanaBuscarViaje() {

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(750, 450);
		setTitle("CharlaCar");
		setLocationRelativeTo(null);

		setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());

		// CABECERA DE TABLA
		cabecera = new String[] { "Matricula", "Propietario", "Origen", "Destino", "Asientos Totales",
				"Asientos Disponibles" };
		///// DATOS DE EJEMPLO

		// Lógica para preparar los datos de ejemplo
		datosEjemplo = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
				.map(viaje -> new String[] { viaje.getVehiculo().getMatricula(),
						viaje.getVehiculo().getPropietario().getNombre(), viaje.getOrigen(), viaje.getDestino(),
						String.valueOf(viaje.getVehiculo().getPlazas()),
						String.valueOf(viaje.getVehiculo().getPlazas() - viaje.getEspaciosDisponibles()) })
				.toArray(String[][]::new);

		// PANEL PRINCIPAL
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 10, 5, 0));

		tableModel = new DefaultTableModel(datosEjemplo, cabecera);
		tablaBusqueda = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		scrollPane = new JScrollPane(tablaBusqueda);
		btnUnirse = new JButton("Unirse");
		btnUnirse.setForeground(new Color(33, 150, 243));
		btnUnirse.setBackground(Color.white);

		panelSouth = new JPanel(new BorderLayout());
		panelSouthDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelNorth = new JPanel(new FlowLayout());

		lblFiltro = new JLabel("Filtrar viajes: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
		// btnFiltrar = new JButton("Filtrar");
		txtFiltro = new JTextField(20);

		this.txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrar(txtFiltro.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrar(txtFiltro.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filtrar(txtFiltro.getText());
			}
		});

		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
		panelPrincipal.setBackground(new Color(217, 239, 248));

		panelSouthDerecha.add(btnUnirse);
		panelSouthDerecha.setBackground(new Color(217, 239, 248));

		panelSouth.add(new JLabel(" Selecciona el viaje al que te quieres unir"), BorderLayout.WEST);
		panelSouth.add(panelSouthDerecha, BorderLayout.EAST);
		panelSouth.setBackground(new Color(217, 239, 248));
		panelPrincipal.add(panelSouth, BorderLayout.SOUTH);

		panelNorth.add(lblFiltro);
		panelNorth.add(txtFiltro);
		panelNorth.setBackground(new Color(217, 239, 248));

		// panelNorth.add(btnFiltrar);
		panelPrincipal.add(panelNorth, BorderLayout.NORTH);
		this.tablaBusqueda.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.tablaBusqueda.setDefaultRenderer(Object.class, cellRenderer);
		this.add(panelPrincipal);

		btnUnirse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int numFila = tablaBusqueda.getSelectedRow();
				if (numFila != -1) {
					String matricula = (String) tableModel.getValueAt(numFila, 0);
					String propietario = (String) tableModel.getValueAt(numFila, 1);
					String origen = (String) tableModel.getValueAt(numFila, 2);
					String destino = (String) tableModel.getValueAt(numFila, 3);
					int asientosTotal = Integer.parseInt((String) tableModel.getValueAt(numFila, 4));
					int asientosDisponibles = Integer.parseInt((String) tableModel.getValueAt(numFila, 5));

					Vehiculo vehiculo = new Vehiculo(matricula, TipoVehiculo.COCHE, asientosTotal,
							new Usuario(propietario, "", "", "", true, 0.0f));
					Viaje viaje = new Viaje(origen, destino, asientosDisponibles, 0, null, "");
					viaje.setVehiculo(vehiculo);

					CharlaCarImpl.getCharlaCarImpl().addViajeToUsuario(viaje);
					System.out.println(viaje);
					//System.out.println(CharlaCarImpl.getCharlaCarImpl().getLogedUser().visualizarListaViajes);

					if (CharlaCarImpl.getCharlaCarImpl().isLoged() == false) {
						JOptionPane.showMessageDialog(null, "No estas logeado");
					} else {
						JOptionPane.showMessageDialog(null, "Te has unido al viaje!!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un viaje");
				}
			}

		});
		setVisible(true);

	}

	//// RENDERER
	TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
		JLabel result = new JLabel(value.toString());

		// con rgb negro claro
		result.setHorizontalAlignment(JLabel.CENTER);
		result.setForeground(new Color(60, 60, 60));
		result.setBackground(Color.white);

		if (isSelected) {
			// result.setHorizontalAlignment(JLabel.CENTER);
			result.setBackground(new Color(173, 216, 230));
			result.setForeground(Color.black);

		} else if (value instanceof Number) {
			// result.setHorizontalAlignment(JLabel.CENTER);
		} else {
			// Si el valor es texto pero representa un número se renderiza centrado
			try {
				Integer.parseInt(value.toString());
				// result.setHorizontalAlignment(JLabel.CENTER);
			} catch (Exception ex) {
				result.setText(value.toString());
			}
		}
		result.setOpaque(true);
		return result;
	};

	// ayuda de claude ->

	private void filtrar(String filtro) {
		// Inicializar el TableRowSorter si aún no existe
		if (rowSorter == null) {
			rowSorter = new TableRowSorter<>(tableModel);
			tablaBusqueda.setRowSorter(rowSorter);
		}

		if (filtro.toString().isEmpty()) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
				@Override
				public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {

					for (int i = 0; i < entry.getValueCount(); i++) {
						String valor = entry.getStringValue(i);
						if (valor.toLowerCase().contains(filtro.toLowerCase())) {
							return true;
						}
					}
					return false;
				}
			});
		}
	}

}
