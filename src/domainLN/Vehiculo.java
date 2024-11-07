package domainLN;

import java.util.Objects;

public class Vehiculo {

	private String matricula;
	private TipoVehiculo tipo;
	private int plazas;
	private Usuario propietario;
	
	public Vehiculo(String matricula, TipoVehiculo tipo, int plazas, Usuario propietario) {
		super();
		this.matricula = matricula;
		this.tipo = tipo;
		this.plazas = plazas;
		this.propietario = propietario;
	}

	public Vehiculo() {
		super();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula, plazas, propietario, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula) && plazas == other.plazas
				&& Objects.equals(propietario, other.propietario) && tipo == other.tipo;
	}
}