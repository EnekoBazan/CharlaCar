package domainLN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Usuario {

    private String dni;
    private String nombre;
    private String apellido;
    private String contrasena;
    private boolean carnet;
    private float rating;
    private HashMap<Usuario, ArrayList<Viaje>> viajesPorUsuario;

    public Usuario(String dni, String nombre, String apellido, String contrasena, boolean carnet, float rating) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.carnet = carnet;
        this.rating = rating;
        this.viajesPorUsuario = new HashMap<>();
    }

    public Usuario() {
        this.viajesPorUsuario = new HashMap<>();
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        this.contrasena = contrasena;
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
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 0 y 5.");
        }
        this.rating = rating;
    }

    public HashMap<Usuario, ArrayList<Viaje>> getViajesPorUsuario() {
        return new HashMap<>(viajesPorUsuario);
    }

    public void addViaje(Usuario usuario, Viaje viaje) {
        viajesPorUsuario.computeIfAbsent(usuario, k -> new ArrayList<>()).add(viaje);
    }

    public void deleteViaje(Usuario usuario, Viaje viaje) {
        if (viajesPorUsuario.containsKey(usuario)) {
            viajesPorUsuario.get(usuario).remove(viaje);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(apellido, carnet, contrasena, dni, nombre, rating);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return carnet == other.carnet &&
               Float.compare(other.rating, rating) == 0 &&
               Objects.equals(dni, other.dni) &&
               Objects.equals(nombre, other.nombre) &&
               Objects.equals(apellido, other.apellido) &&
               Objects.equals(contrasena, other.contrasena);
    }

    @Override
    public String toString() {
        return "Usuario {" +
               "dni='" + dni + '\'' +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", carnet=" + carnet +
               ", rating=" + rating +
               '}';
    }

    public String visualizarListaViajes() {
        return "Viajes del usuario: " + viajesPorUsuario.toString();
    }
}
