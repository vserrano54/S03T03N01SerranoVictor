package Entidad;

import java.util.List;

public class DatosVenta {
	
	private List<DetalleVentaTemp> tabla;
	private VentaTemp datosFactura;
	
	public DatosVenta(List<DetalleVentaTemp> tabla, VentaTemp datosFactura) {
		
		this.tabla = tabla;
		this.datosFactura = datosFactura;
	}

	public List<DetalleVentaTemp> getTabla() {
		return tabla;
	}

	public void setTabla(List<DetalleVentaTemp> tabla) {
		this.tabla = tabla;
	}

	public VentaTemp getDatosFactura() {
		return datosFactura;
	}

	public void setDatosFactura(VentaTemp datosFactura) {
		this.datosFactura = datosFactura;
	}
	
	
	
	

}
