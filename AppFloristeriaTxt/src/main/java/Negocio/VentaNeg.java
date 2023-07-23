package Negocio;

import java.util.List;

import Dao.VentaDao;
import Entidad.VentaEnt;

public class VentaNeg {
	
	VentaDao ventacd = new VentaDao();
	
	public List<VentaEnt> listar() {
	
		return ventacd.listar();
	
	}

	public boolean guardar() {
		
		return ventacd.guardar();
	
	}
	
	public boolean agregar(VentaEnt venta) {
	
		return ventacd.agregar(venta);
		
	}
	
	public boolean modificar(int i, VentaEnt venta) {
		
		return ventacd.modificar(i, venta);
	}
	
	public boolean retirar(VentaEnt venta) {
	
			return ventacd.retirar(venta);
			
	}
	
	public VentaEnt obtenerRegistro(int i) {
		
		return ventacd.obtenerRegistro(i);
		
	}

	
	public int buscaCodigol(int codigo) {
		
		return ventacd.buscaCodigol(codigo);

	
	}
	
	public int GenerarCodigo() {
		
	
		return ventacd.GenerarCodigo();
	}	


}
