package service;

import java.util.List;

import domainLN.Viaje;

public interface TravelService {

	List<Viaje> getViajes();
	void inicializarViajes();
	
	void addViaje(Viaje viaje);
	void deleteViaje(Viaje viaje );
}
