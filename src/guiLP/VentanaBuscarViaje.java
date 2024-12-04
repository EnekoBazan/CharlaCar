package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import db.GestorBD;
import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.Viaje;

import javax.swing.RowFilter;

public class VentanaBuscarViaje extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;

	private JTable tablaBusqueda;

	private JScrollPane scrollPane;
	private JButton btnUnirse;
	private JPanel panelSouth;
	private JPanel panelSouthDerecha;

	private JPanel panelNorth;
	private JLabel lblFiltro;
	private JTextField txtFiltro;

	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> rowSorter;

	GestorBD gestorBD = GestorBD.getGestorDB();

	// private JButton btnFiltrar;
	public VentanaBuscarViaje() {

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(750, 450);
		setTitle("CharlaCar");
		setLocationRelativeTo(null);

		ImageIcon icon = new ImageIcon("resources/images/favicon.png");
		setIconImage(icon.getImage());

		String[] cabecera = { "Origen", "Destino", "Plazas", "Conductor", "Matricula" };
		tableModel = new DefaultTableModel(cabecera, 0);

		tablaBusqueda = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaBusqueda = new JTable(tableModel);

		try {
			gestorBD.connect();
			List<Viaje> viajes = gestorBD.getViajes();
			for (Viaje viaje : viajes) {
				String conductor = "Sin conductor";
				String matricula = "No asignada";

				if (viaje.getConductor() != null) {
					Usuario conductorInfo = gestorBD.getUsuarioByDni(viaje.getConductor().getDni());
					if (conductorInfo != null) {
						conductor = conductorInfo.getNombre() + " " + conductorInfo.getApellido();
					}

					Vehiculo vehiculo = gestorBD.getVehiculoPorUsuario(viaje.getConductor().getDni());
					if (vehiculo != null) {
						matricula = vehiculo.getMatricula();
					}
				}

				tableModel.addRow(new Object[] { viaje.getOrigen(), viaje.getDestino(), viaje.getPlazas(), conductor,
						matricula });
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar viajes: " + e.getMessage());
		} finally {
			gestorBD.close();
		}

		// PANEL PRINCIPAL
		scrollPane = new JScrollPane(tablaBusqueda);
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 10, 5, 0));
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
		this.add(panelPrincipal);

		// RENDERER ENCABEZADO
		JTableHeader header = tablaBusqueda.getTableHeader();
		header.setDefaultRenderer(new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				label.setBackground(new Color(33, 150, 243));
				label.setForeground(Color.WHITE);
				label.setFont(new Font("Arial", Font.BOLD, 13));

				label.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25, 118, 210)),
						BorderFactory.createEmptyBorder(8, 10, 8, 10)));

				label.setHorizontalAlignment(JLabel.CENTER);
				label.setOpaque(true);

				return label;
			}
		});

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

				try {

					gestorBD.connect();

					Usuario usuarioLogueado = gestorBD.getUsuarioLogeado();
					int idViajeSeleccionado = obtenerIdViajeSeleccionado();

					Viaje viajeSeleccionado = gestorBD.getViajePorId(idViajeSeleccionado);
					if (viajeSeleccionado == null) {
						JOptionPane.showMessageDialog(null, "No se encontró el viaje seleccionado.");
						return;
					}

					if (viajeSeleccionado.getPlazas() <= 0) {
						JOptionPane.showMessageDialog(null, "Lo sentimos, no quedan plazas disponibles en este viaje.");
						return;
					}
					if (viajeSeleccionado.getConductor().getDni().equals(usuarioLogueado.getDni())) {
						JOptionPane.showMessageDialog(null, "No puedes unirte a un viaje que has creado.");
						return;
					}

					gestorBD.insertarViajeUsuario(viajeSeleccionado, usuarioLogueado);

					viajeSeleccionado.setPlazas(viajeSeleccionado.getPlazas() - 1);
					gestorBD.insertarViaje(viajeSeleccionado);

					JOptionPane.showMessageDialog(null, "Te has unido al viaje correctamente.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Ocurrió un error al intentar unirte al viaje: " + ex.getMessage());
					ex.printStackTrace();
				} finally {
					gestorBD.close();
				}
			}

		});
		setVisible(true);

	}

	private int obtenerIdViajeSeleccionado() {
	    int filaSeleccionada = tablaBusqueda.getSelectedRow();
	    if (filaSeleccionada >= 0) {
	        try {
	            return Integer.parseInt(tablaBusqueda.getValueAt(filaSeleccionada, 0).toString());
	        	} catch (NumberFormatException e) {
	            throw new IllegalStateException("El DNI seleccionado no es un número válido.");
	        }
	    } else {
	        throw new IllegalStateException("No se ha seleccionado ninguna fila.");
	    }
	}

	//// RENDERER
	TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
		JLabel result = new JLabel(value.toString());

		// con rgb negro claro
		result.setHorizontalAlignment(JLabel.RIGHT);
		result.setForeground(new Color(60, 60, 60));
		result.setBackground(Color.white);
		result.setBorder(new EmptyBorder(5, 10, 5, 10));

		if (isSelected) {
//			result.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 248, 255));
			result.setBackground(new Color(173, 216, 230));
//			result.setForeground(new Color(60, 60, 60));
			result.setForeground(Color.BLACK);

		} else {
//			result.setBackground(new Color(173, 216, 230));
			result.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 248, 255));
			result.setForeground(new Color(60, 60, 60));
//			result.setForeground(Color.BLACK);
		}
		// result.setHorizontalAlignment(JLabel.CENTER);
		if (value != null) {
			try {
				Double.parseDouble(value.toString());
				result.setHorizontalAlignment(JLabel.CENTER);
			} catch (NumberFormatException e) {
				result.setHorizontalAlignment(JLabel.LEFT);
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
