package Entidad;

public class DetalleVentaEnt {
	private int codigo;
	private int linea;
	private int tipo;
	private int idProducto;
	private int cantidad;
	private float precio;
	private String factura;
	
	public DetalleVentaEnt(int codigo, int linea, int tipo, int idProducto, int cantidad, float precio,
			String factura) {
		
		this.codigo = codigo;
		this.linea = linea;
		this.tipo = tipo;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.factura = factura;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "DetalleVentaEnt [codigo=" + codigo + ", linea=" + linea + ", tipo=" + tipo + ", idProducto="
				+ idProducto + ", cantidad=" + cantidad + ", precio=" + precio + ", factura=" + factura + "]";
	}
	
	
	
	
	
}
