package service;

import java.util.List;

import domainLN.Usuario;

public interface UserService {
	List<Usuario> getListUsers();
	
	void inicializarUsers();
	
	void addUser(Usuario user);
	void deleteUser(Usuario user);
}
