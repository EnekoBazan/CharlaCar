package domainLN;

import java.util.List;
import java.util.Objects;

public class Viaje {

    private int id; // ID generado automáticamente por la base de datos
    private String origen;
    private String destino;
    private int plazas;
    private Usuario conductor;
    private List<Usuario> listaPasajeros;

    // Constructor sin ID (para nuevos viajes, la base de datos generará el ID)
    public Viaje(String origen, String destino, int plazas, Usuario conductor, List<Usuario> listaPasajeros) {
        this.id = -1; // Valor temporal para indicar que el ID no se ha asignado
        this.origen = origen;
        this.destino = destino;
        this.plazas = plazas;
        this.conductor = conductor;
        this.listaPasajeros = listaPasajeros;
    }

    // Constructor completo (para viajes existentes con ID conocido)
    public Viaje(int id, String origen, String destino, int plazas, Usuario conductor, List<Usuario> listaPasajeros) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.plazas = plazas;
        this.conductor = conductor;
        this.listaPasajeros = listaPasajeros;
    }

    public Viaje() {
        // Constructor vacío para casos genéricos
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Usuario getConductor() {
        return conductor;
    }

    public void setConductor(Usuario conductor) {
        this.conductor = conductor;
    }

    public List<Usuario> getListaPasajeros() {
        return listaPasajeros;
    }

    public void setListaPasajeros(List<Usuario> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
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
               ", plazas=" + plazas + ", conductor=" + conductor +
               ", listaPasajeros=" + listaPasajeros + "]";
    }
}
