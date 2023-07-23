package Entidad;

import java.util.List;

public class DatosCompra {
	
	private List<DetalleCompraTemp> tabla;
	private CompraTemp datosFactura;
	
	public DatosCompra() {}
	
	public List<DetalleCompraTemp> getTabla(){
		return tabla;
	}
	
	public void setTabla(List<DetalleCompraTemp> tabla) {
		this.tabla = tabla;
	}
	
	public CompraTemp getDatosFactura() {
		return datosFactura;
		
	}
	
	public void setOtrosDatos(CompraTemp datosFactura) {
		this.datosFactura = datosFactura;
	}
	
	

}
