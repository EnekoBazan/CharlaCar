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
	}
	
	public Usuario getLogedUser()
	{
		return logedUser;
	}
	
	
	
	private List<Viaje> listaViajes;
	private List<Usuario> listaUsuarios;

	// ** Singleton
	private static CharlaCarImpl charlaCarImpl;

	private CharlaCarImpl() {
		listaViajes = new ArrayList<Viaje>();
		listaUsuarios = new ArrayList<Usuario>();
		
		inicializarViajes();
		inicializarUsers();
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

		Vehiculo vehiculo = new Vehiculo("1234 HYS", TipoVehiculo.COCHE, 6, listaUsuarios.get(0));
		Vehiculo vehiculo1 = new Vehiculo("9372 BMN", TipoVehiculo.COCHE, 5, listaUsuarios.get(1));
		Vehiculo vehiculo2= new Vehiculo("1234 HYS", TipoVehiculo.COCHE, 5, listaUsuarios.get(2));
		Vehiculo vehiculo3 = new Vehiculo("1234 HYS", TipoVehiculo.COCHE, 5, listaUsuarios.get(3));
		Vehiculo vehiculo4 = new Vehiculo("1234 HYS", TipoVehiculo.COCHE, 5, listaUsuarios.get(4));

		listaViajes.add(new Viaje("Madrid", "Barcelona", 4, 2, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(0).setVehiculo(vehiculo);
		listaViajes.add(new Viaje("Madrid", "Sevilla", 3, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(1).setVehiculo(vehiculo1);
		listaViajes.add(new Viaje("Madrid", "Valencia", 2, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(2).setVehiculo(vehiculo2);
		listaViajes.add(new Viaje("Madrid", "Bilbao", 1, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(3).setVehiculo(vehiculo3);
		listaViajes.add(new Viaje("Madrid", "Santander", 5, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(4).setVehiculo(vehiculo4);
		listaViajes.add(new Viaje("Madrid", "Malaga", 3, 0, listaUsuarios, "Viaje de trabajo"));
		listaViajes.get(5).setVehiculo(vehiculo);

	}

	private void inicializarUsers() {
		listaUsuarios.add(new Usuario("a", "Palotes", "73627382J", "a", true, 2.0f));
		listaUsuarios.add(new Usuario("Juan", "Perez", "73627382J", "contraseña", true, 4.0f));
		listaUsuarios.add(new Usuario("Maria", "Lopez", "73627382J", "contraseña", true, 1.0f));
		listaUsuarios.add(new Usuario("Ana", "Garcia", "73627382J", "contraseña", true, 5.0f));
		listaUsuarios.add(new Usuario("Pedro", "Rodriguez", "73627382J", "contraseña", true, 3.0f));

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
}
