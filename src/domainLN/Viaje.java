package domainLN;

import java.util.List;
import java.util.Objects;

public class Viaje {

	private String origen;
	private String destino;
	private int espaciosDisponibles;
	private int espaciosOcupados;
	private List<Usuario> listaPasajeros;
	private String info;
	
	public Viaje(String origen, String destino, int espaciosDisponibles, int espaciosOcupados,
			List<Usuario> listaPasajeros, String info) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.espaciosDisponibles = espaciosDisponibles;
		this.espaciosOcupados = espaciosOcupados;
		this.listaPasajeros = listaPasajeros;
		this.info = info;
	}

	public Viaje() {
		super();
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

	public int getEspaciosDisponibles() {
		return espaciosDisponibles;
	}

	public void setEspaciosDisponibles(int espaciosDisponibles) {
		this.espaciosDisponibles = espaciosDisponibles;
	}

	public int getEspaciosOcupados() {
		return espaciosOcupados;
	}

	public void setEspaciosOcupados(int espaciosOcupados) {
		this.espaciosOcupados = espaciosOcupados;
	}

	public List<Usuario> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(List<Usuario> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destino, espaciosDisponibles, espaciosOcupados, info, listaPasajeros, origen);
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
		return Objects.equals(destino, other.destino) && espaciosDisponibles == other.espaciosDisponibles
				&& espaciosOcupados == other.espaciosOcupados && Objects.equals(info, other.info)
				&& Objects.equals(listaPasajeros, other.listaPasajeros) && Objects.equals(origen, other.origen);
	}
}
	