package guiLP;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
//import javax.swing.JSlider;
import javax.swing.JTextField;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;

public class VentanaRegistro extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDNI;
    private JPasswordField passwordField;
    private JCheckBox checkCarnet;
//    private JSlider sliderRating;
//    private JLabel lblRatingValue;
    private JButton btnRegistrar;
    
    public VentanaRegistro() {
        setModal(true);
        setSize(350, 350);
        setTitle("CharlaCar (Registro)");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        setLocationRelativeTo(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 100, 20);
        getContentPane().add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(140, 30, 150, 20);
        getContentPane().add(txtNombre);
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 70, 100, 20);
        getContentPane().add(lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setBounds(140, 70, 150, 20);
        getContentPane().add(txtApellido);
        
        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setBounds(30, 110, 100, 20);
        getContentPane().add(lblDNI);
        
        txtDNI = new JTextField();
        txtDNI.setBounds(140, 110, 150, 20);
        getContentPane().add(txtDNI);
        
        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setBounds(30, 150, 100, 20);
        getContentPane().add(lblClave);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(140, 150, 150, 20);
        getContentPane().add(passwordField);
        
        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(30, 190, 100, 20);
        getContentPane().add(lblCarnet);
        
        checkCarnet = new JCheckBox("Posee Carnet");
        checkCarnet.setBounds(140, 190, 150, 20);
        getContentPane().add(checkCarnet);
        
        //no tine que poner el rating el usuario que se registra
//        JLabel lblRating = new JLabel("Calificación:");
//        lblRating.setBounds(30, 230, 100, 20);
//        getContentPane().add(lblRating);
//        
//        sliderRating = new JSlider(0, 100, 50);
//        sliderRating.setBounds(140, 230, 150, 20);
//        sliderRating.setMajorTickSpacing(25);
//        sliderRating.setPaintTicks(true);
//        getContentPane().add(sliderRating);
        
//        lblRatingValue = new JLabel("50");  // Muestra el valor inicial del slider
//        lblRatingValue.setBounds(300, 230, 30, 20);
//        getContentPane().add(lblRatingValue);
//        
//        sliderRating.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                lblRatingValue.setText(String.valueOf(sliderRating.getValue()));
//            }
//        });
        //cambio
        //comentario 2
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(120, 280, 100, 30);
        getContentPane().add(btnRegistrar);
    }
}
