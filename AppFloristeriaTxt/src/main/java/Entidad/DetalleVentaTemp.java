package Entidad;

public class DetalleVentaTemp {
	
	private int linea;
	private int tipo;
	private int cod;
	private String nombre;
	private int cantidad;
	private String precio;
	
	public DetalleVentaTemp(int linea, int tipo, int cod, String nombre, int cantidad, String precio) {
		
		this.linea = linea;
		this.tipo = tipo;
		this.cod = cod;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
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

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
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
		return "DetalleVentaTemp [linea=" + linea + ", tipo=" + tipo + ", cod=" + cod + ", nombre=" + nombre
				+ ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}
	
	
	
	
	
	

}
