package Entidad;

public class VistaDetalleCompraEnt {
	
	private int linea;
	private String tipo;
	private String idProducto;
	private int cantidad;
	private float precio;
	private float total;
	
	public VistaDetalleCompraEnt(int linea, String tipo, String idProducto, int cantidad, float precio, float total) {
		super();
		this.linea = linea;
		this.tipo = tipo;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
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

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "VistaDetalleCompraEnt [linea=" + linea + ", tipo=" + tipo + ", idProducto=" + idProducto + ", cantidad="
				+ cantidad + ", precio=" + precio + ", total=" + total + "]";
	}
	
	
	
	

}
