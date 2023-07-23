package Entidad;

public class DetalleCompraTemp {
	private int linea;
	private int tipo;
	private int cod;
	private String nombre;
	private int cantidad;
	private String precio;
	
	public DetalleCompraTemp(int linea, int tipo, int cod, String nombre, int cantidad, String precio) {
		this.linea = linea;
		this.tipo = tipo;
		this.cod = cod;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public DetalleCompraTemp() {}

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

	public int getCod() {
		return cod;
	}

	public void setCodigo(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Tabla [linea=" + linea + ", tipo=" + tipo + ", codigo=" + cod + ", nombre=" + nombre + ", cantidad="
				+ cantidad + ", precio=" + precio + "]";
	}
	
	
	
	
	
	

	
	
}
