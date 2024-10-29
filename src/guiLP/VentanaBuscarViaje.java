package guiLP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import domainLN.Usuario;
import domainLN.Viaje;
import service.TravelServiceImpl;
import service.UserServiceImpl;

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
	private TableRowSorter<DefaultTableModel> rowSorter; //
	
	private UserServiceImpl userService = new UserServiceImpl();
	private TravelServiceImpl travelService = new TravelServiceImpl(userService);
	 
	//private JButton btnFiltrar;
	
	public VentanaBuscarViaje() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(750, 450);
		setTitle("CharlaCar");
		setLocationRelativeTo(null);
		
		setIconImage(new ImageIcon(VentanaPrincipal.class.getResource("/images/favicon.png")).getImage());

		//CABECERA DE TABLA
		cabecera = new String[] {"Matricula", "Propietario", "Origen", "Destino", "Asientos Totales", "Asientos Disponibles"};
		/////DATOS DE EJEMPLO
		// Inicializa primero los usuarios
		userService.inicializarUsers();
		for (Usuario user : userService.getListUsers()) {
            System.out.println(user.toString());
			
		}
		// Luego inicializa los viajes
		travelService.inicializarViajes();
		for (Viaje viaje : travelService.getViajes()) {
			System.out.println(viaje.toString());

		}

		// Lógica para preparar los datos de ejemplo
		datosEjemplo = travelService.getViajes().stream()
		    .map(viaje -> new String[] {
		        viaje.getVehiculo().getMatricula(),
		        viaje.getVehiculo().getPropietario().getNombre(),
		        viaje.getOrigen(),
		        viaje.getDestino(),
		        String.valueOf(viaje.getVehiculo().getPlazas()),
		        String.valueOf(viaje.getVehiculo().getPlazas() - viaje.getEspaciosDisponibles())
		    })
		    .toArray(String[][]::new);
		
		//PANEL PRINCIPAL
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 10, 5, 0));
		
		tableModel = new DefaultTableModel(datosEjemplo, cabecera);
		tablaBusqueda = new JTable(tableModel);
		
		scrollPane = new JScrollPane(tablaBusqueda);
		btnUnirse = new JButton("Unirse");
		btnUnirse.setForeground(new Color(33, 150, 243 ));
		btnUnirse.setBackground(Color.white);
		
		panelSouth = new JPanel(new BorderLayout());
		panelSouthDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelNorth = new JPanel(new FlowLayout());
		
		lblFiltro = new JLabel("Filtrar viajes: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
		//btnFiltrar = new JButton("Filtrar");
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
		panelSouth.setBackground(new Color(217, 239,248));
		panelPrincipal.add(panelSouth, BorderLayout.SOUTH);
		
		panelNorth.add(lblFiltro);
		panelNorth.add(txtFiltro);
		panelNorth.setBackground(new Color(217, 239, 248));
		
		//panelNorth.add(btnFiltrar);
		panelPrincipal.add(panelNorth, BorderLayout.NORTH);
		this.tablaBusqueda.setBorder(new EmptyBorder(10,10,10,10));
		this.tablaBusqueda.setDefaultRenderer(Object.class, cellRenderer);
		this.add(panelPrincipal);
		
		setVisible(true);
		
	}
		////RENDERER
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			//con rgb negro claro
			result.setHorizontalAlignment(JLabel.CENTER);
			result.setForeground(new Color(60,60,60));
			result.setBackground(Color.white);
			
			if (isSelected) {
				//result.setHorizontalAlignment(JLabel.CENTER);
				result.setBackground(new Color(173, 216, 230));
				result.setForeground(Color.black);			
			}
			else if (value instanceof Number) {
				//result.setHorizontalAlignment(JLabel.CENTER);
			} else {
				//Si el valor es texto pero representa un número se renderiza centrado
				try {
					Integer.parseInt(value.toString());
					//result.setHorizontalAlignment(JLabel.CENTER);				
				} catch(Exception ex) {
					result.setText(value.toString());
				}		
			}
			result.setOpaque(true);
			return result;
		};
	
	
	private void filtrar(String filtro) {
		
		//no chuta
//		  if (filtro.toLowerCase().length() == 0) {
//	            rowSorter.setRowFilter(null); // Sin filtro si el campo está vacío
//	        }
		
	}
}
