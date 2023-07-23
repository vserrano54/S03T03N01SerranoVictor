package Entidad;


public class VentaEnt {
	private int codigo;
	private String factura;
	private String fecha;
	public VentaEnt(int codigo, String factura, String fecha) {
		
		this.codigo = codigo;
		this.factura = factura;
		this.fecha = fecha;
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
	
	@Override
	public String toString() {
		return "VentaEnt [codigo=" + codigo + ", factura=" + factura + ", fecha=" + fecha + "]";
	}
	
	
	
}
