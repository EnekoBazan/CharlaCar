package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.GestorBD;
import domainLN.CharlaCarImpl;
import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.Viaje;

public class VentanaCrearViaje extends JFrame {

	private static final long serialVersionUID = 1L;

	GestorBD gestorDB = GestorBD.getGestorDB();

	private static final Color COLOR_FONDO = new Color(217, 239, 248);
	private static final Color COLOR_BOTONES = new Color(33, 150, 243);
	private static final Color COLOR_TEXTO = new Color(60, 60, 60);
	private static final Font FUENTE_NORMAL = new Font("Inter", Font.PLAIN, 14);

	Usuario user;
	// Paneles
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelPrincipalSur = new JPanel();
	private JPanel panelSecundario = new JPanel(new BorderLayout());
	private JPanel panelSecundarioViaje = new JPanel(new BorderLayout());
	private JPanel panelInfo = new JPanel(new BorderLayout());
	private JPanel panelInfoN = new JPanel(new BorderLayout());
	private JPanel panelInfoN1 = new JPanel(new GridLayout(1, 2));
	private JPanel panelInfoN2 = new JPanel(new GridLayout(1, 2));
	private JPanel panelInfoN3 = new JPanel(new GridLayout(1, 2));
	private JPanel panelInfoC = new JPanel(new BorderLayout());
	private JPanel panelInfoC1 = new JPanel(new GridLayout(1, 2));
	private JPanel panelInfoS = new JPanel(new BorderLayout());
	private JPanel panelInfoSW = new JPanel(new GridLayout(3, 1));
	private JPanel panelInfoSE = new JPanel(new GridLayout(1, 2));

	// Boton
	private JButton btnCrear = new JButton("Crear");
	private JButton btnAplicar = new JButton("Aplicar");

	// Label
	private JLabel lbViaje = new JLabel();
	private JLabel lbMatricula = new JLabel();
	private JLabel lbPlazas = new JLabel();
	private JLabel lbOrigen = new JLabel("Origen: ");
	private JLabel lbDestino = new JLabel("Destino: ");
	private JLabel lbAsientos = new JLabel("Asientos disponibles: ");

	// ComboBox
	private String[] numAsientos = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private JComboBox<String> comboAsientos = new JComboBox<>(numAsientos);

	private String[] ciudadesEspaña = { "Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia",
			"Palma", "Las Palmas", "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón",
			"Hospitalet de Llobregat", "La Coruña", "Vitoria", "Granada" };
	private JComboBox<String> comboCiudadesOrigen = new JComboBox<>(ciudadesEspaña);
	private JComboBox<String> comboCiudadesDestino = new JComboBox<>(ciudadesEspaña);

	// Tabla
	private JTable tablaViaje;
	private String[] cabecera = { "Origen", "Destino", "Asientos Disponibles" };
	private Object[][] datos = {};
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;

	public VentanaCrearViaje() {

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 600);
		setTitle("CharlaCar - Crear Viaje");
		setVisible(true);
		setLocationRelativeTo(null);

//		setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());

		Usuario usuarioLogeado = CharlaCarImpl.getCharlaCarImpl().getLogedUser();
//		String matricula = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
//				.filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
//				.map(viaje -> viaje.getVehiculo().getMatricula()).findFirst().orElse("No disponible");
//		Vehiculo vehiculo = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
//				.filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
//				.map(viaje -> viaje.getVehiculo()).findFirst().orElse(new Vehiculo());
//		Integer plazas = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
//				.filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
//				.map(viaje -> viaje.getVehiculo().getPlazas()).findFirst().orElse(0);

		panelPrincipal.setBackground(COLOR_FONDO);
		panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelSecundario.setBackground(COLOR_FONDO);
		panelSecundario.setBorder(BorderFactory.createCompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),
				new EmptyBorder(15, 15, 15, 15)));
		panelPrincipal.add(panelSecundario);
		Border bordeSecundario = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloBordeSecundario = BorderFactory.createTitledBorder(bordeSecundario, "Crear Viaje");
		panelSecundario.setBorder(tituloBordeSecundario);
		panelPrincipalSur.setBackground(COLOR_FONDO);

		panelSecundarioViaje.add(lbViaje, BorderLayout.CENTER);
		panelSecundarioViaje.setBackground(COLOR_FONDO);
		panelSecundario.add(panelSecundarioViaje, BorderLayout.SOUTH);
		Border bordeViaje = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloBordeViaje = BorderFactory.createTitledBorder(bordeViaje, "Viaje");
		panelSecundarioViaje.setBorder(tituloBordeViaje);

		// Tabla
		tableModel = new DefaultTableModel(datos, cabecera);
		tablaViaje = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // Las celdas no deben ser editables
			}
		};
		scrollPane = new JScrollPane(tablaViaje);
		tablaViaje.setPreferredScrollableViewportSize(new Dimension(150, 60)); // Tamaño deseado para la tabla
		tablaViaje.setFillsViewportHeight(true); // Rellenar el área de visualización
		tablaViaje.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSecundarioViaje.add(scrollPane, BorderLayout.CENTER);

		// Botones
		estilizarBoton(btnCrear);
		estilizarBoton(btnAplicar);

		panelPrincipal.add(panelPrincipalSur, BorderLayout.SOUTH);
		panelPrincipalSur.add(btnCrear, BorderLayout.SOUTH);
		panelPrincipalSur.add(btnAplicar, BorderLayout.SOUTH);

		panelSecundario.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelInfo.setBackground(COLOR_FONDO);

		estilizarLabel(lbOrigen);
		estilizarLabel(lbDestino);
		estilizarLabel(lbAsientos);
		estilizarLabel(lbMatricula);
		estilizarLabel(lbPlazas);

		Border bordeInfo = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloBordeInfo = BorderFactory.createTitledBorder(bordeInfo, "Info");
		panelInfoSE.setBorder(tituloBordeInfo);
		Border bordeInfoUsuario = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloBordeInfoUsuario = BorderFactory.createTitledBorder(bordeInfoUsuario, "Vehículo");
		panelInfoSW.setBorder(tituloBordeInfoUsuario);
		lbMatricula.setText("Matrícula: "); // + matricula);
		lbMatricula.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelInfoSW.add(lbMatricula);
		lbPlazas.setText("Plazas: "); // + plazas);
		lbPlazas.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelInfoSW.add(lbPlazas);

		comboCiudadesOrigen.setSelectedIndex(-1);
		comboCiudadesDestino.setSelectedIndex(-1);
		comboAsientos.setSelectedIndex(-1);

		estilizarComboBox(comboCiudadesOrigen);
		estilizarComboBox(comboCiudadesDestino);
		estilizarComboBox(comboAsientos);

		panelInfoN1.add(lbOrigen);
		panelInfoN1.add(comboCiudadesOrigen);
		panelInfoN2.add(lbDestino);
		panelInfoN2.add(comboCiudadesDestino);
		panelInfoN3.add(lbAsientos);
		panelInfoN3.add(comboAsientos);

		panelInfo.add(panelInfoN, BorderLayout.NORTH);
		panelInfoN.add(panelInfoN1, BorderLayout.NORTH);
		panelInfoN.add(panelInfoN2, BorderLayout.CENTER);
		panelInfoN.add(panelInfoN3, BorderLayout.SOUTH);
		panelInfo.add(panelInfoC, BorderLayout.CENTER);
		panelInfoC.add(panelInfoC1, BorderLayout.NORTH);
		panelInfoC.setBackground(COLOR_FONDO);
		panelInfo.add(panelInfoS, BorderLayout.SOUTH);
		panelInfoS.add(panelInfoSW, BorderLayout.WEST);
		panelInfoS.add(panelInfoSE, BorderLayout.CENTER);

		stylePanel(panelInfoN1);
		stylePanel(panelInfoN2);
		stylePanel(panelInfoN3);
		stylePanel(panelInfoC1);
		stylePanel(panelInfoSW);
		stylePanel(panelInfoSE);

		this.add(panelPrincipal);

		btnAplicar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String origen = (String) comboCiudadesOrigen.getSelectedItem();
				String destino = (String) comboCiudadesDestino.getSelectedItem();
				String asientosDisponibles = (String) comboAsientos.getSelectedItem();

				if (origen != null && destino != null && asientosDisponibles != null) {
					tableModel.addRow(new Object[] { origen, destino, asientosDisponibles });

					comboCiudadesOrigen.setSelectedIndex(-1);
					comboCiudadesDestino.setSelectedIndex(-1);
					comboAsientos.setSelectedIndex(-1);

				}
			}
		});
		btnCrear.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = tablaViaje.getSelectedRow();
		        if (filaSeleccionada == -1) {
		            JOptionPane.showMessageDialog(null, 
		                "Por favor, seleccione una fila de la tabla para crear el viaje.", 
		                "Fila no seleccionada", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        try {
		            gestorDB.connect();

		            // Extraer datos de la fila seleccionada
		            String origen = (String) tableModel.getValueAt(filaSeleccionada, 0);
		            String destino = (String) tableModel.getValueAt(filaSeleccionada, 1);
		            int asientosDisponibles = Integer.parseInt((String) tableModel.getValueAt(filaSeleccionada, 2));

		            // Obtener usuario logeado y su vehículo
		            Usuario usuarioLogeado = gestorDB.getUsuarioLogeado();
		            Vehiculo vehiculo = gestorDB.getVehiculoPorUsuario(usuarioLogeado.getDni());
		            if (vehiculo == null) {
		                JOptionPane.showMessageDialog(null, 
		                    "No se encontró un vehículo asociado al usuario logeado.", 
		                    "Error al crear viaje", 
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Crear el viaje (sin ID, ya que se genera automáticamente)
		            Viaje nuevoViaje = new Viaje(0, origen, destino, asientosDisponibles, usuarioLogeado, new ArrayList<>());

		            // Insertar en la base de datos
		            gestorDB.insertarViaje(nuevoViaje);

		            JOptionPane.showMessageDialog(null, 
		                "Viaje creado correctamente.", 
		                "Éxito", 
		                JOptionPane.INFORMATION_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, 
		                "Ocurrió un error al crear el viaje: " + ex.getMessage(), 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		        } finally {
		            gestorDB.close();
		        }
		    }
		});


	}

	TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
		JLabel result = new JLabel(value.toString());

		// con rgb negro claro
		result.setHorizontalAlignment(JLabel.CENTER);
		result.setForeground(new Color(60, 60, 60));
		result.setBackground(Color.white);
		result.setBorder(new EmptyBorder(2, 5, 2, 5));

		if (isSelected) {
			result.setBackground(new Color(33, 150, 243, 50));
			result.setForeground(Color.black);
		} else {
			result.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
			result.setForeground(Color.BLACK);
		}
		result.setOpaque(true);
		return result;
	};

	private void stylePanel(JPanel panel) {
		panel.setBackground(new Color(220, 240, 255));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	private void estilizarBoton(JButton boton) {
		boton.setFont(FUENTE_NORMAL);
		boton.setBackground(COLOR_BOTONES);
		boton.setForeground(Color.white);
		boton.setBorder(new EmptyBorder(10, 20, 10, 20));
		boton.setFocusPainted(false);
		boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

	private void estilizarComboBox(JComboBox<String> comboBox) {
		comboBox.setFont(FUENTE_NORMAL);
		comboBox.setBackground(Color.white);
		comboBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)),
				BorderFactory.createEmptyBorder(5, 12, 5, 12)));
	}

	private void estilizarLabel(JLabel label) {
		label.setFont(FUENTE_NORMAL);
		label.setForeground(COLOR_TEXTO);
		label.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
