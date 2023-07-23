package Entidad;

public class DecoracionEntVista extends ProductoEnt {
	
	private String tipoMaterial;

	public DecoracionEntVista(int codigo, String nombre, double precio, String tipoMaterial) {
		super(codigo, nombre, precio);
		this.tipoMaterial = tipoMaterial;
	}

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	@Override
	public String toString() {
		return super.toString() + "Decoracion [tipoMaterial=" + tipoMaterial + "]";
	}
	
	

}
