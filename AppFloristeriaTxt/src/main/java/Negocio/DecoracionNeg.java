package Negocio;

import java.util.List;

import Dao.DecoracionDao;
import Entidad.DecoracionEnt;

public class DecoracionNeg {
	
	DecoracionDao decoraciondao = new DecoracionDao();
	
	public List<DecoracionEnt> listar() {

		return decoraciondao.listar();
	}
	
	

	public boolean guardar() {
		
		return decoraciondao.guardar();
		
	}
	
	public boolean agregar(DecoracionEnt decoracion) {
		
		return decoraciondao.agregar(decoracion);
		
	}
	
	public boolean modificar(int i, DecoracionEnt decoracion) {
		
		return decoraciondao.modificar(i, decoracion);
		
	}
	
	public boolean retirar(DecoracionEnt decoracion) {
		
		return decoraciondao.retirar(decoracion);
		
	}
	
	public DecoracionEnt obtenerRegistro(int i) {
		
		return decoraciondao.obtenerRegistro(i);
		
	}

	public int buscaCodigo(int codigo) {
		
		return decoraciondao.buscaCodigo(codigo);
		
	}

	public int GenerarCodigo() {
		
		return decoraciondao.GenerarCodigo();
		
	}	

}
