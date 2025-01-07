package domainLN;

import java.util.Objects;

public class Vehiculo {

    private String matricula;
    private int plazas;
    private Usuario propietario;

    public Vehiculo(String matricula, int plazas, Usuario propietario) {
        this.matricula = matricula;
        setPlazas(plazas);
        setPropietario(propietario);
    }

    public Vehiculo() {
        // Constructor vacío
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía.");
        }
        this.matricula = matricula;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        if (plazas <= 0) {
            throw new IllegalArgumentException("El número de plazas debe ser mayor que cero.");
        }
        this.plazas = plazas;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        if (propietario == null) {
            throw new IllegalArgumentException("El propietario no puede ser nulo.");
        }
        this.propietario = propietario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, plazas, propietario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        return Objects.equals(matricula, other.matricula) && plazas == other.plazas
                && Objects.equals(propietario, other.propietario);
    }

    @Override
    public String toString() {
        return "Vehiculo [matricula=" + matricula + ", plazas=" + plazas + ", propietario=" + propietario.getNombre() + "]";
    }
}