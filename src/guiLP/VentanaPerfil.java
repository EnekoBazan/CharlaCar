package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domainLN.ButtonEditor;
import domainLN.CharlaCarImpl;
import domainLN.Usuario;
import domainLN.Vehiculo;

public class VentanaPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;

	ButtonEditor buttonEditor = new ButtonEditor();

	//Panel
	private JPanel panelColor = new JPanel(new BorderLayout());
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelNorte = new JPanel(new GridLayout(2, 2));
	private JPanel panelCentroGeneral = new JPanel(new BorderLayout());
	private JPanel panelCentroN = new JPanel(new BorderLayout());
	private JPanel panelCentroA = new JPanel(new GridLayout(1, 5));
	private JPanel panelCentroCentro = new JPanel(new BorderLayout());
	private JPanel panelCentroS = new JPanel(new BorderLayout());

	private JPanel panelSur = new JPanel();

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
	private String[] cabecera = { "Matricula", "Propietario", "Origen", "Destino", "Asientos Totales", "Asientos Disponibles", "Acción" };
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	private JTable tablaViajesUnidos;
	private String[] cabecera2 = { "Matricula", "Propietario", "Origen", "Destino", "Asientos Totales", "Asientos Disponibles", "Acción" };
	private JScrollPane scrollPane2;
	private DefaultTableModel tableModel2;
	
	String[][] datosEjemplo;
		
	//Imagenes
	private ImageIcon estrellaA1 = new ImageIcon("resources/images/estrellaA1.png");
	private ImageIcon estrellaA2 = new ImageIcon("resources/images/estrellaA2.png");
	private ImageIcon estrellaA3 = new ImageIcon("resources/images/estrellaA3.png");
	private ImageIcon estrellaA4 = new ImageIcon("resources/images/estrellaA4.png");
	private ImageIcon estrellaA5 = new ImageIcon("resources/images/estrellaA5.png");
	private ImageIcon estrellaG1 = new ImageIcon("resources/images/estrellaG1.png");
	private ImageIcon estrellaG2 = new ImageIcon("resources/images/estrellaG2.png");
	private ImageIcon estrellaG3 = new ImageIcon("resources/images/estrellaG3.png");
	private ImageIcon estrellaG4 = new ImageIcon("resources/images/estrellaG4.png");
	private ImageIcon estrellaG5 = new ImageIcon("resources/images/estrellaG5.png");

	//Botones
	JButton btnSalir = new JButton("Salir");
	JButton btnEliminar = new JButton("Eliminar");

	public VentanaPerfil() {
		
		//setModal(true); solo si queremos que no se pueda interactuar con la ventana principal mientras esta este abierta
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(460, 480);

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
		
//		Usuario usuarioLogeado = CharlaCarImpl.getCharlaCarImpl().getLogedUser();
//		String matricula = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
//		        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado))
//		        .map(viaje -> viaje.getVehiculo().getMatricula())
//		        .findFirst()
//		        .orElse("No disponible");
//		lblMatricula.setText("Matrícula:  " + matricula);
		panelNorte.add(lblMatricula, BorderLayout.SOUTH);
		lblMatricula.setBorder(new EmptyBorder(2,5,2,5));
		
		lbDNI.setFont(new Font("Arial", Font.BOLD, 12));

		//Rating
      	Border bordeRating = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeRating = BorderFactory.createTitledBorder(bordeRating,"Rating");
      	panelCentroN.setBorder(tituloBordeRating);
    	panelCentroN.setBackground(Color.WHITE);
      	panelCentroN.add(panelCentroA, BorderLayout.NORTH);
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
      	panelCentroCentro.setBorder(tituloBordeViajes);
      	panelCentroCentro.setBackground(Color.WHITE);
     	Border bordeViajesUnidos = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloViajesUnidos = BorderFactory.createTitledBorder(bordeViajesUnidos,"Viajes Unidos");
      	panelCentroS.setBorder(tituloViajesUnidos);
      	panelCentroS.setBackground(Color.WHITE);
//      	Object[][] datos = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
//    	        .filter(viaje -> viaje.getVehiculo().getPropietario().equals(usuarioLogeado)) // Filtra por usuario logueado
//    	        .map(viaje -> new String[] {
//    	                viaje.getVehiculo().getMatricula(),
//    	                viaje.getVehiculo().getPropietario().getNombre(),
//    	                viaje.getOrigen(),
//    	                viaje.getDestino(),
//    	                String.valueOf(viaje.getVehiculo().getPlazas()),
//    	                String.valueOf(viaje.getVehiculo().getPlazas() - viaje.getEspaciosDisponibles()),
//    	                "Eliminar"
//    	        })
//    	        .toArray(String[][]::new);
    	
//		tableModel = new DefaultTableModel(datos, cabecera);
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
		panelCentroCentro.add(scrollPane, BorderLayout.NORTH);
		
//		Object[][] datosViajesUnidos = CharlaCarImpl.getCharlaCarImpl().getViajes().stream()
//    	        .filter(viaje -> viaje.getListaPasajeros().contains(usuarioLogeado)) // Filtra por usuario logueado
//    	        .map(viaje -> new String[] {
//    	                viaje.getVehiculo().getMatricula(),
//    	                viaje.getVehiculo().getPropietario().getNombre(),
//    	                viaje.getOrigen(),
//    	                viaje.getDestino(),
//    	                String.valueOf(viaje.getVehiculo().getPlazas()),
//    	                String.valueOf(viaje.getVehiculo().getPlazas() - viaje.getEspaciosDisponibles()),
//    	                "Salir"    	        })
//    	        .toArray(String[][]::new);
//		tableModel2 = new DefaultTableModel(datosViajesUnidos, cabecera2);
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
		panelCentroS.add(scrollPane2, BorderLayout.CENTER);
    	
		// Botones
		panelSur.add(btnEliminar);
		btnEliminar.setBackground(new Color(33, 150, 243));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		panelSur.add(btnSalir);
		btnSalir.setBackground(new Color(33, 150, 243));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Paneles
		panelCentroGeneral.add(panelCentroN, BorderLayout.NORTH);
		panelCentroGeneral.add(panelCentroCentro, BorderLayout.CENTER);
		panelCentroGeneral.add(panelCentroS, BorderLayout.SOUTH);
		
    	panelPrincipal.add(panelNorte, BorderLayout.NORTH);
    	panelPrincipal.add(panelCentroGeneral, BorderLayout.CENTER);
    	panelPrincipal.add(panelSur, BorderLayout.SOUTH);
    	
		panelColor.add(panelPrincipal);
		panelColor.setBorder(new EmptyBorder(20,20,20,20));
		panelColor.setBackground(new Color(237, 242, 255));
    	add(panelColor);
    	
//    	System.out.println(usuarioLogeado.getViajes());
    	btnSalir.addActionListener(new ActionListener() {

    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Obtener la fila seleccionada de la tabla
    	        int numFila = tablaViajesUnidos.getSelectedRow();
    	        
    	        if (numFila != -1) { // Verifica si se ha seleccionado una fila válida
    	            // Aquí, eliminamos la fila de la tabla (en la base de datos o donde se almacenen los datos)
    	            // Suponemos que tienes una forma de acceder a los viajes del usuario logueado.

    	            String matricula = (String) tableModel2.getValueAt(numFila, 0);
    	            String propietario = (String) tableModel2.getValueAt(numFila, 1);
    	            String origen = (String) tableModel2.getValueAt(numFila, 2);
    	            String destino = (String) tableModel2.getValueAt(numFila, 3);
    	            int asientosTotal = Integer.parseInt((String) tableModel2.getValueAt(numFila, 4));
    	            int asientosDisponibles = Integer.parseInt((String) tableModel2.getValueAt(numFila, 5));

    	            // Crear un nuevo objeto viaje que corresponde con los datos seleccionados
    	            Vehiculo vehiculo = new Vehiculo(matricula, asientosTotal, 
    	                new Usuario(propietario, "", "", "", true, 0.0f)); // Crear un vehículo
//    	            Viaje viaje = new Viaje(origen, destino, asientosDisponibles, 0, null, "");
//    	            viaje.setVehiculo(vehiculo);

    	            // Eliminar el viaje del usuario logueado
//    	            CharlaCarImpl.getCharlaCarImpl().deleteViajeToUsuario(viaje);  // Método hipotético para eliminar un viaje
    	            
    	            // Eliminar la fila de la tabla
    	            tableModel2.removeRow(numFila);
    	            
    	            // Actualizar la tabla con los datos más recientes
//    	            actualizarTablaViajesUnidos();
    	            
    	            // Mostrar mensaje de éxito
    	            JOptionPane.showMessageDialog(null, "Has salido del viaje exitosamente.");
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Debes seleccionar un viaje.");
    	        }
    	    }
    	});
        

    	
    	btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener la fila seleccionada de la tabla
    	        int numFila = tablaMisViajes.getSelectedRow();
    	        
    	        if (numFila != -1) { // Verifica si se ha seleccionado una fila válida
    	            // Aquí, eliminamos la fila de la tabla (en la base de datos o donde se almacenen los datos)
    	            // Suponemos que tienes una forma de acceder a los viajes del usuario logueado.

    	            String matricula = (String) tableModel.getValueAt(numFila, 0);
    	            String propietario = (String) tableModel.getValueAt(numFila, 1);
    	            String origen = (String) tableModel.getValueAt(numFila, 2);
    	            String destino = (String) tableModel.getValueAt(numFila, 3);
    	            int asientosTotal = Integer.parseInt((String) tableModel.getValueAt(numFila, 4));
    	            int asientosDisponibles = Integer.parseInt((String) tableModel.getValueAt(numFila, 5));

    	            // Crear un nuevo objeto viaje que corresponde con los datos seleccionados
    	            Vehiculo vehiculo = new Vehiculo(matricula, asientosTotal, 
    	                new Usuario(propietario, "", "", "", true, 0.0f)); // Crear un vehículo
//    	            Viaje viaje = new Viaje(origen, destino, asientosDisponibles, 0, null, "");
//    	            viaje.setVehiculo(vehiculo);

    	            
    	            // Eliminar el viaje del usuario logueado
    	            CharlaCarImpl.getCharlaCarImpl().getViajes().stream();  // Método hipotético para eliminar un viaje
    	            
    	            // Eliminar la fila de la tabla
    	            tableModel.removeRow(numFila);
    	            
    	            // Actualizar la tabla con los datos más recientes
//    	            actualizarTablaViajesUnidos();
    	            
    	            // Mostrar mensaje de éxito
    	            JOptionPane.showMessageDialog(null, "El viaje ha sido eliminado exitosamente.");
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Debes seleccionar un viaje.");
    	        }
    	    }
    	});
    	tablaMisViajes.getColumnModel().getColumn(6).setCellRenderer(buttonRenderer);
    	tablaViajesUnidos.getColumnModel().getColumn(6).setCellRenderer(buttonRenderer);
    	tablaMisViajes.getColumnModel().getColumn(6).setCellEditor(buttonEditor);
    	tablaViajesUnidos.getColumnModel().getColumn(6).setCellEditor(buttonEditor);
	}
	TableCellRenderer buttonRenderer = new DefaultTableCellRenderer() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//	        if (value instanceof String && value.equals("")) {  // Check if the value is "Salir"
//	            JButton button = new JButton("");
//	            button.setBackground(new Color(33, 150, 243));
//	            button.setForeground(Color.WHITE);
//	            button.setFont(new Font("Arial", Font.BOLD, 12));
//	            button.setBorder(new EmptyBorder(5,5,5,5));
//	            
//	            // Acción al pasar el ratón por encima
//	            button.addMouseListener(new java.awt.event.MouseAdapter() {
//	                @Override
//	                public void mouseEntered(java.awt.event.MouseEvent evt) {
//	                    button.setBackground(new Color(41, 121, 255));  // Color al pasar el ratón
//	                }
//	                @Override
//	                public void mouseExited(java.awt.event.MouseEvent evt) {
//	                    button.setBackground(new Color(33, 150, 243));  // Color original
//	                }
//	            });
	            
//	            // Acción al hacer clic en el botón
//	            button.addActionListener(new ActionListener() {
//	                @Override
//	                public void actionPerformed(ActionEvent e) {
//	                    // Logic to handle the "Salir" action
//	                    int numFila = table.getSelectedRow();
//	                    if (numFila != -1) {
//	                        // Handle the action (e.g., remove the row)
//	                        ((DefaultTableModel) table.getModel()).removeRow(numFila);
//	                        JOptionPane.showMessageDialog(null, "Has salido del viaje.");
//	                    }
//	                }
//	            });
//
//	            return button;
//	        }
	        // Default renderer for other values
	        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    }
	};

	

	// Personalizamos el renderizador para los botones
	TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
	    JLabel result = new JLabel(value.toString());

	    // Si la columna es la de los botones (última columna)
	    if (column == table.getColumnCount() - 1) {
	        JButton boton = new JButton(value.toString());
	        boton.setBackground(new Color(33, 150, 243));
	        boton.setForeground(Color.WHITE);
	        boton.setFont(new Font("Arial", Font.BOLD, 12));

	        // Acción para el botón "Eliminar" en Mis Viajes
	        if ("Eliminar".equals(value)) {
	            boton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    // Aquí puedes implementar la lógica para eliminar el viaje
	                    int numFila = table.getSelectedRow();
	                    if (numFila != -1) {
	                        // Eliminar el viaje
	                        String matricula = (String) tableModel.getValueAt(numFila, 0);
	                        // Lógica para eliminar el viaje (en base de datos, lista, etc.)
	                        // Luego eliminar la fila de la tabla
	                        tableModel.removeRow(numFila);
	                        JOptionPane.showMessageDialog(null, "El viaje ha sido eliminado.");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Debes seleccionar un viaje.");
	                    }
	                }
	            });
	        }

	        // Acción para el botón "Salir" en Viajes Unidos
	        else if ("Salir".equals(value)) {
	            boton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    // Aquí puedes implementar la lógica para salir del viaje
	                    int numFila = table.getSelectedRow();
	                    if (numFila != -1) {
	                        // Lógica para salir del viaje (en base de datos, lista, etc.)
	                        // Luego eliminar la fila de la tabla
	                        tableModel2.removeRow(numFila);
	                        JOptionPane.showMessageDialog(null, "Has salido del viaje.");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Debes seleccionar un viaje.");
	                    }
	                }
	            });
	        }

	        return boton; // Retorna el botón para mostrarlo en la celda
	    }

	    result.setHorizontalAlignment(JLabel.CENTER);
	    result.setForeground(new Color(60, 60, 60));
	    result.setBackground(Color.white);
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
