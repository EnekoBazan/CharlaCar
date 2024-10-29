package service;

import java.util.ArrayList;
import java.util.List;

import domainLN.TipoVehiculo;
import domainLN.Vehiculo;
import domainLN.Viaje;

public class TravelServiceImpl implements TravelService {

	private List<Viaje> listaViajes = new ArrayList<Viaje>();
	private UserService userService;
	
	public TravelServiceImpl(UserService userService) {
        this.userService = userService;
    }

	@Override
    public void inicializarViajes() {
        if (userService.getListUsers().isEmpty()) {
            userService.inicializarUsers();
        }
        
        Vehiculo vehiculo = new Vehiculo("1234 HYS", TipoVehiculo.COCHE, 5, userService.getListUsers().get(0));
        
        listaViajes.add(new Viaje("Madrid", "Barcelona", 4, 0, userService.getListUsers(), "Viaje de trabajo"));
        listaViajes.get(0).setVehiculo(vehiculo);
        listaViajes.add(new Viaje("Madrid", "Sevilla", 3, 0, userService.getListUsers(), "Viaje de trabajo"));
        listaViajes.get(1).setVehiculo(vehiculo);
        listaViajes.add(new Viaje("Madrid", "Valencia", 2, 0, userService.getListUsers(), "Viaje de trabajo"));
        listaViajes.get(2).setVehiculo(vehiculo);
        listaViajes.add(new Viaje("Madrid", "Bilbao", 1, 0, userService.getListUsers(), "Viaje de trabajo"));
        listaViajes.get(3).setVehiculo(vehiculo);
        listaViajes.add(new Viaje("Madrid", "Santander", 5, 0, userService.getListUsers(), "Viaje de trabajo"));
        listaViajes.get(4).setVehiculo(vehiculo);
        listaViajes.add(new Viaje("Madrid", "Malaga", 3, 0, userService.getListUsers(), "Viaje de trabajo"));
        listaViajes.get(5).setVehiculo(vehiculo);
        System.out.println("Viajes inicializados");
    }

	@Override
	public List<Viaje> getViajes() {
		return listaViajes;
	}

	@Override
	public void addViaje(Viaje viaje) {
		listaViajes.add(viaje);

	}

	@Override
	public void deleteViaje(Viaje viaje) {
		listaViajes.remove(viaje);
	}

}
