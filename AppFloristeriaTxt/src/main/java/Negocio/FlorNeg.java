package Negocio;


import java.util.List;

import Dao.*;
import Entidad.*;

public class FlorNeg {
	
	FlorDao florcd = new FlorDao();
	
	public List<FlorEnt> listar() {
		
		return florcd.listar();

	}

	public boolean guardar() {

		return florcd.guardar();
	}
	
	public boolean agregar(FlorEnt flor) {
		
		return florcd.agregar(flor);
		
	}
	
	public boolean modificar(int i, FlorEnt flor) {
		
		return florcd.modificar(i, flor);
		
	}
	
	public boolean retirar(FlorEnt flor) {
		
		return florcd.retirar(flor);
		

	}
	
	public FlorEnt obtenerRegistro(int i) {
		
		return florcd.obtenerRegistro(i);
		
	}


	public int buscaCodigo(int codigo) {
		
		return florcd.buscaCodigo(codigo);
		
	}

	public int GenerarCodigo() {
		
		return florcd.GenerarCodigo();
		
	}	


}
