package domainLN;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Viaje {

    private static final int ID_NO_ASIGNADO = -1; // Constante para ID no asignado

    private int id;
    private String origen;
    private String destino;
    private int plazas;
    private Usuario conductor;
    private List<Usuario> listaPasajeros;

    // Constructor sin ID (para nuevos viajes, la base de datos generará el ID)
    public Viaje(String origen, String destino, int plazas, Usuario conductor, List<Usuario> listaPasajeros) {
        this.id = ID_NO_ASIGNADO;
        setOrigen(origen);
        setDestino(destino);
        setPlazas(plazas);
        setConductor(conductor != null ? conductor : getDefaultConductor());
        this.listaPasajeros = (listaPasajeros != null) ? new ArrayList<>(listaPasajeros) : new ArrayList<>();
    }

    // Constructor completo (para viajes existentes con ID conocido)
    public Viaje(int id, String origen, String destino, int plazas, Usuario conductor, List<Usuario> listaPasajeros) {
        this.id = id;
        setOrigen(origen);
        setDestino(destino);
        setPlazas(plazas);
        setConductor(conductor != null ? conductor : getDefaultConductor());
        this.listaPasajeros = (listaPasajeros != null) ? new ArrayList<>(listaPasajeros) : new ArrayList<>();
    }

    public Viaje() {
        this.id = ID_NO_ASIGNADO;
        this.listaPasajeros = new ArrayList<>();
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        }
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        if (origen == null || origen.trim().isEmpty()) {
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        }
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
        this.destino = destino;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        if (plazas < 0) {
            throw new IllegalArgumentException("El número de plazas no puede ser negativo.");
        }
        this.plazas = plazas;
    }

    public Usuario getConductor() {
        return conductor;
    }

    public void setConductor(Usuario conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede ser nulo.");
        }
        this.conductor = conductor;
    }

    public List<Usuario> getListaPasajeros() {
        return new ArrayList<>(listaPasajeros);
    }

    public void setListaPasajeros(List<Usuario> listaPasajeros) {
        this.listaPasajeros = (listaPasajeros != null) ? new ArrayList<>(listaPasajeros) : new ArrayList<>();
    }

    public void addPasajero(Usuario pasajero) {
        if (listaPasajeros.size() >= plazas) {
            throw new IllegalStateException("No hay plazas disponibles.");
        }
        listaPasajeros.add(pasajero);
    }

    public void removePasajero(Usuario pasajero) {
        listaPasajeros.remove(pasajero);
    }

    private Usuario getDefaultConductor() {
        return new Usuario("00000000Z", "Conductor", "Predeterminado", "default", true, 0.0f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origen, destino, plazas, conductor, listaPasajeros);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Viaje other = (Viaje) obj;
        return id == other.id &&
               plazas == other.plazas &&
               Objects.equals(origen, other.origen) &&
               Objects.equals(destino, other.destino) &&
               Objects.equals(conductor, other.conductor) &&
               Objects.equals(listaPasajeros, other.listaPasajeros);
    }

    @Override
    public String toString() {
        return "Viaje [id=" + id + ", origen=" + origen + ", destino=" + destino +
               ", plazas=" + plazas + ", conductor=" + conductor.getNombre() +
               ", listaPasajeros=" + listaPasajeros.size() + " pasajeros]";
    }
}