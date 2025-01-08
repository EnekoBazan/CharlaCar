package domainLN;

import java.util.List;
import javax.swing.JLabel;

/**
 * Interface que define los servicios principales de CharlaCar.
 */
public interface CharlaCarService {

    /**
     * Inicia un reloj en tiempo real y actualiza un JLabel con la hora actual.
     * @param lblHora JLabel donde se mostrará la hora actual.
     */
    public void relojTiempoReal(JLabel lblHora);

    /**
     * Detiene el hilo que actualiza el reloj en tiempo real.
     */
    public void detenerHilo();

    /**
     * Obtiene la lista de todos los viajes registrados.
     * @return Lista de viajes.
     */
    public List<Viaje> getViajes();

    /**
     * Añade un viaje al sistema.
     * @param viaje Viaje a añadir.
     * @return true si el viaje fue añadido exitosamente, false en caso contrario.
     */
    public boolean addViaje(Viaje viaje);

    /**
     * Elimina un viaje del sistema.
     * @param viaje Viaje a eliminar.
     * @return true si el viaje fue eliminado exitosamente, false en caso contrario.
     * @throws IllegalArgumentException si el viaje no existe.
     */
    public boolean deleteViaje(Viaje viaje);

    /**
     * Obtiene la lista de todos los usuarios registrados.
     * @return Lista de usuarios.
     */
    public List<Usuario> getListUsers();

    /**
     * Añade un usuario al sistema.
     * @param user Usuario a añadir.
     * @return true si el usuario fue añadido exitosamente, false en caso contrario.
     */
    public boolean addUser(Usuario user);

    /**
     * Elimina un usuario del sistema.
     * @param user Usuario a eliminar.
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     * @throws IllegalArgumentException si el usuario no existe.
     */
    public boolean deleteUser(Usuario user);

    /**
     * Verifica si un usuario está actualmente logueado.
     * @return true si hay un usuario logueado, false en caso contrario.
     */
    public boolean isLoged();

    /**
     * Establece el estado de login del sistema.
     * @param isLoged true para marcar como logueado, false para desloguear.
     */
    public void setLoged(boolean isLoged);

    /**
     * Establece el usuario actualmente logueado.
     * @param u Usuario a loguear.
     */
    public void setLogedUser(Usuario u);

    /**
     * Obtiene el usuario actualmente logueado.
     * @return Usuario logueado, o null si no hay usuario logueado.
     */
    public Usuario getLogedUser();
}
