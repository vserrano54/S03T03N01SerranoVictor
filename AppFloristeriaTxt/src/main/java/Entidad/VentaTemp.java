package Entidad;

public class VentaTemp {
	
	private String factura;
	private String fecha;
	
	public VentaTemp(String factura, String fecha) {
		
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
		return "VentaTemp [factura=" + factura + ", fecha=" + fecha + "]";
	}
	
	
	
	

}
