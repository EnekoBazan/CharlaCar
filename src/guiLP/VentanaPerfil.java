package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import db.GestorBD;
import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.Viaje;
import io.Propiedades;

public class VentanaPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;

	GestorBD gestorDB = GestorBD.getGestorDB();
	
  	private Propiedades propiedades;
  	public Propiedades getPropiedades() {
  		return propiedades;
  	}
	
//	ButtonEditor buttonEditor = new ButtonEditor();

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
	private String[] cabecera = {"Origen", "Destino", "Plazas", "DNI del conductor"};
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;

	private JTable tablaViajesUnidos;
	private String[] cabecera2 = {"Origen", "Destino", "Plazas", "DNI del conductor"};
	private JScrollPane scrollPane2;
	private DefaultTableModel tableModel2;
	
	private int filaMouseOver = -1;
	//private int colMouseOver = -1;
	
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
        gestorDB.connect();

//        URL imagePath = getClass().getResource("/images/favicon.png");
//        ImageIcon icon = new ImageIcon(imagePath);
//        setIconImage(icon.getImage());
        
//		ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource("/resources/images/favicon.png"));//imagen generada con IA
//		setIconImage(icon.getImage());
		
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(217, 239, 248));
		
		//Usuario
      	Border bordeUsuario = BorderFactory.createLineBorder(Color.BLACK);
      	Border tituloBordeUsuario = BorderFactory.createTitledBorder(bordeUsuario,"Usuario");
    	panelNorte.setBorder(tituloBordeUsuario);
    	panelNorte.setBackground(Color.WHITE);

    	Usuario usuarioLogeado = gestorDB.getUsuarioLogeado();
        Vehiculo vehiculo = gestorDB.getVehiculoPorUsuario(usuarioLogeado.getDni());
        
		lbNombre.setText("Nombre:  " + usuarioLogeado.getNombre());
		panelNorte.add(lbNombre, BorderLayout.NORTH);
		lbNombre.setBorder(new EmptyBorder(2,5,2,5));
		lbApellido.setText("Apellido:  " + usuarioLogeado.getApellido());
		panelNorte.add(lbApellido, BorderLayout.CENTER);
		lbApellido.setBorder(new EmptyBorder(2,5,2,5));
		lbDNI.setText("DNI:  " + usuarioLogeado.getDni());
		panelNorte.add(lbDNI, BorderLayout.SOUTH);
		lbDNI.setBorder(new EmptyBorder(2,5,2,5));
		
		lblMatricula.setText("Matrícula:  " + vehiculo.getMatricula());
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

        // Configuración de las tabla
        tableModel = new DefaultTableModel(null, cabecera) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Solo permitir edición en la columna de botones
                return column == 4;
            }
        };
        
     // Estilo dinámico para las filas
 


        
        tablaMisViajes = new JTable(tableModel);
	
		tablaMisViajes = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};	
		tablaMisViajes.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            label.setForeground(isSelected ? Color.BLACK : Color.DARK_GRAY);
            return label;
        });
        
     // Filtro dinámico para buscar en la tabla
        JTextField filtro = new JTextField(20);
        filtro.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            private void aplicarFiltro() {
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                tablaMisViajes.setRowSorter(sorter);
                String texto = filtro.getText();
                if (texto.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }
            }
        });
		scrollPane = new JScrollPane(tablaMisViajes);
//		tablaMisViajes.setDefaultRenderer(Object.class, cellRenderer);
		tablaMisViajes.setPreferredScrollableViewportSize(new Dimension(150, 60)); // Tamaño deseado para la tabla
		tablaMisViajes.setFillsViewportHeight(true); // Rellenar el área de visualización
		tablaMisViajes.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelCentroCentro.add(scrollPane, BorderLayout.NORTH);

		tablaMisViajes.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
		    JLabel label = new JLabel(value + "");
		    label.setFont(new Font("Arial", Font.PLAIN, 14));
		    label.setOpaque(true);

		    // Cambiar color de fondo de toda la fila cuando el ratón está sobre ella
		    if (filaMouseOver == row) {
		        label.setBackground(new Color(204, 229, 255));
		    } else {
		        label.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
		    }

		    return label;
		});

		tablaMisViajes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseExited(MouseEvent e) {
		        filaMouseOver = -1;
		        tablaMisViajes.repaint();
		    }
		});

		tablaMisViajes.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		        filaMouseOver = tablaMisViajes.rowAtPoint(e.getPoint());
		        if (filaMouseOver >= 0) {
		            tablaMisViajes.repaint();
		        }
		    }
		});
		
//		tablaMisViajes.getColumn("Acciones").setCellRenderer(new ButtonRenderer());
//		tablaMisViajes.getColumn("Acciones").setCellEditor(new ButtonEditor(new JCheckBox()));
//		tablaMisViajes.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
//		tablaMisViajes.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(tablaMisViajes));

		// Configuración de las tabla
        tableModel2 = new DefaultTableModel(null, cabecera2) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Solo permitir edición en la columna de botones
                return column == 4;
            }
        };
        tablaViajesUnidos = new JTable(tableModel2);	
        
		tablaViajesUnidos = new JTable(tableModel2) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};	
		scrollPane2 = new JScrollPane(tablaViajesUnidos);
//		tablaViajesUnidos.setDefaultRenderer(Object.class, cellRenderer);
		tablaViajesUnidos.setPreferredScrollableViewportSize(new Dimension(150, 60)); // Tamaño deseado para la tabla
		tablaViajesUnidos.setFillsViewportHeight(true); // Rellenar el área de visualización
		tablaViajesUnidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelCentroS.add(scrollPane2, BorderLayout.CENTER);
	    //JButton bSalir = new JButton("Salir");

		tablaViajesUnidos.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
		    JLabel label = new JLabel(value + "");
		    label.setFont(new Font("Arial", Font.PLAIN, 14));
		    label.setOpaque(true);
		    // Cambiar color de fondo de toda la fila cuando el ratón está sobre ella
		    if (filaMouseOver == row) {
		        label.setBackground(new Color(204, 229, 255));
		    } else {
		        label.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
		    }

		    return label;
		});
		tablaViajesUnidos.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseExited(MouseEvent e) {
		        filaMouseOver = -1;
		        tablaViajesUnidos.repaint();
		    }
		});

		tablaViajesUnidos.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		        filaMouseOver = tablaViajesUnidos.rowAtPoint(e.getPoint());
		        if (filaMouseOver >= 0) {
		        	tablaViajesUnidos.repaint();
		        }
		    }
		});

//		tablaViajesUnidos.getColumn("Acciones").setCellRenderer(new ButtonRenderer());
//		tablaViajesUnidos.getColumn("Acciones").setCellEditor(new ButtonEditor(new JCheckBox()));
//		tablaViajesUnidos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
//		tablaViajesUnidos.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(tablaViajesUnidos));
		tablaMisViajes.getColumnModel().getColumn(0).setMinWidth(0);
		tablaMisViajes.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaMisViajes.getColumnModel().getColumn(0).setWidth(0);
		tablaViajesUnidos.getColumnModel().getColumn(0).setMinWidth(0);
		tablaViajesUnidos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaViajesUnidos.getColumnModel().getColumn(0).setWidth(0);

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
    	        int selectedRow = tablaViajesUnidos.getSelectedRow();
    	        if (selectedRow != -1) {
    	            // Obtener el ID del viaje desde la columna correspondiente
    	            int viajeId = (int) tablaViajesUnidos.getValueAt(selectedRow, 0);
    	            Usuario usuarioLogeado = gestorDB.getUsuarioLogeado();

    	            // Llamar a deleteViajeUsuario
    	            boolean success = gestorDB.deleteViajeUsuario(viajeId, usuarioLogeado.getDni());
    	            if (success) {
    	                // Eliminar la fila de la tabla
    	                ((DefaultTableModel) tablaViajesUnidos.getModel()).removeRow(selectedRow);
    	                JOptionPane.showMessageDialog(null, "Has salido del viaje exitosamente.");
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Error al salir del viaje. Verifica la base de datos.");
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Por favor selecciona un viaje para salir.");
    	        }
    	    }
    	});


    	btnEliminar.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        int selectedRow = tablaMisViajes.getSelectedRow();
    	        if (selectedRow != -1) {
    	            // Obtener el ID del viaje desde la columna correspondiente
    	            int viajeId = (int) tablaMisViajes.getValueAt(selectedRow, 0);

    	            // Llamar a deleteViaje
    	            boolean success = gestorDB.deleteViaje(viajeId);
    	            if (success) {
    	                // Eliminar la fila de la tabla
    	                ((DefaultTableModel) tablaMisViajes.getModel()).removeRow(selectedRow);
    	                JOptionPane.showMessageDialog(null, "El viaje ha sido eliminado exitosamente.");
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Error al eliminar el viaje. Verifica la base de datos.");
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Por favor selecciona un viaje para eliminar.");
    	        }
    	    }
    	});



        cargarDatosEnTablas();
//        tablaMisViajes.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
//        tablaMisViajes.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());
//
//        tablaViajesUnidos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
//        tablaViajesUnidos.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());
        

	}
	private void cargarDatosEnTablas() {
        Usuario usuarioLogeado = gestorDB.getUsuarioLogeado();

        // Limpia las tablas antes de recargar los datos
        tableModel.setRowCount(0);
        tableModel2.setRowCount(0);

        // Carga de "Mis Viajes" (conductor es el usuario logeado)
        List<Viaje> misViajes = gestorDB.getViajes();
        misViajes.stream()
                .filter(v -> v.getConductor() != null && v.getConductor().getDni().equals(usuarioLogeado.getDni()))
                .forEach(viaje -> tableModel.addRow(new Object[]{
                		viaje.getId(),
                        viaje.getOrigen(),
                        viaje.getDestino(),
                        viaje.getPlazas(),
                        viaje.getConductor().getDni(),
                        "Eliminar"
                }));

        // Carga de "Viajes Unidos" (usuario logeado es pasajero)
        gestorDB.getViajeUsuario().getOrDefault(usuarioLogeado, List.of())
                .forEach(viaje -> tableModel2.addRow(new Object[]{
                		viaje.getId(),
                        viaje.getOrigen(),
                        viaje.getDestino(),
                        viaje.getPlazas(),
                        viaje.getConductor() != null ? viaje.getConductor().getDni() : "N/A",
                        "Salir"
                }));
        
    }
	public void ratingEstrellas() {
		float rating;
		Usuario usuarioLogeado= gestorDB.getUsuarioLogeado();
		rating = usuarioLogeado.getRating(); 
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
	// Renderizador para botones en las tablas
	public class ButtonRenderer extends JButton implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public ButtonRenderer(String label) {
	        setText(label);
	        setOpaque(true);
	        setBackground(new Color(33, 150, 243));
	        setForeground(Color.WHITE);
	        setFont(new Font("Arial", Font.BOLD, 12));
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                                                   boolean hasFocus, int row, int column) {
	        return this;
	    }
	}

//	public class ButtonRenderer extends JButton implements TableCellRenderer {
//	    public ButtonRenderer() {
//	        setOpaque(true);
//	    }
//
//	    @Override
//	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//	        setText(value == null ? "Click" : value.toString());
//	        setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
//	        return this;
//	    }
//	}
//	public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
//	    private JButton button;
//	    private String label;
//	    private boolean clicked;
//
//	    public ButtonEditor() {
//	        button = new JButton();
//	        button.setOpaque(true);
//	        button.addActionListener(this);
//	    }
//
//	    @Override
//	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//	        label = value == null ? "Click" : value.toString();
//	        button.setText(label);
//	        clicked = true;
//	        return button;
//	    }
//
//	    @Override
//	    public Object getCellEditorValue() {
//	        if (clicked) {
//	            System.out.println("HOLA"); // Acción al hacer clic
//	        }
//	        clicked = false;
//	        return label;
//	    }
//
//	    @Override
//	    public void actionPerformed(ActionEvent e) {
//	        fireEditingStopped(); // Detener edición después del clic
//	    }
//
//	    @Override
//	    public boolean stopCellEditing() {
//	        clicked = false;
//	        return super.stopCellEditing();
//	    }
//	}
}
