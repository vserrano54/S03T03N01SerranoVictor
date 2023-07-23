package Entidad;

public class VentaVistaEnt {
	
	private String factura;
	private String fecha;
	private float grantotal;
	
	public VentaVistaEnt(String factura, String fecha, float grantotal) {
		
		this.factura = factura;
		this.fecha = fecha;
		this.grantotal = grantotal;
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

	public float getGrantotal() {
		return grantotal;
	}

	public void setGrantotal(float grantotal) {
		this.grantotal = grantotal;
	}

	@Override
	public String toString() {
		return "VentaVistaEnt [factura=" + factura + ", fecha=" + fecha + ", grantotal=" + grantotal + "]";
	}
	
	
	
	

}
