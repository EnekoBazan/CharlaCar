package domainLN;

import java.util.ArrayList;
import java.util.List;

//GESTORLN patron singleton
public class CharlaCarImpl implements CharlaCarService {

	
	private Usuario logedUser;
	private boolean isLoged = false;
	
	public boolean isLoged() {
		return isLoged;
	}

	public void setLoged(boolean isLoged) {
		this.isLoged = isLoged;
	}

	public void setLogedUser( Usuario u )
	{
		logedUser = u;
	    transferirViajesAListaUsuario();  // Llamamos al método que transfiere los viajes a la lista del usuario
	}
	
	public Usuario getLogedUser()
	{
		return logedUser;
	}
	
	
	
	private List<Viaje> listaViajes;
	private List<Usuario> listaUsuarios;
	private ArrayList<Viaje> listaViajesPorUsuarios;
	// ** Singleton
	private static CharlaCarImpl charlaCarImpl;

	private CharlaCarImpl() {
		listaViajes = new ArrayList<Viaje>();
		listaUsuarios = new ArrayList<Usuario>();
		listaViajesPorUsuarios = new ArrayList<Viaje>();
		inicializarViajes();
		inicializarUsers();
		inicializarViajesPorUsuario();
	}

	public static CharlaCarImpl getCharlaCarImpl() {
		if (charlaCarImpl == null) {
			charlaCarImpl = new CharlaCarImpl();
		}
		return charlaCarImpl;
	}
	// ** END SINGLETON

	
	private void inicializarViajes() {
		if (listaUsuarios.isEmpty()) {
			inicializarUsers();
		}

		Vehiculo vehiculo = new Vehiculo("1234 HYS",  6, listaUsuarios.get(0));
		Vehiculo vehiculo1 = new Vehiculo("9372 BMN", 5, listaUsuarios.get(1));
		Vehiculo vehiculo2= new Vehiculo("8754 KLO", 5, listaUsuarios.get(2));
		Vehiculo vehiculo3 = new Vehiculo("1730 HGR", 5, listaUsuarios.get(3));
		Vehiculo vehiculo4 = new Vehiculo("0034 GTR", 5, listaUsuarios.get(4));
		Vehiculo vehiculo5 = new Vehiculo("7890 ABC", 2, listaUsuarios.get(5));
		Vehiculo vehiculo6 = new Vehiculo("4567 DEF", 8, listaUsuarios.get(6));
		Vehiculo vehiculo7 = new Vehiculo("9012 GHI", 7, listaUsuarios.get(7));
		Vehiculo vehiculo8 = new Vehiculo("5678 JKL", 12, listaUsuarios.get(8));
		Vehiculo vehiculo9 = new Vehiculo("2345 MNO", 20, listaUsuarios.get(9));


		listaViajes.add(new Viaje("Valencia", "Barcelona", 4, 2, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(0).setVehiculo(vehiculo);
		listaViajes.add(new Viaje("Bilbao", "Sevilla", 3, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(1).setVehiculo(vehiculo1);
		listaViajes.add(new Viaje("Vitoria", "Valencia", 2, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(2).setVehiculo(vehiculo2);
		listaViajes.add(new Viaje("Madrid", "Bilbao", 1, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(3).setVehiculo(vehiculo3);
		listaViajes.add(new Viaje("Malaga", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(4).setVehiculo(vehiculo4);
		listaViajes.add(new Viaje("Galicia", "Malaga", 2, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(5).setVehiculo(vehiculo5);
		listaViajes.add(new Viaje("Sevilla", "Cordoba", 4, 1, listaUsuarios, "Viaje de ocio"));
		listaViajes.get(6).setVehiculo(vehiculo6);
		listaViajes.add(new Viaje("Valencia", "Castellon", 3, 2, listaUsuarios, "Viaje de compras"));
		listaViajes.get(7).setVehiculo(vehiculo7);
		listaViajes.add(new Viaje("Bilbao", "San Sebastian", 6, 0, listaUsuarios, "Viaje de turismo"));
		listaViajes.get(8).setVehiculo(vehiculo8);
		listaViajes.add(new Viaje("Zaragoza", "Huesca", 2, 1, listaUsuarios, "Viaje de negocios"));
		listaViajes.get(9).setVehiculo(vehiculo9);
		listaViajes.add(new Viaje("Oviedo", "Gijon", 5, 3, listaUsuarios, "Viaje de visita a familia"));
		listaViajes.get(10).setVehiculo(vehiculo1);
		listaViajes.add(new Viaje("Madrid", "Toledo", 3, 1, listaUsuarios, "Visita cultural"));
		listaViajes.get(11).setVehiculo(vehiculo);
		listaViajes.add(new Viaje("Barcelona", "Tarragona", 4, 2, listaUsuarios, "Excursión a la playa"));
		listaViajes.get(12).setVehiculo(vehiculo2);
		listaViajes.add(new Viaje("Valencia", "Alicante", 2, 1, listaUsuarios, "Reunión de negocios"));
		listaViajes.get(13).setVehiculo(vehiculo5);
		listaViajes.add(new Viaje("Bilbao", "Vitoria", 2, 0, listaUsuarios, "Visita a familiares"));
		listaViajes.get(14).setVehiculo(vehiculo4);
		listaViajes.add(new Viaje("Sevilla", "Cadiz", 5, 1, listaUsuarios, "Viaje de fin de semana"));
		listaViajes.get(15).setVehiculo(vehiculo3);
		listaViajes.add(new Viaje("Malaga", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(16).setVehiculo(vehiculo8);
		listaViajes.add(new Viaje("Malaga", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(17).setVehiculo(vehiculo9);
		listaViajes.add(new Viaje("Malaga", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(18).setVehiculo(vehiculo6);
		listaViajes.add(new Viaje("Malaga", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(19).setVehiculo(vehiculo4);
		listaViajes.add(new Viaje("Malaga", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(20).setVehiculo(vehiculo2);
		
	}

	private void inicializarUsers() {
		listaUsuarios.add(new Usuario("a", "Palotes", "73627382J", "a", true, 2.0f));
		listaUsuarios.add(new Usuario("Juan", "Perez", "73627382J", "contraseña", true, 4.0f));
		listaUsuarios.add(new Usuario("Maria", "Lopez", "73627382J", "contraseña", true, 1.0f));
		listaUsuarios.add(new Usuario("Ana", "Garcia", "73627382J", "contraseña", true, 5.0f));
		listaUsuarios.add(new Usuario("Pedro", "Rodriguez", "73627382J", "contraseña", true, 3.0f));
		listaUsuarios.add(new Usuario("Alejandro", "Gonzalez", "12345678A", "pass123", true, 4.5f));
		listaUsuarios.add(new Usuario("Luisa", "Diaz", "87654321B", "luisa123", true, 3.8f));
		listaUsuarios.add(new Usuario("Carlos", "Fernandez", "45678912C", "carlos456", true, 2.9f));
		listaUsuarios.add(new Usuario("Marta", "Sanchez", "23456789D", "marta789", true, 4.2f));
		listaUsuarios.add(new Usuario("Javier", "Torres", "98765432E", "javier321", true, 3.5f));

	}


	@Override
	public List<Viaje> getViajes() {
		return listaViajes;
	}

	@Override	
	public void addViaje(Viaje viaje) {
		listaViajes.add(viaje);

	}

	@Override
	public void deleteViaje(Viaje viaje) {
		listaViajes.remove(viaje);
	}

	@Override
	public List<Usuario> getListUsers() {
		return listaUsuarios;
	}

	@Override
	public void addUser(Usuario user) {
		listaUsuarios.add(user);

	}

	@Override
	public void deleteUser(Usuario user) {
		listaUsuarios.remove(user);
	}

	public void addViajeToUsuario( Viaje v) {
		logedUser.addViaje(v);
	}
	public void deleteViajeToUsuario( Viaje v) {
		logedUser.deleteViaje(v);
	}

	public void inicializarViajesPorUsuario() {
		if (logedUser == null) {
			System.out.println("No hay usuario logueado.");
			return;
		}

		// Filtramos los viajes donde el usuario logueado está incluido
		listaViajesPorUsuarios.clear(); // Limpiamos la lista antes de llenarla
		for (Viaje viaje : listaViajes) {
			if (viaje.getListaPasajeros().contains(logedUser)) {
				listaViajesPorUsuarios.add(viaje);
			}
		}
	}

	@Override
	public ArrayList<Viaje> getViajesPorUsuario() {
		return listaViajesPorUsuarios;
	}
	// Método que transfiere los viajes del CharlaCarImpl a la lista de viajes del usuario logueado
	public void transferirViajesAListaUsuario() {
	    // Verificar si el usuario logueado existe
	    if (logedUser == null) {
	        System.out.println("No hay usuario logueado.");
	        return;
	    }

	    // Obtener los viajes correspondientes al usuario logueado desde CharlaCarImpl
//	    ArrayList<Viaje> viajesDeCharlaCar = getViajesPorUsuario();

	    // Agregar estos viajes a la lista de viajes del usuario
	    for (Viaje viaje : listaViajesPorUsuarios) {
	        logedUser.addViaje(viaje);  // Suponiendo que addViaje() agrega el viaje a la lista de viajes del usuario
	    }
	}

}

