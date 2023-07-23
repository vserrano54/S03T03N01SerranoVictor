package Negocio;

import java.util.List;


import Dao.DetalleVentaDao;
import Entidad.DetalleVentaEnt;

public class DetalleVentaNeg {
	
	DetalleVentaDao detalleventacn = new DetalleVentaDao();
	
	public List<DetalleVentaEnt> listar() {

		return detalleventacn.listar();
	}

	public List<DetalleVentaEnt> listar(String fac) {
		
		return detalleventacn.listar(fac);
	}

	public boolean guardar() {

		return detalleventacn.guardar();
	}
	
	
	public boolean agregar(DetalleVentaEnt detalle) {
	
		return detalleventacn.agregar(detalle);
	}
	
	public boolean modificar(int i, DetalleVentaEnt detalle) {
		
		return  detalleventacn.modificar(i, detalle);
		
	}
	
	public boolean retirar(DetalleVentaEnt detalle) {
		
		return detalleventacn.retirar(detalle);

	}
	
	public DetalleVentaEnt obtenerRegistro(int i) {
		
		return detalleventacn.obtenerRegistro(i);
	}
	
	public int GenerarCodigo() {
		return detalleventacn.GenerarCodigo();	
	}	


	public int buscaCodigol(int codigo) {
		
		return detalleventacn.buscaCodigol(codigo);
	}



}
