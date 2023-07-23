package Negocio;


import java.util.List;

import Dao.StockDao;
import Entidad.StockEnt;

public class StockNeg {
	
	StockDao stockcd = new StockDao();
	
	public List<StockEnt> listar() {

		return stockcd.listar();
	}

	public boolean guardar() {

		return stockcd.guardar();
	}
	
	public boolean agregar(StockEnt stock) {
		
		return stockcd.agregar(stock);
		
	}
	
	public boolean modificar(int i, StockEnt stock) {
		
		return stockcd.modificar(i, stock);
	}
	
	public boolean retirar(StockEnt stock) {
		
		return stockcd.retirar(stock);
		
	}
	
	public StockEnt obtenerRegistro(int i) {
		
		return stockcd.obtenerRegistro(i);
		
	}

	public int cantidadRegistro() {
		
		return stockcd.cantidadRegistro();
	}

	public int buscaCodigol(int codigo) {
		
		return stockcd.buscaCodigol(codigo);
		
	}
	public int buscaIdProducto(int codigo, int tipo) {
		
		return stockcd.buscaIdProducto(codigo, tipo);
	}

	public int GenerarCodigo() {
		return stockcd.GenerarCodigo();
	}	

}
