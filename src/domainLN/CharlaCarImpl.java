package domainLN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

// Implementación de Singleton para la lógica de CharlaCar
public class CharlaCarImpl implements CharlaCarService {

    private Usuario logedUser;
    private boolean isLoged = false;

    private List<Viaje> listaViajes;
    private List<Usuario> listaUsuarios;
    private ArrayList<Viaje> listaViajesPorUsuarios;

    private static CharlaCarImpl charlaCarImpl;

    private CharlaCarImpl() {
        listaViajes = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        listaViajesPorUsuarios = new ArrayList<>();
        inicializarUsers();
        inicializarViajes();
    }

    public static synchronized CharlaCarImpl getCharlaCarImpl() {
        if (charlaCarImpl == null) {
            charlaCarImpl = new CharlaCarImpl();
        }
        return charlaCarImpl;
    }

    public boolean isLoged() {
        return isLoged;
    }

    public void setLoged(boolean isLoged) {
        this.isLoged = isLoged;
    }

    public Usuario getLogedUser() {
        return logedUser;
    }

    public void setLogedUser(Usuario u) {
        this.logedUser = u;
        inicializarViajesPorUsuario();
    }

    public List<Viaje> getViajes() {
        return new ArrayList<>(listaViajes);
    }

    public boolean addViaje(Viaje viaje) {
        if (viaje != null && !listaViajes.contains(viaje)) {
            listaViajes.add(viaje);
        }
		return isLoged;
    }

    public boolean deleteViaje(Viaje viaje) {
        return listaViajes.remove(viaje);
    }

    public List<Usuario> getListUsers() {
        return new ArrayList<>(listaUsuarios);
    }

    public boolean addUser(Usuario user) {
        if (user != null && !listaUsuarios.contains(user)) {
            listaUsuarios.add(user);
        }
		return isLoged;
    }

    public boolean deleteUser(Usuario user) {
        return listaUsuarios.remove(user);
    }

    public void relojTiempoReal(JLabel lblHora) {
        Runnable relojTask = () -> {
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            while (isLoged) {
                try {
                    String horaActual = formatoHora.format(new Date());
                    SwingUtilities.invokeLater(() -> lblHora.setText(horaActual));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("El hilo del reloj fue interrumpido.");
                    Thread.currentThread().interrupt();
                }
            }
        };
        new Thread(relojTask).start();
    }

    public void inicializarViajesPorUsuario() {
        if (logedUser == null) {
            listaViajesPorUsuarios.clear();
            return;
        }
        listaViajesPorUsuarios = listaViajes.stream()
            .filter(viaje -> viaje.getListaPasajeros().contains(logedUser))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private void inicializarViajes() {
        // Verificar que haya usuarios suficientes antes de asignar conductores
        if (listaUsuarios.isEmpty()) {
            inicializarUsers();
        }

        if (listaUsuarios.size() < 3) {
            throw new IllegalStateException("No hay suficientes usuarios para inicializar los viajes.");
        }

        Usuario conductorPredeterminado = listaUsuarios.get(0);

        listaViajes.add(new Viaje(1, "Valencia", "Barcelona", 4, logedUser, listaUsuarios));
        listaViajes.add(new Viaje(2, "Bilbao", "Sevilla", 3, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(3, "Vitoria", "Valencia", 2, listaUsuarios.get(1), listaUsuarios));
        listaViajes.add(new Viaje(4, "Madrid", "Bilbao", 1, listaUsuarios.get(4), listaUsuarios));
        listaViajes.add(new Viaje(5, "Malaga", "Santander", 5, listaUsuarios.get(5), listaUsuarios));
        listaViajes.add(new Viaje(6, "Galicia", "Malaga", 2, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(7, "Sevilla", "Cordoba", 4, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(8, "Valencia", "Castellon", 3, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(9, "Bilbao", "San Sebastian", 6, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(10, "Zaragoza", "Huesca", 2, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(11, "Oviedo", "Gijon", 5, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(12, "Madrid", "Toledo", 3, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(13, "Barcelona", "Tarragona", 4, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(14, "Valencia", "Alicante", 2, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(15, "Bilbao", "Vitoria", 2, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(16, "Sevilla", "Cadiz", 5, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(17, "Malaga", "Santander", 5, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(18, "Malaga", "Santander", 5, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(19, "Malaga", "Santander", 5, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(20, "Malaga", "Santander", 5, listaUsuarios.get(0), listaUsuarios));
        listaViajes.add(new Viaje(21, "Malaga", "Santander", 5, listaUsuarios.get(0), listaUsuarios));
    }

    private void inicializarUsers() {
        if (listaUsuarios.isEmpty()) {
        	
        	listaUsuarios.add(new Usuario("73627382J", "a", "Palotes", "a", true, 2.0f));
        	listaUsuarios.add(new Usuario("73627382J", "Juan", "Perez", "contraseña", true, 4.0f));
        	listaUsuarios.add(new Usuario("73627382J", "Maria", "Lopez", "contraseña", true, 1.0f));
        	listaUsuarios.add(new Usuario("73627382J", "Ana", "Garcia", "contraseña", true, 5.0f));
        	listaUsuarios.add(new Usuario("73627382J", "Pedro", "Rodriguez", "contraseña", true, 3.0f));
        	listaUsuarios.add(new Usuario("12345678A", "Alejandro", "Gonzalez", "pass123", true, 4.5f));
        	listaUsuarios.add(new Usuario("87654321B", "Luisa", "Diaz", "luisa123", true, 3.8f));
        	listaUsuarios.add(new Usuario("45678912C", "Carlos", "Fernandez", "carlos456", true, 2.9f));
        	listaUsuarios.add(new Usuario("23456789D", "Marta", "Sanchez", "marta789", true, 4.2f));
        	listaUsuarios.add(new Usuario("98765432E", "Javier", "Torres", "javier321", true, 3.5f));
        }

        // Establecer un usuario logueado si no existe
        if (logedUser == null) {
            logedUser = listaUsuarios.get(0);
        }
    }

	@Override
	public void detenerHilo() {
		// TODO Auto-generated method stub
		
	}
}
