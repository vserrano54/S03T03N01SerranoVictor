package Negocio;

import java.util.List;

import Dao.*;
import Entidad.*;

public class UsuarioNeg {
	
	UsuarioDao usuarios = new UsuarioDao();
	
	public List<UsuarioEnt> listar() {
		
    	return usuarios.listar();
    }
	

	public boolean guardar() {

		return usuarios.guardar();
	}
	
	public boolean agregar(UsuarioEnt usuario) {
		
		return usuarios.agregar(usuario);
		
	}
	
	public boolean modificar(int i, UsuarioEnt usuario) {
		
		return usuarios.modificar(i, usuario);
	}
	
	public boolean retirar(UsuarioEnt usuario) {
		
		return usuarios.retirar(usuario);

	}
	
	public UsuarioEnt obtenerRegistro(int i){
		
		return usuarios.obtenerRegistro(i);
		
	}


	public int buscaCodigo(int codigo) {
		
		return usuarios.buscaCodigo(codigo);
		
	}
	
	public int validar(String user, String pass){
		
		return usuarios.validar(user, pass);
	     
	}

	public int generarCodigo() {
		
		return usuarios.generarCodigo();
		
	}	

}
