package domainLN;

import java.util.List;
import java.util.Objects;

public class Viaje {

	private String origen;
	private String destino;
	private int plazas;
	private Usuario conductor;
	private List<Usuario> listaPasajeros;
	
	public Viaje(String origen, String destino, int plazas, Usuario conductor,List<Usuario> listaPasajeros) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.plazas = plazas;
		this.conductor = conductor;
		this.listaPasajeros = listaPasajeros;
	}

	public Viaje() {
		super();
	}

	///GETTERS Y SETTERS
	public Usuario getConductor() {
		return conductor;
	}

	public void setConductor(Usuario conductor) {
		this.conductor = conductor;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public List<Usuario> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(List<Usuario> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(conductor, destino, listaPasajeros, origen, plazas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(conductor, other.conductor) && Objects.equals(destino, other.destino)
				&& Objects.equals(listaPasajeros, other.listaPasajeros) && Objects.equals(origen, other.origen)
				&& plazas == other.plazas;
	}

	@Override
	public String toString() {
		return "Viaje [origen=" + origen + ", destino=" + destino + ", plazas=" + plazas + ", conductor=" + conductor
				+ ", listaPasajeros=" + listaPasajeros + "]";
	}
	
	

	
}
	