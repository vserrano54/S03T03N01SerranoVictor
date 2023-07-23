package Negocio;

import java.util.List;

import Dao.DetalleCompraDao;
import Entidad.DetalleCompraEnt;

public class DetalleCompraNeg {
	
	DetalleCompraDao detallecompracn = new DetalleCompraDao();
	
	public List<DetalleCompraEnt> listar() {

		return detallecompracn.listar();
	}

	public List<DetalleCompraEnt> listar(String fac) {
		
		return detallecompracn.listar(fac);
	}

	public boolean guardar() {

		return detallecompracn.guardar();
	}
	
	
	public boolean agregar(DetalleCompraEnt detalle) {
	
		return detallecompracn.agregar(detalle);
	}
	
	public boolean modificar(int i, DetalleCompraEnt detalle) {
		
		return  detallecompracn.modificar(i, detalle);
		
	}
	
	public boolean retirar(DetalleCompraEnt detalle) {
		
		return detallecompracn.retirar(detalle);

	}
	
	public DetalleCompraEnt obtenerRegistro(int i) {
		
		return detallecompracn.obtenerRegistro(i);
	}
	
	public int GenerarCodigo() {
	
		return detallecompracn.GenerarCodigo();	
	}	


	public int buscaCodigol(int codigo) {
		
		return detallecompracn.buscaCodigol(codigo);
	}


}
