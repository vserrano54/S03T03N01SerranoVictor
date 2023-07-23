package Entidad;


public class CompraEnt {
	private int codigo;
	private String factura;
	private String fecha;
	
	public CompraEnt(int codigo, String factura, String fehca) {
	
		this.codigo = codigo;
		this.factura = factura;
		this.fecha = fehca;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	
}