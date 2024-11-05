package domainLN;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String contraseña;
	private boolean carnet;
	private float rating;
	private ArrayList<Viaje> viajesPorUsuario;
	
	public Usuario(String nombre, String apellido, String dni, String contraseña, boolean carnet, float rating) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contraseña = contraseña;
		this.carnet = carnet;
		this.rating = rating;
	}
	public Usuario() {
		super();
	}
	
	///GETTERS Y SETTERS
	
	
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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(apellido, carnet, contraseña, dni, nombre, rating);
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
				&& Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating);
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", contraseña=" + contraseña
				+ ", carnet=" + carnet + ", rating=" + rating + "]";
	}
	public void addViaje(Viaje v) {
		// TODO Auto-generated method stub
		viajesPorUsuario.add( v );
	}
	
	
}
