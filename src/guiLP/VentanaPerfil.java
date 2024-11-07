package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domainLN.CharlaCarImpl;
import domainLN.Usuario;

public class VentanaPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;

	//Panel
	private JPanel panelColor = new JPanel(new BorderLayout());
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelNorte = new JPanel(new GridLayout(2, 2));
	private JPanel panelCentroGeneral = new JPanel(new BorderLayout());
	private JPanel panelCentro = new JPanel(new BorderLayout());
	private JPanel panelCentroA = new JPanel(new GridLayout(1, 5));
	private JPanel panelSur = new JPanel(new BorderLayout());
	private JPanel panelSur2 = new JPanel(new BorderLayout());

	//Label
	private JLabel lbNombre = new JLabel();
	private JLabel lbApellido = new JLabel();
	private JLabel lbDNI = new JLabel();
	private JLabel lblMatricula = new JLabel();
	private JLabel lblestrellaA1 = new JLabel();
	private JLabel lblestrellaA2 = new JLabel();
	private JLabel lblestrellaA3 = new JLabel();
	private JLabel lblestrellaA4 = new JLabel();
	private JLabel lblestrellaA5 = new JLabel();
	private JLabel lblestrellaG1 = new JLabel();
	private JLabel lblestrellaG2 = new JLabel();
	private JLabel lblestrellaG3 = new JLabel();
	private JLabel lblestrellaG4 = new JLabel();
	private JLabel lblestrellaG5 = new JLabel();
	
	//Tabla
	private JTable tablaMisViajes;
	private String[] cabecera = { "Matricula", "Propietario", "Origen", "Destino", "Asientos Totales", "Asientos Disponibles" };
	private String[][] datos;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	private JTable tablaViajesUnidos;
	private String[] cabecera2 = { "Matricula", "Propietario", "Origen", "Destino", "Asientos Totales", "Asientos Disponibles" };
	private String[][] datosViajesUnidos;
	private JScrollPane scrollPane2;
	private DefaultTableModel tableModel2;
	
	String[][] datosEjemplo;
		
	//Imagenes
	private ImageIcon estrellaA1 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaA1.png"));
	private ImageIcon estrellaA2 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaA2.png"));
	private ImageIcon estrellaA3 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaA3.png"));
	private ImageIcon estrellaA4 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaA4.png"));
	private ImageIcon estrellaA5 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaA5.png"));
	private ImageIcon estrellaG1 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaG1.png"));
	private ImageIcon estrellaG2 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaG2.png"));
	private ImageIcon estrellaG3 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaG3.png"));
	private ImageIcon estrellaG4 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaG4.png"));
	private ImageIcon estrellaG5 = new ImageIcon(VentanaPerfil.class.getResource("/images/estrellaG5.png"));

	public VentanaPerfil() {
		
		//setModal(true); solo si queremos que no se pueda interactuar con la ventana principal mientras esta este abierta
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(420, 422);
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
    	panelNorte.setBackground(Color.WHITE);
    		
		lbNombre.setText("Nombre:  " + CharlaCarImpl.getCharlaCarImpl().getLogedUser().getNombre());
		panelNorte.add(lbNombre, BorderLayout.NORTH);
		lbNombre.setBorder(new EmptyBorder(2,5,2,5));
		lbApellido.setText("Apellido:  " + CharlaCarImpl.getCharlaCarImpl().getLogedUser().getApellido());
		panelNorte.add(lbApellido, BorderLayout.CENTER);
		lbApellido.setBorder(new EmptyBorder(2,5,2,5));
		lbDNI.setText("DNI:  " + CharlaCarImpl.getCharlaCarImpl().getLogedUser().getDni());
		panelNorte.add(lbDNI, BorderLayout.SOUTH);
		lbDNI.setBorder(new EmptyBorder(2,5,2,5));
		
		Usuario usuarioLogeado = CharlaCarImpl.getCharlaCarImpl().getLogedUser();
		String matricula = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
		        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
		        .map(viaje -> viaje.getVehiculo().getMatricula())
		        .findFirst()
		        .orElse("No disponible");
		lblMatricula.setText("Matrícula:  " + matricula);
		panelNorte.add(lblMatricula, BorderLayout.SOUTH);
		lblMatricula.setBorder(new EmptyBorder(2,5,2,5));
		
		lbDNI.setFont(new Font("Arial", Font.BOLD, 12));

		//Rating
      	Border bordeRating = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeRating = BorderFactory.createTitledBorder(bordeRating,"Rating");
      	panelCentro.setBorder(tituloBordeRating);
    	panelCentro.setBackground(Color.WHITE);
      	panelCentro.add(panelCentroA, BorderLayout.NORTH);
    	panelCentroA.setBackground(Color.WHITE);
    	
    	lblestrellaA1.setIcon(estrellaA1);
    	lblestrellaA2.setIcon(estrellaA2);
    	lblestrellaA3.setIcon(estrellaA3);
    	lblestrellaA4.setIcon(estrellaA4);
    	lblestrellaA5.setIcon(estrellaA5);
    	lblestrellaG1.setIcon(estrellaG1);
    	lblestrellaG2.setIcon(estrellaG2);
    	lblestrellaG3.setIcon(estrellaG3);
    	lblestrellaG4.setIcon(estrellaG4);
    	lblestrellaG5.setIcon(estrellaG5);
    	panelCentroA.add(lblestrellaA1);
    	panelCentroA.add(lblestrellaA2);
    	panelCentroA.add(lblestrellaA3);
    	panelCentroA.add(lblestrellaA4);
    	panelCentroA.add(lblestrellaA5);
    	ratingEstrellas();
    	
    	//Viajes
      	Border bordeViajes = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeViajes = BorderFactory.createTitledBorder(bordeViajes,"Mis Viajes");
      	panelSur2.setBorder(tituloBordeViajes);
      	panelSur2.setBackground(Color.WHITE);
     	Border bordeViajesUnidos = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloViajesUnidos = BorderFactory.createTitledBorder(bordeViajesUnidos,"Viajes Unidos");
      	panelSur.setBorder(tituloViajesUnidos);
      	panelSur.setBackground(Color.WHITE);
    	datos = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
    	        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado)) // Filtra por usuario logueado
    	        .map(viaje -> new String[] {
    	                viaje.getVehiculo().getMatricula(),
    	                viaje.getVehiculo().getPropietario().getNombre(),
    	                viaje.getOrigen(),
    	                viaje.getDestino(),
    	                String.valueOf(viaje.getVehiculo().getPlazas()),
    	                String.valueOf(viaje.getVehiculo().getPlazas() - viaje.getEspaciosDisponibles())
    	        })
    	        .toArray(String[][]::new);
    	
		tableModel = new DefaultTableModel(datos, cabecera);
		tablaMisViajes = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};	
		scrollPane = new JScrollPane(tablaMisViajes);
		tablaMisViajes.setDefaultRenderer(Object.class, cellRenderer);
		tablaMisViajes.setPreferredScrollableViewportSize(new Dimension(150, 60)); // Tamaño deseado para la tabla
		tablaMisViajes.setFillsViewportHeight(true); // Rellenar el área de visualización
		tablaMisViajes.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSur2.add(scrollPane, BorderLayout.CENTER);
		
    	datosViajesUnidos = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
    	        .filter(viaje -> viaje.getListaPasajeros().contains(usuarioLogeado)) // Filtra por usuario logueado
    	        .map(viaje -> new String[] {
    	                viaje.getVehiculo().getMatricula(),
    	                viaje.getVehiculo().getPropietario().getNombre(),
    	                viaje.getOrigen(),
    	                viaje.getDestino(),
    	                String.valueOf(viaje.getVehiculo().getPlazas()),
    	                String.valueOf(viaje.getVehiculo().getPlazas() - viaje.getEspaciosDisponibles())
    	        })
    	        .toArray(String[][]::new);
		tableModel2 = new DefaultTableModel(datosViajesUnidos, cabecera2);
		tablaViajesUnidos = new JTable(tableModel2) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};	
		scrollPane2 = new JScrollPane(tablaViajesUnidos);
		tablaViajesUnidos.setDefaultRenderer(Object.class, cellRenderer);
		tablaViajesUnidos.setPreferredScrollableViewportSize(new Dimension(150, 60)); // Tamaño deseado para la tabla
		tablaViajesUnidos.setFillsViewportHeight(true); // Rellenar el área de visualización
		tablaViajesUnidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSur.add(scrollPane2, BorderLayout.CENTER);
    	
		panelCentroGeneral.add(panelCentro, BorderLayout.NORTH);
		panelCentroGeneral.add(panelSur, BorderLayout.SOUTH);
    	panelPrincipal.add(panelNorte, BorderLayout.NORTH);
    	panelPrincipal.add(panelCentroGeneral, BorderLayout.CENTER);
    	panelPrincipal.add(panelSur2, BorderLayout.SOUTH);
    	
		panelColor.add(panelPrincipal);
		panelColor.setBorder(new EmptyBorder(20,20,20,20));
		panelColor.setBackground(new Color(237, 242, 255));
    	add(panelColor);
		
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
	public void ratingEstrellas() {
		float rating;
		rating = CharlaCarImpl.getCharlaCarImpl().getLogedUser().getRating(); 
		if(rating == 0) {
			panelCentroA.removeAll();
			panelCentroA.add(lblestrellaG1);
			panelCentroA.add(lblestrellaG2);
			panelCentroA.add(lblestrellaG3);
			panelCentroA.add(lblestrellaG4);
			panelCentroA.add(lblestrellaG5);
			panelCentroA.revalidate();
			panelCentroA.repaint();
		} else if (rating > 0 && rating <= 1){
			panelCentroA.removeAll();
			panelCentroA.add(lblestrellaA1);
			panelCentroA.add(lblestrellaG2);
			panelCentroA.add(lblestrellaG3);
			panelCentroA.add(lblestrellaG4);
			panelCentroA.add(lblestrellaG5);
			panelCentroA.revalidate();
			panelCentroA.repaint();
		} else if (rating > 1 && rating <= 2){
			panelCentroA.removeAll();
			panelCentroA.add(lblestrellaA1);
			panelCentroA.add(lblestrellaA2);
			panelCentroA.add(lblestrellaG3);
			panelCentroA.add(lblestrellaG4);
			panelCentroA.add(lblestrellaG5);
			panelCentroA.revalidate();
			panelCentroA.repaint();
		} else if (rating > 2 && rating <= 3){
			panelCentroA.removeAll();
			panelCentroA.add(lblestrellaA1);
			panelCentroA.add(lblestrellaA2);
			panelCentroA.add(lblestrellaA3);
			panelCentroA.add(lblestrellaG4);
			panelCentroA.add(lblestrellaG5);
			panelCentroA.revalidate();
			panelCentroA.repaint();
		} else if (rating > 3 && rating <= 4){
			panelCentroA.removeAll();
			panelCentroA.add(lblestrellaA1);
			panelCentroA.add(lblestrellaA2);
			panelCentroA.add(lblestrellaA3);
			panelCentroA.add(lblestrellaA4);
			panelCentroA.add(lblestrellaG5);
			panelCentroA.revalidate();
			panelCentroA.repaint();
		} else if (rating > 4 && rating <= 5){
			panelCentroA.removeAll();
			panelCentroA.add(lblestrellaA1);
			panelCentroA.add(lblestrellaA2);
			panelCentroA.add(lblestrellaA3);
			panelCentroA.add(lblestrellaA4);
			panelCentroA.add(lblestrellaA5);
			panelCentroA.revalidate();
			panelCentroA.repaint();
		}
	}
}
