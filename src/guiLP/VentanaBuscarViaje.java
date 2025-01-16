package guiLP;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import db.GestorBD;
import domainLN.Usuario;
import domainLN.Vehiculo;
import domainLN.Viaje;

public class VentanaBuscarViaje extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaBusqueda;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JTextField txtFiltro;

    private GestorBD gestorBD = GestorBD.getGestorDB();

    public VentanaBuscarViaje() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(750, 450);
        setTitle("Buscar Viaje");
        setLocationRelativeTo(null);

        // Crear icono para la ventana
        ImageIcon icon = new ImageIcon("resources/images/favicon.png");
        setIconImage(icon.getImage());

        inicializarComponentes();
        cargarViajes();
        configurarFiltro();
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        configurarAtajoFiltro();
        
        setVisible(true);
    }

    private void inicializarComponentes() {
        // Inicializar modelo de la tabla
        String[] columnas = { "ID", "Origen", "Destino", "Plazas", "Conductor", "Matrícula" };
        tableModel = new DefaultTableModel(columnas, 0);
        tablaBusqueda = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Ocultar columna "ID"
        tablaBusqueda.getColumnModel().getColumn(0).setMinWidth(0);
        tablaBusqueda.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaBusqueda.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

        // Configurar el renderizado de las celdas
        tablaBusqueda.setDefaultRenderer(Object.class, new CustomCellRenderer());

        JScrollPane scrollPane = new JScrollPane(tablaBusqueda);

        // Botón "Unirse"
        JButton btnUnirse = new JButton("Unirse");
        btnUnirse.setForeground(new Color(33, 150, 243));
        btnUnirse.setBackground(Color.white);
        btnUnirse.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));

        btnUnirse.addActionListener(e -> gestionarUnirseViaje());

        // Panel superior con el filtro
        JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorth.setBackground(new Color(217, 239, 248));
        JLabel lblFiltro = new JLabel("Filtrar:");
        txtFiltro = new JTextField( 20);
        txtFiltro.setForeground(Color.GRAY);
        panelNorth.add(lblFiltro);
        panelNorth.add(txtFiltro);
        JLabel lblAtajo = new JLabel("Ctrl + C");
        lblAtajo.setForeground(Color.GRAY);
        panelNorth.add(lblAtajo);
        
        
        // Panel inferior con el botón
        JPanel panelSouth = new JPanel(new BorderLayout());
        JPanel panelSouthDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSouthDerecha.add(btnUnirse);
        panelSouthDerecha.setBackground(new Color(217, 239, 248));
        panelSouth.add(new JLabel(" Selecciona el viaje al que te quieres unir"), BorderLayout.WEST);
        panelSouth.add(panelSouthDerecha, BorderLayout.EAST);
        panelSouth.setBackground(new Color(217, 239, 248));

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(5, 10, 5, 10));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelNorth, BorderLayout.NORTH);
        panelPrincipal.add(panelSouth, BorderLayout.SOUTH);
        panelPrincipal.setBackground(new Color(217, 239, 248));

        this.add(panelPrincipal);
    }

    private void cargarViajes() {
        try {
            gestorBD.connect();
            List<Viaje> viajes = gestorBD.getViajes();
            for (Viaje viaje : viajes) {
                agregarViajeATabla(viaje);
            }
        } catch (Exception e) {
            mostrarMensaje("Error al cargar viajes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            gestorBD.close();
        }
    }

    private void agregarViajeATabla(Viaje viaje) {
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

        tableModel.addRow(new Object[] { viaje.getId(), viaje.getOrigen(), viaje.getDestino(), viaje.getPlazas(),
                conductor, matricula });
    }

    private void configurarFiltro() {
        txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrarTabla() {
                String textoFiltro = txtFiltro.getText().trim();
                if (textoFiltro.isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoFiltro));
                    } catch (PatternSyntaxException e) {
                        mostrarMensaje("Error en el texto del filtro: " + e.getMessage(), "Error de filtro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) { filtrarTabla(); }

            @Override
            public void removeUpdate(DocumentEvent e) { filtrarTabla(); }

            @Override
            public void changedUpdate(DocumentEvent e) { filtrarTabla(); }
        });

        rowSorter = new TableRowSorter<>(tableModel);
        tablaBusqueda.setRowSorter(rowSorter);
    }

    private void gestionarUnirseViaje() {
        try {
            gestorBD.connect();

            int filaSeleccionada = tablaBusqueda.getSelectedRow();
            if (filaSeleccionada < 0) {
                mostrarMensaje("Por favor selecciona un viaje válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idViajeSeleccionado = (int) tableModel.getValueAt(filaSeleccionada, 0);
            Viaje viajeSeleccionado = gestorBD.getViajePorId(idViajeSeleccionado);

            if (viajeSeleccionado == null || viajeSeleccionado.getPlazas() <= 0) {
                mostrarMensaje("No hay plazas disponibles para este viaje.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            gestorBD.unirUsuarioAViaje(viajeSeleccionado, gestorBD.getUsuarioLogeado());
            mostrarMensaje("Te has unido al viaje correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            mostrarMensaje("Error al unirte al viaje: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            gestorBD.close();
        }
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    private static class CustomCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String textoFiltro = ((VentanaBuscarViaje) SwingUtilities.getWindowAncestor(table)).txtFiltro.getText();

            // Alternar colores de filas
            if (!isSelected) {
                cell.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 248, 255));
            } else {
                cell.setBackground(new Color(173, 216, 230));
            }

            // Resaltar texto en rojo si coincide con el filtro
            if (textoFiltro != null && !textoFiltro.trim().isEmpty() && value != null) {
                String valorCelda = value.toString();
                if (valorCelda.toLowerCase().contains(textoFiltro.toLowerCase())) {
                    cell.setForeground(Color.RED);
                } else {
                    cell.setForeground(Color.BLACK);
                }
            } else {
                cell.setForeground(Color.BLACK);
            }

            return cell;
        }
    }
    private void configurarAtajoFiltro() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
                    txtFiltro.requestFocus();
                    txtFiltro.setBackground(new Color(200, 255, 200));
                }
            }
        });

        txtFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFiltro.setBackground(Color.WHITE);
            }
        });
    }
}
