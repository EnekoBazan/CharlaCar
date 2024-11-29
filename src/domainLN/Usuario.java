package domainLN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Usuario {
	
	private String dni;
	private String nombre;
	private String apellido;
	private String contraseña;
	private boolean carnet;
	private float rating;
	public HashMap<Usuario, ArrayList<Viaje>> viajesPorUsuario = new HashMap<Usuario, ArrayList<Viaje>>();
	
	public Usuario(String dni, String nombre, String apellido, String contraseña, boolean carnet, float f) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contraseña = contraseña;
		this.carnet = carnet;
		this.rating = 0;
	}
	public Usuario() {
		super();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public boolean isCarnet() {
		return carnet;
	}
	public void setCarnet(boolean carnet) {
		this.carnet = carnet;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	public HashMap<Usuario, ArrayList<Viaje>> getViajesPorUsuario() {
		return viajesPorUsuario;
	}

	public void setViajesPorUsuario(HashMap<Usuario, ArrayList<Viaje>> viajesPorUsuario) {
		this.viajesPorUsuario = viajesPorUsuario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido, carnet, contraseña, dni, nombre, rating, viajesPorUsuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && carnet == other.carnet
				&& Objects.equals(contraseña, other.contraseña) && Objects.equals(dni, other.dni)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(rating, other.rating)
				&& Objects.equals(viajesPorUsuario, other.viajesPorUsuario);
	}
	@Override
	public String toString() {
		return "Usuario dni: " + dni + ", nombre: " + nombre + ", apellido: " + apellido + ", contraseña: " + contraseña
				+ ", carnet: " + carnet + ", rating: " + rating + ", viajesPorUsuario: " + viajesPorUsuario;
	}
//	public void addViaje(Viaje v) {
//		viajesPorUsuario.add( v );
//	}
	public String visualizarListaViajes() {
		return "Viajes del usuario" + viajesPorUsuario;
	}

	public void deleteViaje(Viaje v) {
		viajesPorUsuario.remove(v);
	}

}
