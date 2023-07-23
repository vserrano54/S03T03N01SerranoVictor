package Entidad;


public class CompraTemp {
	private String factura;
	private String fecha;
	
	public CompraTemp() {}
	
	public CompraTemp(String factura, String fecha) {
		
		this.factura = factura;
		this.fecha = fecha;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "DatosFactura [factura=" + factura + ", Fecha=" + fecha + "]";
	}
	
	

}
