package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Stream;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domainLN.CharlaCarImpl;
import domainLN.CharlaCarService;
import domainLN.TipoVehiculo;
import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.Viaje;

public class VentanaCrearViaje extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Usuario user;
	//Paneles
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelPrincipalSur = new JPanel();
	private JPanel panelSecundario = new JPanel(new BorderLayout());
	private JPanel panelSecundarioViaje = new JPanel(new BorderLayout());
	private JPanel panelInfo = new JPanel(new  BorderLayout());
	private JPanel panelInfoN = new JPanel(new BorderLayout());
	private JPanel panelInfoN1 = new JPanel(new GridLayout(1,2));
	private JPanel panelInfoN2 = new JPanel(new GridLayout(1,2));
	private JPanel panelInfoN3 = new JPanel(new GridLayout(1,2));
	private JPanel panelInfoC = new JPanel(new BorderLayout());
	private JPanel panelInfoC1 = new JPanel(new GridLayout(1,2));
	private JPanel panelInfoS = new JPanel(new BorderLayout());
	private JPanel panelInfoSW = new JPanel(new GridLayout(3,1));
	private JPanel panelInfoSE = new JPanel(new GridLayout(1,2));
	
	//Boton
	private JButton btnCrear = new JButton("Crear");
	private JButton btnAplicar = new JButton("Aplicar");
	
	//Label
	private JLabel lbViaje = new JLabel();
	private JLabel lbMatricula = new JLabel();
	private JLabel lbVehiculo = new JLabel();
	private JLabel lbPlazas = new JLabel();
	private JLabel lbOrigen = new JLabel("Origen: ");
	private JLabel lbDestino = new JLabel("Destino: ");
	private JLabel lbAsientoDisp = new JLabel("Asientos disponibles: ");
	private JLabel lbAsientoOcupados = new JLabel("Asientos totales: ");

	//TextField
	private JTextField txtInfo = new JTextField();

	//ComboBox
	private String[] numAsientos = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private JComboBox<String> comboAsientosDisp= new JComboBox<>(numAsientos);
	private JComboBox<String> comboAsientosOcupados= new JComboBox<>(numAsientos);
	
	private String[] ciudadesEspaña = { "Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", 
            "Palma", "Las Palmas", "Bilbao", "Alicante", "Córdoba", "Valladolid", 
            "Vigo", "Gijón", "Hospitalet de Llobregat", "La Coruña", "Vitoria", "Granada" };
	private JComboBox<String> comboCiudadesOrigen = new JComboBox<>(ciudadesEspaña);
	private JComboBox<String> comboCiudadesDestino = new JComboBox<>(ciudadesEspaña);

	//Tabla
	private JTable tablaViaje;
	private String[] cabecera = { "Origen", "Destino", "Asientos Disponibles", "Asientos Ocupados", "Info" };
	private Object[][] datos = {};
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	//Datos
	private Stream<Viaje> datosEjemplo;
	
	public VentanaCrearViaje() {
	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 550);
		setTitle("CharlaCar");
		setVisible(true);
		setLocationRelativeTo(null);
		
		setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());
		
		Usuario usuarioLogeado = CharlaCarImpl.getCharlaCarImpl().getLogedUser();
		String matricula = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
		        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
		        .map(viaje -> viaje.getVehiculo().getMatricula())
		        .findFirst()
		        .orElse("No disponible");
		TipoVehiculo tipoVehiculo = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
		        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
		        .map(viaje -> viaje.getVehiculo().getTipo())
		        .findFirst()
		        .orElse(TipoVehiculo.AUTOBUS);
		Vehiculo vehiculo = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
		        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
		        .map(viaje -> viaje.getVehiculo())
		        .findFirst()
		        .orElse(new Vehiculo());
		Integer plazas = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
		        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
		        .map(viaje -> viaje.getVehiculo().getPlazas())
		        .findFirst()
		        .orElse(0);
		
		panelPrincipal.setBackground(new Color(217, 239, 248));
		panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelSecundario.setBackground(Color.WHITE);
		panelPrincipal.add(panelSecundario);
		Border bordeSecundario = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeSecundario = BorderFactory.createTitledBorder(bordeSecundario,"Crear Viaje");
      	panelSecundario.setBorder(tituloBordeSecundario);
		panelPrincipalSur.setBackground(Color.WHITE);
		
		panelSecundarioViaje.add(lbViaje, BorderLayout.CENTER);
		panelSecundario.add(panelSecundarioViaje, BorderLayout.SOUTH);
		Border bordeViaje = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeViaje = BorderFactory.createTitledBorder(bordeViaje,"Viaje");
      	panelSecundarioViaje.setBorder(tituloBordeViaje);
      	
      	//Tabla
		tableModel = new DefaultTableModel(datos, cabecera);
		tablaViaje = new JTable(tableModel) {
		    private static final long serialVersionUID = 1L;

		    public boolean isCellEditable(int row, int column) {
		        return false;  // Las celdas no deben ser editables
		    }
		};
		scrollPane = new JScrollPane(tablaViaje);
		tablaViaje.setPreferredScrollableViewportSize(new Dimension(150, 60)); // Tamaño deseado para la tabla
		tablaViaje.setFillsViewportHeight(true); // Rellenar el área de visualización
		tablaViaje.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSecundarioViaje.add(scrollPane, BorderLayout.CENTER);
		
      	//Botones
		panelPrincipal.add(panelPrincipalSur, BorderLayout.SOUTH);
		panelPrincipalSur.add(btnCrear, BorderLayout.SOUTH);
		btnCrear.setBackground(new Color(33, 150, 243));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
		panelPrincipalSur.add(btnAplicar, BorderLayout.SOUTH);
		btnAplicar.setBackground(new Color(33, 150, 243));
		btnAplicar.setForeground(Color.WHITE);
		btnAplicar.setFont(new Font("Arial", Font.BOLD, 14));
		
		panelSecundario.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setBorder(new EmptyBorder(20, 20, 20, 20));

		Border bordeInfo = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeInfo = BorderFactory.createTitledBorder(bordeInfo,"Info");
      	panelInfoSE.setBorder(tituloBordeInfo);
      	panelInfoSE.add(txtInfo, BorderLayout.CENTER); 
		Border bordeInfoUsuario = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeInfoUsuario = BorderFactory.createTitledBorder(bordeInfoUsuario,"Vehículo");
      	panelInfoSW.setBorder(tituloBordeInfoUsuario);
      	lbMatricula.setText("Matrícula: " + matricula);
      	lbMatricula.setBorder(new EmptyBorder(5,5,5,5));
      	panelInfoSW.add(lbMatricula);
      	lbVehiculo.setText("Vehículo: " + tipoVehiculo);
      	lbVehiculo.setBorder(new EmptyBorder(5,5,5,5));
      	panelInfoSW.add(lbVehiculo);
      	lbPlazas.setText("Plazas: " + plazas);
      	lbPlazas.setBorder(new EmptyBorder(5,5,5,5));
      	panelInfoSW.add(lbPlazas);

		comboCiudadesOrigen.setSelectedIndex(-1);
		comboCiudadesOrigen.setBorder(new EmptyBorder(5, 5, 5, 5));
		comboCiudadesDestino.setSelectedIndex(-1);
		comboCiudadesDestino.setBorder(new EmptyBorder(5, 5, 5, 5));
		comboAsientosDisp.setSelectedIndex(-1);
		comboAsientosDisp.setBorder(new EmptyBorder(5, 5, 5, 5));
		comboAsientosOcupados.setSelectedIndex(-1);
		comboAsientosOcupados.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelInfoN1.add(lbOrigen);
		panelInfoN1.add(comboCiudadesOrigen);
		panelInfoN2.add(lbDestino);
		panelInfoN2.add(comboCiudadesDestino);	
		panelInfoN3.add(lbAsientoDisp);
		panelInfoN3.add(comboAsientosDisp);		
		panelInfoC1.add(lbAsientoOcupados);
		panelInfoC1.add(comboAsientosOcupados); 	
      	
		panelInfo.add(panelInfoN, BorderLayout.NORTH);
		panelInfoN.add(panelInfoN1, BorderLayout.NORTH);
		panelInfoN.add(panelInfoN2, BorderLayout.CENTER);
		panelInfoN.add(panelInfoN3, BorderLayout.SOUTH);
		panelInfo.add(panelInfoC, BorderLayout.CENTER);
		panelInfoC.add(panelInfoC1, BorderLayout.NORTH);
		panelInfo.add(panelInfoS, BorderLayout.SOUTH);
		panelInfoS.add(panelInfoSW, BorderLayout.WEST);
		panelInfoS.add(panelInfoSE, BorderLayout.CENTER);

		this.add(panelPrincipal);
		
		btnAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String origen = (String) comboCiudadesOrigen.getSelectedItem();
			    String destino = (String) comboCiudadesDestino.getSelectedItem();
			    String asientosDisponibles = (String) comboAsientosDisp.getSelectedItem();
			    String asientosOcupados = (String) comboAsientosOcupados.getSelectedItem();
			    String info = txtInfo.getText();
	
			    if (origen != null && destino != null && asientosDisponibles != null && asientosOcupados != null) {
		            tableModel.addRow(new Object[]{ origen, destino, asientosDisponibles, asientosOcupados, info });

		            comboCiudadesOrigen.setSelectedIndex(-1);
		            comboCiudadesDestino.setSelectedIndex(-1);
		            comboAsientosDisp.setSelectedIndex(-1);
		            comboAsientosOcupados.setSelectedIndex(-1);
		            txtInfo.setText("");

		        }				
			}
		});
		btnCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        //Verificar que haya una fila seleccionada en la tabla
		        int filaSeleccionada = tablaViaje.getSelectedRow();
		        if (filaSeleccionada == -1) {
		            javax.swing.JOptionPane.showMessageDialog(
		                null,
		                "Por favor, seleccione una fila de la tabla para crear el viaje.",
		                "Fila no seleccionada",
		                javax.swing.JOptionPane.WARNING_MESSAGE
		            );
		            return;
		        }

		        //Extraer datos de la fila seleccionada
		        String origen = (String) tableModel.getValueAt(filaSeleccionada, 0);
		        String destino = (String) tableModel.getValueAt(filaSeleccionada, 1);
		        int asientosDisponibles = Integer.parseInt((String) tableModel.getValueAt(filaSeleccionada, 2));
		        int asientosOcupados = Integer.parseInt((String) tableModel.getValueAt(filaSeleccionada, 3));
		        String info = (String) tableModel.getValueAt(filaSeleccionada, 4);
		        //Crear un nuevo Viaje
		        Viaje nuevoViaje = new Viaje(origen, destino, asientosDisponibles, asientosOcupados, null, info);
		        nuevoViaje.setVehiculo(vehiculo);
		        CharlaCarImpl.getCharlaCarImpl().addViaje(nuevoViaje);
                JOptionPane.showMessageDialog(null, "Viaje creado correctamente ");
			}
		});
	}
	
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

}
