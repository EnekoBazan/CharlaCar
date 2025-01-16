package guiLP;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import db.GestorBD;
import domainLN.Usuario;
import domainLN.Viaje;
import io.Propiedades;

public class VentanaCrearViaje extends JFrame {

    private static final long serialVersionUID = 1L;

    // Colores y Fuentes
    private static final Color COLOR_FONDO = new Color(217, 239, 248);
    private static final Color COLOR_BOTONES = new Color(33, 150, 243);
    private static final Color COLOR_TEXTO = new Color(60, 60, 60);
    private static final Font FUENTE_NORMAL = new Font("Inter", Font.PLAIN, 14);

    private GestorBD gestorDB = GestorBD.getGestorDB();
    private JTable tablaViaje;
    private DefaultTableModel tableModel;
    private JTextField txtFiltroTabla;

    // Paneles y Componentes
    private JComboBox<String> comboCiudadesOrigen;
    private JComboBox<String> comboCiudadesDestino;
    private JComboBox<String> comboAsientos;

    public VentanaCrearViaje() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setTitle("CharlaCar - Crear Viaje");
        setLocationRelativeTo(null);

        // Cargar propiedades
        Propiedades propiedades = new Propiedades();
        propiedades.cargar();
        setIconImage(new ImageIcon(propiedades.getProperty("favicon")).getImage());

        // Configurar la ventana principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(COLOR_FONDO);

        // Panel Superior (Formulario)
        JPanel panelFormulario = crearPanelFormulario();
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        // Panel Central (Tabla)
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // Panel Inferior (Botones)
        JPanel panelBotones = crearPanelBotones();
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearPanelFormulario() {
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(new TitledBorder("Datos del Viaje"));
        panelFormulario.setBackground(COLOR_FONDO);

        JLabel lblOrigen = new JLabel("Origen:");
        estilizarLabel(lblOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        estilizarLabel(lblDestino);

        JLabel lblAsientos = new JLabel("Asientos:");
        estilizarLabel(lblAsientos);

        String[] ciudades = { "Madrid", "Barcelona", "Valencia", "Sevilla", "Málaga" };
        comboCiudadesOrigen = new JComboBox<>(ciudades);
        comboCiudadesDestino = new JComboBox<>(ciudades);
        String[] asientos = { "1", "2", "3", "4", "5", "6" };
        comboAsientos = new JComboBox<>(asientos);

        estilizarComboBox(comboCiudadesOrigen);
        estilizarComboBox(comboCiudadesDestino);
        estilizarComboBox(comboAsientos);

        panelFormulario.add(lblOrigen);
        panelFormulario.add(comboCiudadesOrigen);
        panelFormulario.add(lblDestino);
        panelFormulario.add(comboCiudadesDestino);
        panelFormulario.add(lblAsientos);
        panelFormulario.add(comboAsientos);

        return panelFormulario;
    }

    private JPanel crearPanelTabla() {
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(new TitledBorder("Lista de Viajes"));
        panelTabla.setBackground(COLOR_FONDO);

        // Crear modelo de tabla
        String[] columnas = { "Origen", "Destino", "Asientos" };
        tableModel = new DefaultTableModel(columnas, 0);
        tablaViaje = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			@Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (column == 2) { // Condicional: colorear filas según asientos
                    int asientos = Integer.parseInt(getValueAt(row, column).toString());
                    c.setBackground(asientos < 3 ? new Color(255, 204, 204) : Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? new Color(240, 248, 255) : Color.WHITE);
                }
                return c;
            }
        };

        JScrollPane scrollPane = new JScrollPane(tablaViaje);
        tablaViaje.setFillsViewportHeight(true);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        // Campo de búsqueda
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFiltro.setBackground(COLOR_FONDO);
        JLabel lblFiltro = new JLabel("Buscar:");
        estilizarLabel(lblFiltro);

        txtFiltroTabla = new JTextField(15);
        txtFiltroTabla.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarTabla(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrarTabla(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrarTabla(); }
        });

        panelFiltro.add(lblFiltro);
        panelFiltro.add(txtFiltroTabla);
        panelTabla.add(panelFiltro, BorderLayout.NORTH);

        return panelTabla;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(COLOR_FONDO);

        JButton btnAgregar = new JButton("Agregar a Tabla");
        JButton btnCrearViaje = new JButton("Crear Viaje");
        estilizarBoton(btnAgregar);
        estilizarBoton(btnCrearViaje);

        btnAgregar.addActionListener(e -> agregarFilaATabla());
        btnCrearViaje.addActionListener(e -> crearViajeDesdeTabla());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnCrearViaje);

        return panelBotones;
    }

    private void agregarFilaATabla() {
        String origen = (String) comboCiudadesOrigen.getSelectedItem();
        String destino = (String) comboCiudadesDestino.getSelectedItem();
        String asientos = (String) comboAsientos.getSelectedItem();

        if (origen == null || destino == null || asientos == null || origen.equals(destino)) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos y asegúrese de que el origen y destino sean diferentes.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[] { origen, destino, asientos });
        comboCiudadesOrigen.setSelectedIndex(-1);
        comboCiudadesDestino.setSelectedIndex(-1);
        comboAsientos.setSelectedIndex(-1);
    }

    private void crearViajeDesdeTabla() {
        int filaSeleccionada = tablaViaje.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para crear el viaje.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            gestorDB.connect();

            String origen = tableModel.getValueAt(filaSeleccionada, 0).toString();
            String destino = tableModel.getValueAt(filaSeleccionada, 1).toString();
            int asientos = Integer.parseInt(tableModel.getValueAt(filaSeleccionada, 2).toString());

            Usuario usuarioLogeado = gestorDB.getUsuarioLogeado();
            Viaje nuevoViaje = new Viaje(0, origen, destino, asientos, usuarioLogeado, new ArrayList<>());
            gestorDB.insertarViaje(nuevoViaje);

            JOptionPane.showMessageDialog(this, "Viaje creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear el viaje: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            gestorDB.close();
        }
    }

    private void filtrarTabla() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        tablaViaje.setRowSorter(sorter);
        String textoFiltro = txtFiltroTabla.getText().trim();
        if (textoFiltro.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoFiltro));
        }
    }

    private void estilizarBoton(JButton boton) {
        boton.setFont(FUENTE_NORMAL);
        boton.setBackground(COLOR_BOTONES);
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void estilizarComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(FUENTE_NORMAL);
        comboBox.setBorder(BorderFactory.createLineBorder(COLOR_BOTONES));
    }

    private void estilizarLabel(JLabel label) {
        label.setFont(FUENTE_NORMAL);
        label.setForeground(COLOR_TEXTO);
    }

    public static void main(String[] args) {
        new VentanaCrearViaje();
    }
}
