package domainLN;

import java.util.ArrayList;
import java.util.List;

public interface CharlaCarService {

	public List<Viaje> getViajes();
	//public void inicializarViajes();
	public void addViaje(Viaje viaje);
	public void deleteViaje(Viaje viaje );
	
	
	//******************************************************
	
	public List<Usuario> getListUsers();	
	//public void inicializarUsers();	
	public void addUser(Usuario user);
	public void deleteUser(Usuario user);
	
	
	 //**************************************
	
	public boolean isLoged();
	public void setLoged(boolean isLoged);
	public void setLogedUser( Usuario u );
	public Usuario getLogedUser();
//	public void addViajeToUsuario( Viaje v);
//	public ArrayList<Viaje> getViajesPorUsuario();
}
