package service;

import java.util.ArrayList;
import java.util.List;

import domainLN.Usuario;

public class UserServiceImpl implements UserService {

	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	@Override
	public List<Usuario> getListUsers() {
		return listaUsuarios;
	}

	@Override
	public void addUser(Usuario user) {
		listaUsuarios.add(user);

	}

	@Override
	public void deleteUser(Usuario user) {
		listaUsuarios.remove(user);
	}

	@Override
	public void inicializarUsers() {
		listaUsuarios.add(new Usuario("Pepe", "Palotes", "73627382J", "contraseña", true, 7.0f));
		listaUsuarios.add(new Usuario("Juan", "Perez", "73627382J", "contraseña", true, 7.0f));
		listaUsuarios.add(new Usuario("Maria", "Lopez", "73627382J", "contraseña", true, 7.0f));
		listaUsuarios.add(new Usuario("Ana", "Garcia", "73627382J", "contraseña", true, 7.0f));
		listaUsuarios.add(new Usuario("Pedro", "Rodriguez", "73627382J", "contraseña", true, 7.0f));

	}

}
