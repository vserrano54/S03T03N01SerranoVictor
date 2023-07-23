package Negocio;


import java.util.List;


import Dao.CompraDao;

import Entidad.CompraEnt;



public class CompraNeg {
	
	
	CompraDao compracd = new CompraDao();
	
	public List<CompraEnt> listar() {
		
		return compracd.listar();
	
	}

	public boolean guardar() {
		
		return compracd.guardar();
	
	}
	
	public boolean agregar(CompraEnt compra) {
		
		return compracd.agregar(compra);
		
	}
	
	public boolean modificar(int i, CompraEnt compra) {
		
		return compracd.modificar(i, compra);
	}
	
	public boolean retirar(CompraEnt compra) {
	
			return compracd.retirar(compra);
			
	}
	
	public CompraEnt obtenerRegistro(int i) {
		
		return compracd.obtenerRegistro(i);
		
	}

	
	public int buscaCodigol(int codigo) {
		
		return compracd.buscaCodigol(codigo);

	
	}
	
	public int GenerarCodigo() {
		
	
		return compracd.GenerarCodigo();
	}	

}
