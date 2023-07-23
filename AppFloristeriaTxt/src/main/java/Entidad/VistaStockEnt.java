package Entidad;

public class VistaStockEnt {
	
	private int linea;
	private String tipo;
	private String Producto; 
	private int cantidad;
	
	public VistaStockEnt(int linea, String tipo, String producto, int cantidad) {
		this.linea = linea;
		this.tipo = tipo;
		Producto = producto;
		this.cantidad = cantidad;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getProducto() {
		return Producto;
	}

	public void setProducto(String producto) {
		Producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "VistaStockEnt [linea=" + linea + ", tipo=" + tipo + ", Producto=" + Producto + ", cantidad=" + cantidad
				+ "]";
	}
	
	
	

	
	
	
	

}
