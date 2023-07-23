package Negocio;

import java.util.List;

import Entidad.*;
import Dao.*;

public class ArbolNeg {
	
	ArbolDao arbolcd = new ArbolDao();
	
	public List<ArbolEnt> listar() {

		return arbolcd.listar();
		
	}

	public boolean guardar() {

		return arbolcd.guardar();
		
	}
	
	public boolean agregar(ArbolEnt arbol) {
		
		return arbolcd.agregar(arbol);
		
	}
	
	public boolean modificar(int i, ArbolEnt arbol) {
		
		return arbolcd.modificar(i, arbol);
		
	}
	
	public boolean retirar(ArbolEnt arbol) {
		
			return arbolcd.retirar(arbol);
	}
	
	public ArbolEnt obtenerRegistro(int i) {
		
		return arbolcd.obtenerRegistro(i);
		
	}

	public int buscaCodigo(int codigo) {
		
		return arbolcd.buscaCodigol(codigo);
		
	}

	public int GenerarCodigo() {
		
		return arbolcd.GenerarCodigo();
		
	}	

}
