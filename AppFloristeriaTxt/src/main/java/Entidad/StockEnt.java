package Entidad;

public class StockEnt {
	private int idStock;
	private int tipo;
	private int idProducto; 
	private int cantidad;
	
	
	public StockEnt(int idStock, int tipo, int idProducto, int cantidad) {
		this.idStock = idStock;
		this.tipo = tipo;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	
	

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


}
