package guiLP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.GestorBD;
import domainLN.Usuario;

public class VentanaLogin extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtUser;
    private JPasswordField passwordField;
    private JButton btnAcceder;
    private JLabel lblUsuario;
    private JLabel lblClave;
    private JLabel lblRegistro;
    private JCheckBox checkMostrarClave;
    private JButton btnRecuperarClave;

    GestorBD gestorDB = GestorBD.getGestorDB();

    public VentanaLogin() {

        setModal(true);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350, 250);
        setTitle("CharlaCar (LogIn)");
        setResizable(false);

        // Campo de Usuario
        txtUser = new JTextField();
        txtUser.setBounds(140, 30, 150, 25);
        getContentPane().add(txtUser);

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 100, 25);
        getContentPane().add(lblUsuario);

        // Campo de Contraseña
        passwordField = new JPasswordField();
        passwordField.setBounds(140, 70, 150, 25);
        getContentPane().add(passwordField);

        lblClave = new JLabel("Contraseña:");
        lblClave.setBounds(30, 70, 100, 25);
        getContentPane().add(lblClave);

        // Botón de "Mostrar Contraseña"
        checkMostrarClave = new JCheckBox("Mostrar contraseña");
        checkMostrarClave.setBounds(140, 100, 150, 20);
        checkMostrarClave.setBackground(new Color(217, 239, 248));
        getContentPane().add(checkMostrarClave);

        checkMostrarClave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMostrarClave.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Muestra la contraseña
                } else {
                    passwordField.setEchoChar('*'); // Oculta la contraseña
                }
            }
        });

        // Botón "Acceder"
        btnAcceder = new JButton("Acceder");
        btnAcceder.setBounds(140, 140, 100, 30);
        btnAcceder.setForeground(new Color(33, 150, 243));
        btnAcceder.setBackground(Color.white);
        btnAcceder.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
        btnAcceder.setPreferredSize(new Dimension(60, 25));
        getContentPane().add(btnAcceder);

        btnAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        // Enlace para Registro
        lblRegistro = new JLabel("¿No estás registrado?");
        lblRegistro.setForeground(Color.BLUE);
        lblRegistro.setFont(new Font("Poppins", Font.BOLD, 10));
        lblRegistro.setBounds(120, 180, 150, 20);
        lblRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Cambiar cursor
        getContentPane().add(lblRegistro);

        lblRegistro.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                VentanaRegistro vRegistro = new VentanaRegistro();
                vRegistro.setVisible(true);
            }

            public void mouseEntered(MouseEvent e) {
                lblRegistro.setText("<html><u>¿No estás registrado?</u></html>");
            }

            public void mouseExited(MouseEvent e) {
                lblRegistro.setText("¿No estás registrado?");
            }
        });

        // Botón "Recuperar Contraseña"
        btnRecuperarClave = new JButton("Recuperar contraseña");
        btnRecuperarClave.setBounds(115, 210, 150, 30);
        btnRecuperarClave.setForeground(new Color(33, 150, 243));
        btnRecuperarClave.setBackground(Color.white);
        btnRecuperarClave.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243)));
        btnRecuperarClave.setPreferredSize(new Dimension(60, 25));
        btnRecuperarClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnRecuperarClave);

        btnRecuperarClave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Por favor, contacta al administrador para recuperar tu contraseña.", "Recuperar contraseña", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Estilo general
        ImageIcon icon = new ImageIcon("resources/images/favicon.png");
        setIconImage(icon.getImage());
        getContentPane().setBackground(new Color(217, 239, 248));
        setLocationRelativeTo(null);
    }

    private void autenticarUsuario() {
        String nombre = txtUser.getText().trim();
        String contraseña = new String(passwordField.getPassword()).trim();

        if (nombre.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            gestorDB.connect();

            if (gestorDB.existeUsuarioLogin(nombre, contraseña)) {
                VentanaPrincipal.btnLogIn.setVisible(false);
                VentanaPrincipal.btnRegistro.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);

                Usuario usuarioLogeado = gestorDB.getUsuarioByNombreAndContraseña(nombre, contraseña);
                gestorDB.setUsuarioLogeado(usuarioLogeado);

                VentanaPrincipal.btnUsuario.setEnabled(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            gestorDB.close();
        }
    }
}
